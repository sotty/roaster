package org.jboss.forge.roaster.model.impl.expressions;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Expression;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.expressions.Literal;
import org.jboss.forge.roaster.model.source.JavaSource;

public class LiteralImpl<O extends JavaSource<O>, T extends ExpressionSource<O>>
        extends    ArgumentImpl<O,T>
        implements Literal<O,T>,
                   JdtExpressionWrapper<O,T> {

    public LiteralImpl( T origin, Expression expr, boolean wireToParent ) {
        super( origin, expr, wireToParent );
    }

    public LiteralImpl( T origin, AST ast ) {
        super( origin, ast );
    }

    @Override
    protected void concatExpression( Expression parent, Expression child ) {
        throw new IllegalStateException( "Literal expressions can't have children, but a child node was attempted : " + child );
    }

}
