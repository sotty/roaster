package org.jboss.forge.roaster.model.impl.expressions;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.jboss.forge.roaster.model.expressions.Expression;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.expressions.CastExpression;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.impl.JDTHelper;
import org.jboss.forge.roaster.model.source.JavaSource;

public class CastImpl<O extends JavaSource<O>, T extends ExpressionSource<O>>
        extends ArgumentImpl<O,T>
        implements CastExpression<O,T> {


    public CastImpl( T origin, org.eclipse.jdt.core.dom.Expression expr, boolean wireToParent ) {
        super( origin, expr, wireToParent );
    }

    @Override
    public ExpressionFactory<O,CastExpression<O,T>> expr() {
       return new MockArgumentImpl<O, CastExpression<O,T>>( this, expr.getAST() );
    }

    @Override
    public CastExpression<O, T> as( String type ) {
        ((org.eclipse.jdt.core.dom.CastExpression) expr).setType( JDTHelper.getType( type, ast ) );
        return this;
    }

    @Override
    public CastExpression<O, T> as( Class type ) {
        ((org.eclipse.jdt.core.dom.CastExpression) expr).setType( JDTHelper.getType( type, ast ) );
        return this;
    }

    protected void concatExpression( org.eclipse.jdt.core.dom.Expression parent, org.eclipse.jdt.core.dom.Expression child ) {
        ParenthesizedExpression par = ast.newParenthesizedExpression();
        par.setExpression( child );
        ( (org.eclipse.jdt.core.dom.CastExpression) expr ).setExpression( par );
    }
}
