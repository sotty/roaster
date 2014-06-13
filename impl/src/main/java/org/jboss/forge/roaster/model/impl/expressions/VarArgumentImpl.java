package org.jboss.forge.roaster.model.impl.expressions;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.expressions.Variable;
import org.jboss.forge.roaster.model.source.JavaSource;

public class VarArgumentImpl<O extends JavaSource<O>, T extends ExpressionSource<O>>
        extends ArgumentImpl<O,T>
        implements Variable<O,T> {

    public VarArgumentImpl( T origin, Expression expr, boolean wireToParent ) {
        super( origin, expr, wireToParent );
    }

    @Override
    protected void concatExpression( Expression parent, Expression child ) {
        throw new IllegalStateException( "Var expressions can't have direct children, but a child node was attempted : " + child );
    }

    @Override
    public T inc() {
        return applyPostFixOperator( PostfixExpression.Operator.INCREMENT );
    }

    @Override
    public T dec() {
        return applyPostFixOperator( PostfixExpression.Operator.DECREMENT );
    }
}
