/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.statements;

import org.jboss.forge.roaster.model.expressions.DeclareExpression;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.BlockSource;
import org.jboss.forge.roaster.model.source.JavaSource;

import java.util.List;

public interface ForStatement<O extends JavaSource<O>,
                              P extends BlockHolder<O>>
        extends LoopingStatement<O,P,ForStatement<O,P>>,
        BodyHolder<O,P,ForStatement<O,P>> {

    public List<DeclareExpression<O,ForStatement<O,P>>> getDeclarations();

    public ForStatement<O,P> addDeclaration( DeclareExpression<?,?> declaration );


    public ExpressionSource<O,ForStatement<O,P>,?> getCondition();

    public ForStatement<O,P> setCondition( ExpressionSource<?,?,?> booleanExpression );


    public List<ExpressionSource<O,ForStatement<O,P>,?>> getUpdates();

    public ForStatement<O,P> addUpdate( ExpressionSource<?,?,?> expression );

}
