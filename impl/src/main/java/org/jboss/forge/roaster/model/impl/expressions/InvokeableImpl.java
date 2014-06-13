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
import org.jboss.forge.roaster.model.expressions.Variable;
import org.jboss.forge.roaster.model.source.JavaSource;

public abstract class InvokeableImpl<O extends JavaSource<O>,
                                     P extends ExpressionHolder<O>,
                                     E extends InvokeExpression<O,P,E>,
                                     J extends org.eclipse.jdt.core.dom.Expression>
        extends BaseInvokeableImpl<O,P,E,J>
        implements InvokeExpression<O,P,E> {

    protected ExpressionSource<O,E,?> target;


    public InvokeableImpl( String method ) {
        super( method );
    }

    @Override
    public Accessor<O,P,E> dot() {
        return new DotAccessorImpl<O,P,E>( this );
    }

    public ExpressionSource<O,E,?> chainExpression( ExpressionSource<O, ?, ?> child ) {
        ExpressionSource<O,E,?> cast = ( ExpressionSource<O,E,?> ) child;
        this.target = cast;
        return cast;
    }

}
