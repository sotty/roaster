package org.jboss.forge.roaster.model.statements;


import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.source.MethodSource;

public interface AssignStatement<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends Statement<O,T> {

    public ExpressionFactory<O,AssignStatement<O,T>> setLeftExpression();

    public ExpressionFactory<O,AssignStatement<O,T>> setRightExpression();

    public AssignStatement<O,T> setFieldLeftExpression( String name );

    public AssignStatement<O,T> setVariableLeftExpression( String name );

    public T setVariableRightExpression( String name );

}
