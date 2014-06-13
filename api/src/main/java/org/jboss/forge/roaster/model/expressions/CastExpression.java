package org.jboss.forge.roaster.model.expressions;

import org.jboss.forge.roaster.model.source.JavaSource;

public interface CastExpression<O extends JavaSource<O>, T extends ExpressionSource<O>>
    extends Argument<O,T> {

    public ExpressionFactory<O,CastExpression<O,T>> expr();

    public CastExpression<O,T> as( String type );

    public CastExpression<O,T> as( Class type );
}
