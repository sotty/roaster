/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.impl.expressions;

import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.InstanceofExpression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.jboss.forge.roaster.model.expressions.Accessor;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.expressions.MethodCallExpression;
import org.jboss.forge.roaster.model.expressions.Op;
import org.jboss.forge.roaster.model.expressions.OperatorExpression;
import org.jboss.forge.roaster.model.expressions.OrdinalArgument;
import org.jboss.forge.roaster.model.expressions.PrefixOp;
import org.jboss.forge.roaster.model.impl.JDTHelper;

import java.text.NumberFormat;
import java.text.ParseException;

import static org.jboss.forge.roaster.model.expressions.Expressions.cast;
import static org.jboss.forge.roaster.model.expressions.Expressions.getter;
import static org.jboss.forge.roaster.model.expressions.Expressions.instanceOf;
import static org.jboss.forge.roaster.model.expressions.Expressions.invoke;
import static org.jboss.forge.roaster.model.expressions.Expressions.literal;
import static org.jboss.forge.roaster.model.expressions.Expressions.nullLiteral;
import static org.jboss.forge.roaster.model.expressions.Expressions.operator;
import static org.jboss.forge.roaster.model.expressions.Expressions.setter;
import static org.jboss.forge.roaster.model.expressions.Expressions.ternary;
import static org.jboss.forge.roaster.model.expressions.Expressions.thisLiteral;
import static org.jboss.forge.roaster.model.expressions.Expressions.var;

import static org.eclipse.jdt.core.dom.ASTNode.*;

public class ExpressionBuilder {

    public static org.jboss.forge.roaster.model.expressions.ExpressionSource asRoasterExpression(org.eclipse.jdt.core.dom.Expression jdtExpression)
    {
        org.jboss.forge.roaster.model.expressions.ExpressionSource roasterExpr;
        switch (jdtExpression.getNodeType()) {
            case SIMPLE_NAME:
                roasterExpr = var( ((SimpleName) jdtExpression).getIdentifier() );
                break;
            case STRING_LITERAL:
                roasterExpr = literal( ( (StringLiteral) jdtExpression ).getLiteralValue() );
                break;
            case BOOLEAN_LITERAL:
                roasterExpr = literal( (( BooleanLiteral) jdtExpression).booleanValue() );
                break;
            case CHARACTER_LITERAL:
                roasterExpr = literal( ((CharacterLiteral) jdtExpression).charValue() );
                break;
            case NULL_LITERAL:
                roasterExpr = nullLiteral();
                break;
            case NUMBER_LITERAL:
                try {
                    roasterExpr = literal( NumberFormat.getInstance().parse(
                            ( (NumberLiteral) jdtExpression ).getToken() ) );
                } catch ( ParseException e ) {
                    // we know it's a valid number, so this should never happen
                    e.printStackTrace();
                    roasterExpr = null;
                }
                break;
            case CONDITIONAL_EXPRESSION:
                ConditionalExpression cond = (ConditionalExpression) jdtExpression;
                roasterExpr = ternary().setCondition( asRoasterExpression( cond.getExpression() ) )
                                        .setThenExpression( asRoasterExpression( cond.getThenExpression() ) )
                                        .setElseExpression( asRoasterExpression( cond.getElseExpression() ) );

                break;
            case CAST_EXPRESSION :
                CastExpression cast = (CastExpression) jdtExpression;
                roasterExpr = cast( cast.getType().toString(), asRoasterExpression( cast.getExpression() ) );
                break;
            case INFIX_EXPRESSION :
                InfixExpression infix = (InfixExpression) jdtExpression;
                roasterExpr = operator( Op.build( infix.getOperator().toString() ) )
                                .addArgument( (Argument<?, ?, ?>) asRoasterExpression( infix.getLeftOperand() ) )
                                .addArgument( (Argument<?, ?, ?>) asRoasterExpression( infix.getRightOperand() ) );
                for ( Object o : infix.extendedOperands() ) {
                    (( OperatorExpression) roasterExpr).addArgument( (Argument<?, ?, ?>) asRoasterExpression( (Expression) o ) );
                }
                break;
            case FIELD_ACCESS :
                FieldAccess fax = (FieldAccess) jdtExpression;
                Accessor axx = (Accessor) asRoasterExpression( fax.getExpression() );
                roasterExpr = axx.field( fax.getName().getIdentifier() );
                break;
            case INSTANCEOF_EXPRESSION :
                InstanceofExpression iof = (InstanceofExpression) jdtExpression;
                roasterExpr = instanceOf( iof.getRightOperand().toString(), asRoasterExpression( iof.getLeftOperand() ) );
                break;
            case THIS_EXPRESSION :
                roasterExpr = thisLiteral();
                break;
            case PREFIX_EXPRESSION:
                PrefixExpression pref = (PrefixExpression) jdtExpression;
                roasterExpr = operator( PrefixOp.build( pref.getOperator().toString() ), (Argument) asRoasterExpression( pref.getOperand() ) );
                break;
            case POSTFIX_EXPRESSION:
                PostfixExpression post = (PostfixExpression) jdtExpression;
                roasterExpr = asRoasterExpression( post.getOperand() );
                if ( post.getOperator() == PostfixExpression.Operator.INCREMENT ) {
                    roasterExpr = ((OrdinalArgument) roasterExpr).inc();
                } else {
                    roasterExpr = ((OrdinalArgument) roasterExpr).dec();
                }
                break;
            case METHOD_INVOCATION :
                MethodInvocation invocation = (MethodInvocation) jdtExpression;
                String name = invocation.getName().toString();
                if ( name.startsWith( "get" ) || name.startsWith( "is" ) && invocation.arguments().isEmpty() ) {
                    roasterExpr = getter( JDTHelper.fieldForGetter( name ), name.startsWith( "is" ) ? boolean.class : Object.class );
                    if ( invocation.getExpression() != null ) {
                        //((Getter) roasterExpr).setOrigin( asRoasterExpression( invocation.getExpression() ) );
                    }
                } else if ( name.startsWith( "set" ) && invocation.arguments().size() == 1 ) {
                    roasterExpr = setter( JDTHelper.fieldForSetter( name ), Object.class, asRoasterExpression( (Expression) invocation.arguments().get( 0 ) ) );
                    if ( invocation.getExpression() != null ) {
                        //((Setter) roasterExpr).setOrigin( asRoasterExpression( invocation.getExpression() ) );
                    }
                } else {
                    roasterExpr = invoke( name );
                    for ( Object arg : invocation.arguments() ) {
                        ((MethodCallExpression) roasterExpr).addArgument( (Argument<?, ?, ?>) asRoasterExpression( (Expression) arg ) );
                    }
                }
                break;
            default:
                throw new UnsupportedOperationException("Unable to handle expression of type: " + jdtExpression.getNodeType() + " \n >> " + jdtExpression.toString() );
        }
        if ( roasterExpr != null ) {
            ( (org.jboss.forge.roaster.model.ASTNode) roasterExpr ).setInternal( jdtExpression );
        }
        return roasterExpr;
    }

}
