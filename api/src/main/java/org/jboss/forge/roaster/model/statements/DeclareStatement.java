/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.statements;


import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.BlockSource;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface DeclareStatement<O extends JavaSource<O>,
                                  P extends BlockHolder<O>>
        extends StatementSource<O,P,DeclareStatement<O,P>> {

    public String getVariableName();

    public String getVariableType();

    public DeclareStatement<O,P> setVariable( Class klass, String name );

    public DeclareStatement<O,P> setVariable( String klass, String name );


    public ExpressionSource<O,DeclareStatement<O,P>,?> getInitExpression();

    public DeclareStatement<O,P> setInitExpression( ExpressionSource<?,?,?> init );

    public DeclareStatement<O,P> setDefaultInitExpression();

}