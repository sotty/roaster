package org.jboss.forge.roaster.model.expressions;


import org.jboss.forge.roaster.Origin;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface Argument<O extends JavaSource<O>, T extends ExpressionSource<O>>
    extends Expression<O,T>,
            Origin<T> {

    public ExpressionFactory<O, T> nextArgument();

    public T noMore();

}
