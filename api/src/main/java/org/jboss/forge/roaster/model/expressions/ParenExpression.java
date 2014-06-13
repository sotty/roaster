package org.jboss.forge.roaster.model.expressions;

import org.jboss.forge.roaster.model.source.JavaSource;

public interface ParenExpression<O extends JavaSource<O>, T extends ExpressionSource<O>>
    extends ExpressionFactory<O,T>,
            ExpressionSource<O> {

    public Argument<O,T> close();

}
