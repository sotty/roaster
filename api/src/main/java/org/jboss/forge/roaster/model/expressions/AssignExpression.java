package org.jboss.forge.roaster.model.expressions;

import org.jboss.forge.roaster.model.source.JavaSource;

public interface AssignExpression<O extends JavaSource<O>, T extends ExpressionSource<O>>
    extends ExpressionSource<O>,
            Argument<O,T> {

    public T setVariableRightExpression( String name );

    public AssignExpression<O,T> setFieldLeftExpression( String name );

    public AssignExpression<O,T> setVariableLeftExpression( String name );

    public ExpressionFactory<O,AssignExpression<O,T>> setLeftExpression();

    public ExpressionFactory<O,AssignExpression<O,T>> setRightExpression();
}
