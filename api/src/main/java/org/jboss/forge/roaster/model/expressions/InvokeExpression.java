package org.jboss.forge.roaster.model.expressions;

import org.jboss.forge.roaster.model.source.JavaSource;

public interface InvokeExpression<O extends JavaSource<O>, T extends ExpressionSource<O>>
    extends Argument<O,T>,
            Invokeable<O,T,InvokeExpression<O,T>> {

    public ExpressionFactory<O,InvokeExpression<O,T>> args();
}
