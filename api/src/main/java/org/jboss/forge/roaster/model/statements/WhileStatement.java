package org.jboss.forge.roaster.model.statements;

import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface WhileStatement<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends Statement<O,T>,
                BlockHolder<O,WhileStatement<O,T>> {

    ExpressionFactory<O,WhileStatement<O,T>> setCondition();

    Block<O,WhileStatement<O,T>> setBody();

}
