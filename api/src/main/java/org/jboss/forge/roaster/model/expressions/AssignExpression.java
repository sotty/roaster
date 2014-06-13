package org.jboss.forge.roaster.model.expressions;

import org.jboss.forge.roaster.model.source.JavaSource;

public interface AssignExpression<O extends JavaSource<O>, T extends ExpressionSource<O>>
    extends ExpressionSource<O>,
            Argument<O,T> {

    public AssignExpression<O,T> assignOp( String op );

    public ExpressionFactory<O,AssignExpression<O,T>> to();

    public ExpressionFactory<O,AssignExpression<O,T>> expr();
}
