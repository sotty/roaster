package org.jboss.forge.roaster.model.expressions;

import org.jboss.forge.roaster.model.JavaType;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface UnaryExpression<O extends JavaSource<O>, T extends ExpressionSource<O>>
    extends ExpressionFactory<O,T> {

}
