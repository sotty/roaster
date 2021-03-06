/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.expressions;

import org.jboss.forge.roaster.model.ExpressionHolder;
import org.jboss.forge.roaster.model.source.JavaSource;

import java.util.List;

public interface ArgumentHolder<O extends JavaSource<O>,
                                P extends ExpressionHolder<O>,
                                E extends NonPrimitiveExpression<O,P,?>>
    extends ExpressionHolder<O> {

    public E addArgument( Argument<?,?,?> arg );

    public List<Argument<O,E,?>> getArguments();
}
