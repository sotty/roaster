/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.statements;

import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.Accessor;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.BlockSource;
import org.jboss.forge.roaster.model.source.JavaSource;

import java.util.List;

public interface InvokeStatement<O extends JavaSource<O>,
                                 P extends BlockHolder<O>>
        extends ExpressionStatement<O,P,InvokeStatement<O,P>> {

    public String getMethod();

    public InvokeStatement<O,P> setMethod( String method );


    public Accessor<O,InvokeStatement<O,P>,?> getTarget();

    public InvokeStatement<O,P> setTarget( Accessor<?,?,?> target );


    public List<Argument<O,InvokeStatement<O,P>,?>> getArguments();

    public InvokeStatement<O,P> addArgument( Argument<?,?,?> argument );

}
