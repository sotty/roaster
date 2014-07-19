package org.jboss.forge.roaster.model.statements;


import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.InvokeExpression;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.source.MethodSource;

public interface ReturnStatement<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends Statement<O,T> {

    public ExpressionFactory<O,ReturnStatement<O,T>> setReturnExpression();

    public T variable( String name );

    public InvokeExpression<O,ReturnStatement<O,T>> invoke();

    public T nullLiteral();

    public T trueLiteral();

    public T falseLiteral();
}
