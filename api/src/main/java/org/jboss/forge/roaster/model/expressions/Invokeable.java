package org.jboss.forge.roaster.model.expressions;

import org.jboss.forge.roaster.model.source.JavaSource;

public interface Invokeable<O extends JavaSource<O>, T, I extends Invokeable<O,T,?>>
    extends ExpressionSource<O> {

    public I on( String ref );

    public I on( Class ref );

    public ExpressionFactory<O,I> on();

    public I method( String methodName );

}
