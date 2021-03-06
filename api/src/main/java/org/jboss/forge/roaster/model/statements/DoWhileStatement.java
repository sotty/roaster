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

public interface DoWhileStatement<O extends JavaSource<O>,
                                  P extends BlockHolder<O>>
        extends LoopingStatement<O,P,DoWhileStatement<O,P>>,
                BodyHolder<O,P,DoWhileStatement<O,P>> {

    public ExpressionSource<O,DoWhileStatement<O,P>,?> getCondition();

    public DoWhileStatement<O,P> setCondition( ExpressionSource<?,?,?> expr );

}
