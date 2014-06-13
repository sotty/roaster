/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.expressions;

import org.jboss.forge.roaster.Origin;
import org.jboss.forge.roaster.model.ExpressionHolder;
import org.jboss.forge.roaster.model.JavaType;

public interface Expression<O extends JavaType<O>,
                            P extends ExpressionHolder<O>>
        extends Origin<P> {

        public void setOrigin( P origin );
}
