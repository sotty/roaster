package org.jboss.forge.roaster.model.impl.expressions;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.expressions.Expression;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.expressions.InvokeExpression;
import org.jboss.forge.roaster.model.impl.JDTHelper;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.statements.InvokeStatement;

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
    public ExpressionFactory<O,InvokeExpression<O,T>> on() {
        state = State.ON;
        MethodInvocation invoke = ( MethodInvocation ) expr;
        Argument<O,InvokeExpression<O,T>> onExp = new MockArgumentImpl<O,InvokeExpression<O,T>>( this, invoke.getAST() );
        return (ExpressionFactory<O, InvokeExpression<O,T>>) onExp;
    }

    @Override
    public InvokeExpression<O, T> onVariable( String varName ) {
        ( (MethodInvocation) expr ).setExpression( ast.newSimpleName( varName ) );
        return this;
    }

    @Override
    public InvokeExpression<O, T> method( String methodName ) {
        (( MethodInvocation) expr ).setName( ast.newSimpleName( methodName ) );
        return this;
    }

    @Override
    public InvokeExpression<O, T> staticMethod( String methodName, Class klass ) {
        (( MethodInvocation) expr ).setName( ast.newSimpleName( methodName ) );
        (( MethodInvocation) expr ).setExpression( ast.newName( JDTHelper.getTypeName( JDTHelper.getType( klass, expr.getAST() ) ) ) );
        return this;
    }

    @Override
    public InvokeExpression<O, T> staticMethod( String methodName, String klass ) {
        (( MethodInvocation) expr ).setName( ast.newSimpleName( methodName ) );
        (( MethodInvocation) expr ).setExpression( ast.newName( JDTHelper.getTypeName( JDTHelper.getType( klass, expr.getAST() ) ) ) );
        return this;
    }



    public ExpressionFactory<O,InvokeExpression<O,T>> addArgument() {
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
