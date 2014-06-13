package org.jboss.forge.roaster.model.impl;

import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.statements.Statement;
import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.source.MethodSource;

public abstract class StatementImpl<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        implements Statement<O,T> {

    private T origin;

    public StatementImpl( T origin ) {
        this.origin = origin;
    }

    @Override
    public T getOrigin() {
        return origin;
    }

    @Override
    public T done() {
        return origin;
    }

    public abstract org.eclipse.jdt.core.dom.Statement getStatement();

    public ExpressionSource<O> asExpressionSource() {
        return this;
    }

}
