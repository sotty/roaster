package org.jboss.forge.roaster.model.impl.expressions;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.expressions.UnaryExpression;
import org.jboss.forge.roaster.model.source.JavaSource;

public class UnaryImpl<O extends JavaSource<O>, T extends ExpressionSource<O>>
        extends ArgumentImpl<O,T>
        implements UnaryExpression<O,T>,
                   JdtExpressionWrapper<O,T> {

    public UnaryImpl( T origin, Expression expr, boolean wireToParent ) {
        super( origin, expr, wireToParent );
    }

    public UnaryImpl( T origin, AST ast ) {
        super( origin, ast );
    }

    @Override
    protected void concatExpression( Expression parent, Expression child ) {
        (( PrefixExpression) parent).setOperand( child );
    }

    @Override
    public ExpressionSource<O> asExpressionSource() {
        return ((JdtExpressionWrapper) origin).asExpressionSource();
    }

}
