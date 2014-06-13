/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.statements;

import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.BlockSource;
import org.jboss.forge.roaster.model.source.JavaSource;

import java.util.List;

public interface SuperStatement<O extends JavaSource<O>,
                                P extends BlockHolder<O>>
        extends ExpressionStatement<O,P,SuperStatement<O,P>> {

    public List<Argument<O,SuperStatement<O,P>,?>> getArguments();

    public SuperStatement<O,P> addArgument( Argument<?,?,?> argument );

}
