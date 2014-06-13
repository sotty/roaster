/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.expressions;

import org.jboss.forge.roaster.model.ExpressionHolder;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface MethodExpression<O extends JavaSource<O>,
                                  P extends ExpressionHolder<O>,
                                  M extends MethodExpression<O,P,M>>
        extends InvokeExpression<O,P,M>,
        ArgumentHolder<O,P,M>,
        NonPrimitiveExpression<O,P,M> {

    public M setTarget( ExpressionSource<?,?,?> target );

    public M addArgument( Argument<?,?,?> argument );
}
