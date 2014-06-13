/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.statements;

import org.jboss.forge.roaster.model.ExpressionHolder;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.BlockSource;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface CallStatement<O extends JavaSource<O>,
                               P extends BlockHolder<O>>
        extends ExpressionStatement<O,P,CallStatement<O,P>> {

    public ExpressionSource<O,CallStatement<O,P>,?> getExpr();

    public CallStatement<O,P>  setExpr( ExpressionSource<O,?,?> expr );

}


