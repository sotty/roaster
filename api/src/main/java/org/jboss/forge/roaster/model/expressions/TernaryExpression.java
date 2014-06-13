/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.expressions;

import org.jboss.forge.roaster.model.ExpressionHolder;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface TernaryExpression<O extends JavaSource<O>,
                                   P extends ExpressionHolder<O>>
        extends Argument<O,P,TernaryExpression<O,P>>,
        NonPrimitiveExpression<O,P,TernaryExpression<O,P>> {

    public ExpressionSource<O,TernaryExpression<O,P>,?> getCondition();

    public TernaryExpression<O,P> setCondition( ExpressionSource<?,?,?> expression );


    public ExpressionSource<O,TernaryExpression<O,P>,?> getThenExpression();

    public TernaryExpression<O,P> setThenExpression( ExpressionSource<?,?,?> onTrue );


    public ExpressionSource<O,TernaryExpression<O,P>,?> getElseExpression();

    public TernaryExpression<O,P> setElseExpression( ExpressionSource<?,?,?> onFalse );



}
