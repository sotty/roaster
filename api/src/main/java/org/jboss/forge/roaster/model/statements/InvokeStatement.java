package org.jboss.forge.roaster.model.statements;

import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.Invokeable;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.source.MethodSource;

public interface InvokeStatement<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends Statement<O,T>,
        Invokeable<O,T,InvokeStatement<O,T>> {

    public ExpressionFactory<O,InvokeStatement<O,T>> addArgument();

}
