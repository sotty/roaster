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

public interface IfStatement<O extends JavaSource<O>,
                             P extends BlockHolder<O>>
        extends ControlFlowStatement<O,P,IfStatement<O,P>>,
        BodyHolder<O,P,IfStatement<O,P>> {

    public ExpressionSource<O,IfStatement<O,P>,?> getCondition();

    public IfStatement<O,P> setCondition( ExpressionSource<?,?,?> condition );


    public BlockStatement<O,IfStatement<O,P>> getThen();

    public IfStatement<O,P> setThen( StatementSource<?,?,?> ifBlock );

    public IfStatement<O,P> setThen( BlockSource<?,?,?> ifBlock );


    public BlockStatement<O,IfStatement<O,P>> getElse();

    public IfStatement<O,P> setElse( StatementSource<?,?,?> elseBlock );

    public IfStatement<O,P> setElse( BlockSource<?,?,?> elseBlock );

}
