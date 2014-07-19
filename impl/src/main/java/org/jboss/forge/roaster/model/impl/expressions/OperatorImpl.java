package org.jboss.forge.roaster.model.impl.expressions;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.expressions.OperatorExpression;
import org.jboss.forge.roaster.model.source.JavaSource;

public class OperatorImpl<O extends JavaSource<O>, T extends ExpressionSource<O>>
        extends ArgumentImpl<O,T>
        implements OperatorExpression<O,T> {

    public OperatorImpl( T origin, Expression expr, boolean wireToParent ) {
        super( origin, expr, wireToParent );
    }

    @Override
    public void wireExpression( ExpressionSource<O> child ) {
        concatExpression( this.expr, ((JdtExpressionWrapper) child).getExpression() );
    }

    protected void concatExpression( org.eclipse.jdt.core.dom.Expression parent, org.eclipse.jdt.core.dom.Expression child ) {
        if ( child.getNodeType() == ASTNode.INFIX_EXPRESSION ) {
            if ( ! ((InfixExpression) parent).getOperator().equals( ((InfixExpression) child).getOperator() ) ) {
                ParenthesizedExpression par = ast.newParenthesizedExpression();
                par.setExpression( child );
                child = par;
            }
        }

        InfixExpression infix = ((InfixExpression) parent);
        if (  "MISSING".equals( infix.getLeftOperand().toString() ) ) {
            infix.setLeftOperand( child );
        } else if ( "MISSING".equals( infix.getRightOperand().toString() ) ) {
            infix.setRightOperand( child );
        } else {
            org.eclipse.jdt.core.dom.Expression prev = infix.getRightOperand();
            InfixExpression more = ast.newInfixExpression();
            more.setOperator( infix.getOperator() );
            infix.setRightOperand( more );
            prev.delete();
            more.setLeftOperand( prev );
            more.setRightOperand( child );
        }
    }


    @Override
    public ExpressionFactory<O, OperatorExpression<O, T>> addArgument() {
        return new MockArgumentImpl<O,OperatorExpression<O,T>>( this, expr.getAST() );
    }
}
