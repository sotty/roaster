/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.expressions;

import org.jboss.forge.roaster.model.ExpressionHolder;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface CastExpression<O extends JavaSource<O>,
                                P extends ExpressionHolder<O>>
        extends Argument<O,P,CastExpression<O,P>>,
        NonPrimitiveExpression<O,P,CastExpression<O,P>> {

    public String getType();

    public ExpressionSource<O,CastExpression<O,P>,?> getExpression();
}
