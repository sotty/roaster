package org.jboss.forge.roaster.model.impl.expressions;


import org.eclipse.jdt.core.dom.Expression;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface JdtExpressionWrapper<O extends JavaSource<O>,T> {

    public Expression getExpression();

    public void wireExpression( ExpressionSource<O> child );

    <T extends ExpressionSource<O>> ExpressionFactory<O,T> getBuilder();

    public ExpressionSource<O> asExpressionSource();

}
