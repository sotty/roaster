/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.expressions;

import org.jboss.forge.roaster.model.ExpressionHolder;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface InstanceofExpression<O extends JavaSource<O>,
                                      P extends ExpressionHolder<O>>
        extends Argument<O,P,InstanceofExpression<O,P>>,
        NonPrimitiveExpression<O,P,InstanceofExpression<O,P>> {

    public String getTypeName();

    public ExpressionSource<O,InstanceofExpression<O,P>,?> getExpression();
}
