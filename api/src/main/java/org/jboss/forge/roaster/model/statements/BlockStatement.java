package org.jboss.forge.roaster.model.statements;

import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface BlockStatement<O extends JavaSource<O>, T extends Block<O,BlockHolder<O,?>>>
        extends Statement<O,T> {


}
