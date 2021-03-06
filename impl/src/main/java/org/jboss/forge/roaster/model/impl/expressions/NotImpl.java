/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.impl.expressions;

import org.jboss.forge.roaster.model.ExpressionHolder;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.expressions.PrefixOp;
import org.jboss.forge.roaster.model.expressions.UnaryExpression;
import org.jboss.forge.roaster.model.source.JavaSource;

public class NotImpl<O extends JavaSource<O>,
                     P extends ExpressionHolder<O>>
        extends UnaryImpl<O,P> {

    public NotImpl( Argument<O,?,?> expr ) {
        super( PrefixOp.NOT, expr );
    }

}
