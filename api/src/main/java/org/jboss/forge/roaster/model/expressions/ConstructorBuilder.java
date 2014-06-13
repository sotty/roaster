/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.expressions;

import org.jboss.forge.roaster.model.ExpressionHolder;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface ConstructorBuilder<O extends JavaSource<O>,
                                    P extends ExpressionHolder<O>> {

    public ConstructorExpression<O,P> newInstance( String klass );

    public ConstructorExpression<O,P> newInstance( Class<?> klass );

    public ArrayConstructorExpression<O,P> newArray( Class<?> klass );

    public ArrayConstructorExpression<O,P> newArray( String klass );

}
