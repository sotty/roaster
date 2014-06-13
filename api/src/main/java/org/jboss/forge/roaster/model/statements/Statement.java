package org.jboss.forge.roaster.model.statements;


import org.jboss.forge.roaster.Origin;
import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.source.MethodSource;

public interface Statement<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends Origin<T>,
                ExpressionSource<O> {

    public T done();
}
