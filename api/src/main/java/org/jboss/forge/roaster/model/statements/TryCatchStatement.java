/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.statements;

import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.DeclareExpression;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.BlockSource;
import org.jboss.forge.roaster.model.source.JavaSource;

import java.util.Map;

public interface TryCatchStatement<O extends JavaSource<O>,
                                   P extends BlockHolder<O>>
        extends ControlFlowStatement<O,P,TryCatchStatement<O,P>>,
        BodyHolder<O,P,TryCatchStatement<O,P>> {

    public Map<DeclareExpression<O,TryCatchStatement<O,P>>,BlockStatement<O,TryCatchStatement<O,P>>> getCatches();

    public TryCatchStatement<O,P> addCatch( DeclareExpression<?,?> declaration,
                                            BlockStatement<?,?> block );

    public TryCatchStatement<O,P> addCatch( DeclareExpression<?,?> declaration,
                                            StatementSource<?,?,?> block );


    public BlockStatement<O,TryCatchStatement<O,P>> getFinally();

    public TryCatchStatement<O,P> setFinally( BlockStatement<?,?> block );

    public TryCatchStatement<O,P> setFinally( StatementSource<?,?,?> block );

}
