package org.jboss.forge.roaster.model.expressions;

import org.jboss.forge.roaster.model.source.JavaSource;

public interface DeclareExpression<O extends JavaSource<O>, T extends ExpressionSource<O>>
    extends ExpressionSource<O> {

    public DeclareExpression<O,T> setVariable( String name, Class type );

    public DeclareExpression<O,T> setVariable( String name, String type );

    public ExpressionFactory<O,T> init();

}
