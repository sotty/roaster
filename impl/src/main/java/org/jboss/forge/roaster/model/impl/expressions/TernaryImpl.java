package org.jboss.forge.roaster.model.impl.expressions;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.expressions.Expression;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.expressions.TernaryExpression;
import org.jboss.forge.roaster.model.source.JavaSource;

public class TernaryImpl<O extends JavaSource<O>, T extends ExpressionSource<O>>
        extends ArgumentImpl<O,T>
        implements TernaryExpression<O,T> {


    private enum State { COND, IF, ELSE };
    private State state;

    public TernaryImpl( T origin, org.eclipse.jdt.core.dom.Expression expr, boolean wireToParent ) {
        super( origin, expr, wireToParent );
    }

    @Override
    public ExpressionFactory<O, TernaryExpression<O, T>> condition() {
        state = State.COND;
        return new MockArgumentImpl<O, TernaryExpression<O,T>>( this, expr.getAST() );
    }

    @Override
    public ExpressionFactory<O, TernaryExpression<O, T>> yes() {
        state = State.IF;
        return new MockArgumentImpl<O, TernaryExpression<O,T>>( this, expr.getAST() );
    }

    @Override
    public ExpressionFactory<O, TernaryExpression<O, T>> no() {
        state = State.ELSE;
        return new MockArgumentImpl<O, TernaryExpression<O,T>>( this, expr.getAST() );
    }

    @Override
    public T end() {
        return (T) origin;
    }

    protected void concatExpression( org.eclipse.jdt.core.dom.Expression parent, org.eclipse.jdt.core.dom.Expression child ) {
        ConditionalExpression ce = (ConditionalExpression) parent;
        switch ( state ) {
            case COND   : ce.setExpression( child ); break;
            case IF     : ce.setThenExpression( child ); break;
            case ELSE   : ce.setElseExpression( child ); break;
        }
    }

}
