/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.expressions;


import org.jboss.forge.roaster.model.ExpressionHolder;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface AccessBuilder<O extends JavaSource<O>,
                               P extends ExpressionHolder<O>,
                               E extends NonPrimitiveExpression<O,P,E>> {

    public Field<O,E> field( String field );

    public Getter<O,E> getter( String field, String klass );

    public Getter<O,E> getter( String field, Class klass );

    public Setter<O,E> setter( String fldName, String type, ExpressionSource<?,?,?> value );

    public Setter<O,E> setter( String fldName, Class type, ExpressionSource<?,?,?> value );

    public MethodCallExpression<O,E> invoke( String method );

    public ArrayIndexer<O,E> itemAt( ExpressionSource<?,?,?> index );

}
