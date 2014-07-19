package org.jboss.forge.roaster.model.statements;

import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.DeclareExpression;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface ForStatement<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends Statement<O,T>,
                BlockHolder<O,ForStatement<O,T>> {

    ExpressionFactory<O,ForStatement<O,T>> setCondition();

    DeclareExpression<O,ForStatement<O,T>> addDeclaration();

    ExpressionFactory<O,ForStatement<O,T>> addUpdate();

    Block<O,ForStatement<O,T>> setBody();

}
