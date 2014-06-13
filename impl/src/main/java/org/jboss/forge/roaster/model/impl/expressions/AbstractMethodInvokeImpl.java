/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.impl.expressions;

import org.jboss.forge.roaster.model.ExpressionHolder;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.expressions.Expression;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.expressions.InvokeExpression;
import org.jboss.forge.roaster.model.expressions.MethodCallExpression;
import org.jboss.forge.roaster.model.expressions.MethodExpression;
import org.jboss.forge.roaster.model.source.JavaSource;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractMethodInvokeImpl<O extends JavaSource<O>,
                                               P extends ExpressionHolder<O>,
                                               M extends MethodExpression<O,P,M>,
                                               J extends org.eclipse.jdt.core.dom.Expression>
        extends InvokeableImpl<O,P,M,J>
        implements MethodExpression<O,P,M> {

    protected List<Argument<O,M,?>> arguments = Collections.EMPTY_LIST;

    public AbstractMethodInvokeImpl( String method ) {
        super( method );
    }

    @Override
    public M addArgument( Argument<?,?,?> argument ) {
        if ( arguments.isEmpty() ) {
            arguments = new LinkedList<Argument<O,M,?>>();
        }
        arguments.add( (Argument<O, M, ?>) argument );
        return (M) this;
    }

    @Override
    public M setTarget( ExpressionSource<?,?,?> target ) {
        this.target = (ExpressionSource<O, M, ?>) target;
        return (M) this;
    }

    @Override
    public List<Argument<O, M, ?>> getArguments() {
        return arguments;
    }
}
