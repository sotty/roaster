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

public interface ThisStatement<O extends JavaSource<O>,
                               P extends BlockHolder<O>>
        extends ExpressionStatement<O,P,ThisStatement<O,P>> {

    public List<Argument<O,ThisStatement<O,P>,?>> getArguments();

    public ThisStatement<O,P> addArgument( Argument<?,?,?> argument );

}
