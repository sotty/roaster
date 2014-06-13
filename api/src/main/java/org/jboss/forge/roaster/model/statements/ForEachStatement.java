/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.statements;

import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.BlockSource;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface ForEachStatement<O extends JavaSource<O>,
                                  P extends BlockHolder<O>>
        extends LoopingStatement<O,P,ForEachStatement<O,P>>,
        BodyHolder<O,P,ForEachStatement<O,P>> {

    public String getIteratorName();

    public String getIteratorType();

    public ForEachStatement<O,P> setIterator( String klass, String name );

    public ForEachStatement<O,P> setIterator( Class klass, String name );


    public ExpressionSource<O,ForEachStatement<O,P>,?> getSource();

    public ForEachStatement<O,P> setSource( ExpressionSource<?,?,?> expr );

}
