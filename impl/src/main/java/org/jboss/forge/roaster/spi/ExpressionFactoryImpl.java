/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.spi;

import org.eclipse.jdt.core.dom.AST;
import org.jboss.forge.roaster.model.ExpressionHolder;
import org.jboss.forge.roaster.model.expressions.Accessor;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.expressions.ArrayConstructorExpression;
import org.jboss.forge.roaster.model.expressions.ArrayIndexer;
import org.jboss.forge.roaster.model.expressions.ArrayInit;
import org.jboss.forge.roaster.model.expressions.AssignExpression;
import org.jboss.forge.roaster.model.expressions.Assignment;
import org.jboss.forge.roaster.model.expressions.CastExpression;
import org.jboss.forge.roaster.model.expressions.ClassLiteral;
import org.jboss.forge.roaster.model.expressions.ConstructorExpression;
import org.jboss.forge.roaster.model.expressions.DeclareExpression;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.expressions.Field;
import org.jboss.forge.roaster.model.expressions.Getter;
import org.jboss.forge.roaster.model.expressions.InstanceofExpression;
import org.jboss.forge.roaster.model.expressions.Literal;
import org.jboss.forge.roaster.model.expressions.MethodCallExpression;
import org.jboss.forge.roaster.model.expressions.NonPrimitiveExpression;
import org.jboss.forge.roaster.model.expressions.NullLiteral;
import org.jboss.forge.roaster.model.expressions.Op;
import org.jboss.forge.roaster.model.expressions.OperatorExpression;
import org.jboss.forge.roaster.model.expressions.ParenExpression;
import org.jboss.forge.roaster.model.expressions.PrefixOp;
import org.jboss.forge.roaster.model.expressions.PrimitiveLiteral;
import org.jboss.forge.roaster.model.expressions.Setter;
import org.jboss.forge.roaster.model.expressions.StringLiteral;
import org.jboss.forge.roaster.model.expressions.Super;
import org.jboss.forge.roaster.model.expressions.TernaryExpression;
import org.jboss.forge.roaster.model.expressions.ThisLiteral;
import org.jboss.forge.roaster.model.expressions.UnaryExpression;
import org.jboss.forge.roaster.model.expressions.Variable;
import org.jboss.forge.roaster.model.impl.NodeImpl;
import org.jboss.forge.roaster.model.impl.expressions.ArrayAccessImpl;
import org.jboss.forge.roaster.model.impl.expressions.ArrayImpl;
import org.jboss.forge.roaster.model.impl.expressions.ArrayInitImpl;
import org.jboss.forge.roaster.model.impl.expressions.AssignImpl;
import org.jboss.forge.roaster.model.impl.expressions.BasicExpressionFactoryImpl;
import org.jboss.forge.roaster.model.impl.expressions.BooleanLiteralImpl;
import org.jboss.forge.roaster.model.impl.expressions.CastImpl;
import org.jboss.forge.roaster.model.impl.expressions.CharacterLiteralImpl;
import org.jboss.forge.roaster.model.impl.expressions.ClassLiteralImpl;
import org.jboss.forge.roaster.model.impl.expressions.ConstructorImpl;
import org.jboss.forge.roaster.model.impl.expressions.DeclareExpressionImpl;
import org.jboss.forge.roaster.model.impl.expressions.FieldImpl;
import org.jboss.forge.roaster.model.impl.expressions.GetterImpl;
import org.jboss.forge.roaster.model.impl.expressions.InstanceofImpl;
import org.jboss.forge.roaster.model.impl.expressions.MethodInvokeImpl;
import org.jboss.forge.roaster.model.impl.expressions.NotImpl;
import org.jboss.forge.roaster.model.impl.expressions.NullLiteralImpl;
import org.jboss.forge.roaster.model.impl.expressions.NumberLiteralImpl;
import org.jboss.forge.roaster.model.impl.expressions.OperatorImpl;
import org.jboss.forge.roaster.model.impl.expressions.ParenImpl;
import org.jboss.forge.roaster.model.impl.expressions.SelfArgumentImpl;
import org.jboss.forge.roaster.model.impl.expressions.StaticClassAccessorImpl;
import org.jboss.forge.roaster.model.impl.expressions.StringLiteralImpl;
import org.jboss.forge.roaster.model.impl.expressions.SuperImpl;
import org.jboss.forge.roaster.model.impl.expressions.TernaryImpl;
import org.jboss.forge.roaster.model.impl.expressions.UnaryImpl;
import org.jboss.forge.roaster.model.impl.expressions.VarArgumentImpl;
import org.jboss.forge.roaster.model.source.JavaSource;

public class ExpressionFactoryImpl<O extends JavaSource<O>,
                                   P extends ExpressionHolder<O>,
                                   E extends NonPrimitiveExpression<O,P,E>,
                                   J extends org.eclipse.jdt.core.dom.Expression>
        extends BasicExpressionFactoryImpl<O,P,J>
        implements ExpressionFactory<O,P,E> {


    public ExpressionFactoryImpl() { }

    @Override
    public Field<O,E> field( String fieldName ) {
        return new FieldImpl<O,E>( fieldName );
    }

    @Override
    public Getter<O,E> getter( String fieldName, String type ) {
        return new GetterImpl<O,E>( fieldName, type );
    }

    @Override
    public Getter<O,E> getter( String fieldName, Class type ) {
        return getter( fieldName, type.getName() );
    }

    @Override
    public Setter<O,E> setter( String fldName, String type, ExpressionSource<?,?,?> value ) {
        return setter( fldName, type, value );
    }

    @Override
    public Setter<O,E> setter( String fldName, Class type, ExpressionSource<?,?,?> value ) {
        return setter( fldName, type.getName(), value );
    }

    @Override
    public MethodCallExpression<O,E> invoke( String method ) {
        return new MethodInvokeImpl<O,E>( method );
    }


    @Override
    public ArrayIndexer<O,E> itemAt( ExpressionSource<?,?,?> index ) {
        return new ArrayAccessImpl<O,E>( index );
    }
}
