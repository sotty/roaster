package org.jboss.forge.roaster.model.statements;

import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.DeclareExpression;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface ForEachStatement<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends Statement<O,T>,
                BlockHolder<O,ForEachStatement<O,T>> {

    ForEachStatement<O,T> iterator( String name, Class type );

    ForEachStatement<O,T> iterator( String name, String type );

    ExpressionFactory<O,ForEachStatement<O,T>> in();

    Block<O,ForEachStatement<O,T>> repeat();

}
