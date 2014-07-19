package org.jboss.forge.roaster.model.expressions;


import org.jboss.forge.roaster.Origin;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface OperatorExpression<O extends JavaSource<O>, T extends ExpressionSource<O>>
    extends Argument<O,T>,
            Origin<T> {

    public ExpressionFactory<O,OperatorExpression<O,T>> addArgument();
}
