package org.jboss.forge.roaster.model.expressions;


import org.jboss.forge.roaster.model.source.JavaSource;

public interface Variable<O extends JavaSource<O>, T extends ExpressionSource<O>>
    extends Argument<O,T> {

    public T inc();

    public T dec();

}
