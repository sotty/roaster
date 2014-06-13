package org.jboss.forge.roaster.model.impl.expressions;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.expressions.Expression;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.expressions.InvokeExpression;
import org.jboss.forge.roaster.model.source.JavaSource;

public class InvokeableImpl<O extends JavaSource<O>, T extends ExpressionSource<O>>
        extends ArgumentImpl<O,T>
        implements InvokeExpression<O, T> {

    private enum State { ON, ARGS }
    private State state;
    private Argument<O,InvokeExpression<O,T>> argsRoot;

    public InvokeableImpl( T origin, org.eclipse.jdt.core.dom.Expression expr, boolean wireToParent ) {
        super( origin, expr, wireToParent );
    }

    @Override
    public InvokeExpression<O, T> on( String ref ) {
        (( MethodInvocation) expr ).setExpression( ast.newName( ref ) );
        return this;
    }

    @Override
    public InvokeExpression<O, T> on( Class ref ) {
        return on( ref.getName() );
    }

    @Override
    public ExpressionFactory<O,InvokeExpression<O,T>> on() {
        state = State.ON;
        MethodInvocation invoke = ( MethodInvocation ) expr;
        Argument<O,InvokeExpression<O,T>> onExp = new MockArgumentImpl<O,InvokeExpression<O,T>>( this, invoke.getAST() );
        return (ExpressionFactory<O, InvokeExpression<O,T>>) onExp;
    }

    @Override
    public InvokeExpression<O, T> method( String methodName ) {
        (( MethodInvocation) expr ).setName( ast.newSimpleName( methodName ) );
        return this;
    }

    public ExpressionFactory<O,InvokeExpression<O,T>> args() {
        state = State.ARGS;
        argsRoot = new MockArgumentImpl<O, InvokeExpression<O, T>>( this, expr.getAST() );
        return (ExpressionFactory<O,InvokeExpression<O,T>>) argsRoot;
    }

    protected void concatExpression( org.eclipse.jdt.core.dom.Expression parent, org.eclipse.jdt.core.dom.Expression child ) {
        switch ( state ) {
            case ON : ( (MethodInvocation) parent).setExpression( child ); break;
            case ARGS:
                default:
                    ((MethodInvocation) parent).arguments().add( child );
        }
    }

    @Override
    public ExpressionFactory<O,T> getBuilder() {
        switch ( state ) {
            case ON : return (ExpressionFactory<O,T>) ((MethodInvocation) expr).getExpression();
            case ARGS:
                default:
                    return (ExpressionFactory<O,T>) argsRoot;
        }
    }
}
