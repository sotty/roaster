package org.jboss.forge.roaster.model.statements;


import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.InvokeExpression;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.source.MethodSource;

public interface ReturnStatement<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends Statement<O,T> {

    public ReturnStatement<O,T> var( String name );

    public ExpressionFactory<O,ReturnStatement<O,T>> expr();

    public InvokeExpression<O,ReturnStatement<O,T>> invoke();

    public ReturnStatement<O,T> nil();

    public ReturnStatement<O,T> yes();

    public ReturnStatement<O,T> no();
}
