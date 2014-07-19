package org.jboss.forge.roaster.model.impl.expressions;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.jboss.forge.roaster.model.expressions.Expression;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.expressions.ConstructorExpression;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.source.JavaSource;


public class ConstructorImpl<O extends JavaSource<O>, T extends ExpressionSource<O>>
    extends    ArgumentImpl<O,T>
    implements ConstructorExpression<O,T> {

    public ConstructorImpl( T origin, org.eclipse.jdt.core.dom.Expression expr, boolean wireToParent ) {
        super( origin, expr, wireToParent );
    }

    @Override
    public void concatExpression( org.eclipse.jdt.core.dom.Expression parent, org.eclipse.jdt.core.dom.Expression child ) {
        ( (ClassInstanceCreation) parent ).setExpression( child );

    }

    @Override
    public ExpressionFactory<O, ConstructorExpression<O, T>> addArgument() {
        return (ExpressionFactory<O, ConstructorExpression<O, T>>) this;
    }
}
