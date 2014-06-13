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

import java.util.List;

public interface SwitchStatement<O extends JavaSource<O>,
                                 P extends BlockHolder<O>>
        extends ControlFlowStatement<O,P,SwitchStatement<O,P>>,
        BodyHolder<O,P,SwitchStatement<O,P>> {

    public List<ExpressionSource<O,SwitchStatement<O,P>,?>> getCases();

    public SwitchStatement<O,P> addCase( ExpressionSource<?,?,?> option );


    public List<StatementSource<O,SwitchStatement<O,P>,?>> getStatements();

    public SwitchStatement<O,P> addDefault();

    public SwitchStatement<O,P> addStatement( StatementSource<?,?,?> arg );


    public ExpressionSource<O,SwitchStatement<O,P>,?> getSwitch();

    public SwitchStatement<O,P> setSwitch( ExpressionSource<?,?,?> expr );

}
