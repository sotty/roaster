package org.jboss.forge.roaster.model.statements;

import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.DeclareExpression;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface ForStatement<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends Statement<O,T>,
                BlockHolder<O,ForStatement<O,T>> {

    ExpressionFactory<O,ForStatement<O,T>> condition();

    DeclareExpression<O,ForStatement<O,T>> declare();

    ExpressionFactory<O,ForStatement<O,T>> update();

    Block<O,ForStatement<O,T>> repeat();

}
