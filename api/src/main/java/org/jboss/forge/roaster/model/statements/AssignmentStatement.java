/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.statements;


import org.jboss.forge.roaster.model.expressions.Accessor;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.BlockSource;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface AssignmentStatement<O extends JavaSource<O>,
                                     P extends BlockHolder<O>>
        extends ExpressionStatement<O,P,AssignmentStatement<O,P>> {

    public Accessor<O, AssignmentStatement<O, P>, ?> getLeft();

    public AssignmentStatement<O, P> setLeft( Accessor<?, ?, ?> left );


    public ExpressionSource<O, AssignmentStatement<O, P>, ?> getRight();

    public AssignmentStatement<O, P> setRight( ExpressionSource<?, ?, ?> right );

}
