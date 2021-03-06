/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.impl.expressions;

import org.eclipse.jdt.core.dom.AST;
import org.jboss.forge.roaster.model.ExpressionHolder;
import org.jboss.forge.roaster.model.expressions.Expression;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.impl.NodeImpl;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.spi.ExpressionFactoryImpl;

public abstract class ExpressionImpl<O extends JavaSource<O>,
                                     P extends ExpressionHolder<O>,
                                     E extends ExpressionSource<O,P,E>,
                                     J extends org.eclipse.jdt.core.dom.Expression>
    extends NodeImpl<O,P,J>
    implements ExpressionSource<O,P,E>  {

    private ExpressionHolder<O> parent;

    public ExpressionImpl() { }

    public ExpressionHolder<O> getParent() {
        return parent;
    }

    public void wireParent( ExpressionHolder<O> parent ) {
        this.parent = parent;
    }

}
