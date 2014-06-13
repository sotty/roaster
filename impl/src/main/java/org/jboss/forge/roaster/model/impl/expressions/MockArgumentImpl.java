package org.jboss.forge.roaster.model.impl.expressions;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.Expression;
import org.jboss.forge.roaster.model.expressions.ConstructorExpression;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.source.JavaSource;


public class MockArgumentImpl<O extends JavaSource<O>, T extends ExpressionSource<O>>
    extends ArgumentImpl<O,T> {

    public MockArgumentImpl( T origin, AST ast ) {
        super( origin, ast );
    }

    @Override
    protected T getParent() {
        return (T) this;
    }

    @Override
    protected void concatExpression( Expression parent, Expression child ) {
        throw new IllegalStateException( "Literal expressions can't have children, but a child node was attempted : " + child );
    }

    @Override
    public void wireExpression( ExpressionSource<O> child ) {
        ((JdtExpressionWrapper) origin).wireExpression( child );
    }

    @Override
    public ExpressionSource<O> asExpressionSource() {
        return ((JdtExpressionWrapper) origin).asExpressionSource();
    }

}
