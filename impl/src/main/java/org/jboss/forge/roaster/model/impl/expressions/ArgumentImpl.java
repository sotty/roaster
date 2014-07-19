package org.jboss.forge.roaster.model.impl.expressions;

import org.eclipse.jdt.core.dom.AST;
import org.jboss.forge.roaster.model.expressions.Accessor;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.expressions.ExpressionTypes;
import org.jboss.forge.roaster.model.source.JavaSource;


public abstract class ArgumentImpl<O extends JavaSource<O>, T extends ExpressionSource<O>>
    extends ExpressionFactoryImpl<O,T>
    implements Argument<O,T>,
               JdtExpressionWrapper<O,T> {

    protected org.eclipse.jdt.core.dom.Expression expr;
    private ExpressionFactoryImpl<O,T> factory;

    public ArgumentImpl( T origin, org.eclipse.jdt.core.dom.Expression expr, boolean wireToParent ) {
        super( origin, expr.getAST() );
        this.expr = expr;
        if ( wireToParent ) {
            ((JdtExpressionWrapper) origin).wireExpression( this );
        }
    }

    public ArgumentImpl( T origin, AST ast ) {
        super( origin, ast );
    }

    @Override
    public ExpressionFactory<O,T> nextArgument() {
        return ((JdtExpressionWrapper) origin).getBuilder();
    }

    public T noMore() {
        return (T) ((JdtExpressionWrapper) origin).asExpressionSource();
    }

    @Override
    public T getOrigin() {
        return origin;
    }

    @Override
    public org.eclipse.jdt.core.dom.Expression getExpression() {
        return expr;
    }

    public Accessor<O,T> dot() {
        return new DotAccessorImpl<O,T>( origin, this, ast );
    }

    @Override
    public void wireExpression( ExpressionSource<O> child ) {
        concatExpression( expr, ( (JdtExpressionWrapper) child ).getExpression() );
    }

    @Override
    public ExpressionFactory<O,T> getBuilder() {
        return this;
    }

    protected <X extends Argument<O,T>> X subArg( org.eclipse.jdt.core.dom.Expression expr, ExpressionTypes type ) {
        X child = buildArgument( expr, type, true );
        return child;
    }

    protected T getParent() {
        return (T) this;
    }

    @Override
    public ExpressionSource<O> asExpressionSource() {
        return this;
    }

}

