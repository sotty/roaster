package org.jboss.forge.roaster.model.impl.expressions;

import org.eclipse.jdt.core.dom.Block;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface JdtBlockWrapper<O extends JavaSource<O>,T> {

    public Block getBlock();

}