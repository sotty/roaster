package org.jboss.forge.roaster.model.expressions;


import org.jboss.forge.roaster.model.source.JavaSource;

public interface LiteralBuilder<O extends JavaSource<O>, T extends ExpressionSource<O>> {

    public Literal<O,T> literal( String val );

    public Literal<O,T> literal( Number val );

    public Literal<O,T> literal( Character val );

    public Literal<O,T> literal( Class<?> val );

    public Literal<O,T> literal( Boolean val );

    public Argument<O,T> zero( String klass );

    public Argument<O,T> zero( Class<?> klass );

    public Argument<O,T> self();

    public Argument<O,T> nil();

    public Argument<O,T> klass( String klass );

    public Argument<O,T> klass( Class<?> klass );

}
