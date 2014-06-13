/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.expressions;


import org.jboss.forge.roaster.model.ExpressionHolder;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface ArrayInit<O extends JavaSource<O>,P extends ExpressionHolder<O>>
    extends ExpressionSource<O,P,ArrayInit<O,P>>,
        NonPrimitiveExpression<O,P,ArrayInit<O,P>> {

    public ArrayInit<O,P> addElement( ArrayInit<?,?> subRow );

    public ArrayInit<O,P> addElement( ExpressionSource<?,?,?> subElement );

    public int size();

}
