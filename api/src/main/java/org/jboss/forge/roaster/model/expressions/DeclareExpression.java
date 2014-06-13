package org.jboss.forge.roaster.model.expressions;

import org.jboss.forge.roaster.model.source.JavaSource;

public interface DeclareExpression<O extends JavaSource<O>, T extends ExpressionSource<O>>
    extends ExpressionSource<O> {

    public DeclareExpression<O,T> name( String name );

    public DeclareExpression<O,T> type( Class<?> klass );

    public DeclareExpression<O,T> type( String klass );

    public ExpressionFactory<O,T> init();

}
