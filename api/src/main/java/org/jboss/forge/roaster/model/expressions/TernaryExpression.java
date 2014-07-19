package org.jboss.forge.roaster.model.expressions;

import org.jboss.forge.roaster.model.source.JavaSource;

public interface TernaryExpression<O extends JavaSource<O>, T extends ExpressionSource<O>>
    extends Argument<O,T> {

    public ExpressionFactory<O,TernaryExpression<O,T>> setCondition();

    public ExpressionFactory<O,TernaryExpression<O,T>> setIfExpression();

    public ExpressionFactory<O,TernaryExpression<O,T>> setElseExpression();

    public T end();

}
