/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.impl.expressions;

import org.jboss.forge.roaster.model.ExpressionHolder;
import org.jboss.forge.roaster.model.expressions.Accessor;
import org.jboss.forge.roaster.model.expressions.Expression;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.expressions.InvokeExpression;
import org.jboss.forge.roaster.model.source.JavaSource;

public abstract class BaseInvokeableImpl<O extends JavaSource<O>,
                                         P extends ExpressionHolder<O>,
                                         E extends InvokeExpression<O,P,E>,
                                         J extends org.eclipse.jdt.core.dom.Expression>
        extends AccessorImpl<O,P,E,J>
        implements InvokeExpression<O,P,E> {

    protected String method;

    public BaseInvokeableImpl( String method ) {
        this.method = method;
    }

}
