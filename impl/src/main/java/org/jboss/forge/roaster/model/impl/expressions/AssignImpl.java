package org.jboss.forge.roaster.model.impl.expressions;

import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.jboss.forge.roaster.model.expressions.AssignExpression;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.source.JavaSource;
import sun.reflect.FieldAccessor;

public class AssignImpl<O extends JavaSource<O>, T extends ExpressionSource<O>>
        extends ArgumentImpl<O,T>
        implements AssignExpression<O,T> {

    private enum State { LEFT, RIGHT };
    private State state;

    public AssignImpl( T origin, Expression expr, boolean wireToParent ) {
        super( origin, expr, wireToParent );
    }

    @Override
    protected void concatExpression( Expression parent, Expression child ) {
        switch ( state ) {
            case RIGHT:
                ((Assignment) parent).setRightHandSide( child );
                break;
            case LEFT:
                ((Assignment) parent).setLeftHandSide( child );
                break;
        }
    }

    @Override
    public T setVariableRightExpression( String name ) {
        ((Assignment) expr).setRightHandSide( expr.getAST().newSimpleName( name ) );
        return getOrigin();
    }

    @Override
    public AssignExpression<O, T> setFieldLeftExpression( String name ) {
        FieldAccess field = expr.getAST().newFieldAccess();
        field.setExpression( expr.getAST().newSimpleName( name ) );
        ( (Assignment) expr ).setLeftHandSide( field );
        return this;
    }

    @Override
    public AssignExpression<O, T> setVariableLeftExpression( String name ) {
        ((Assignment) expr).setLeftHandSide( expr.getAST().newSimpleName( name ) );
        return this;
    }

    @Override
    public ExpressionFactory<O, AssignExpression<O, T>> setLeftExpression() {
        state = State.LEFT;
        return new MockArgumentImpl<O,AssignExpression<O,T>>( this, expr.getAST() );
    }

    @Override
    public ExpressionFactory<O, AssignExpression<O, T>> setRightExpression() {
        state = State.RIGHT;
        return new MockArgumentImpl<O,AssignExpression<O,T>>( this, expr.getAST() );
    }
}
