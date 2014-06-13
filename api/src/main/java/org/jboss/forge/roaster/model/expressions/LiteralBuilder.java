/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.expressions;


import org.jboss.forge.roaster.model.ExpressionHolder;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface LiteralBuilder<O extends JavaSource<O>,
                                P extends ExpressionHolder<O>> {

    public StringLiteral<O,P> literal( String val );

    public PrimitiveLiteral<O,P> literal( Number val );

    public PrimitiveLiteral<O,P> literal( Character val );

    public PrimitiveLiteral<O,P> literal( Boolean val );

    public Literal<O,P,?> zeroLiteral( String klass );

    public Literal<O,P,?> zeroLiteral( Class<?> klass );

    public ThisLiteral<O,P> thisLiteral();

    public NullLiteral<O,P> nullLiteral();

    public ClassLiteral<O,P> classLiteral( String klass );

    public ClassLiteral<O,P> classLiteral( Class<?> klass );

}
