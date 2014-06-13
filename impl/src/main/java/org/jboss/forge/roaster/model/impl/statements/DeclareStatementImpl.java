/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.impl.statements;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.jboss.forge.roaster.model.expressions.BasicExpressionFactory;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.impl.JDTHelper;
import org.jboss.forge.roaster.model.impl.expressions.BasicExpressionFactoryImpl;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.statements.DeclareStatement;

public class DeclareStatementImpl<O extends JavaSource<O>, 
                                  P extends BlockHolder<O>>
        extends StatementImpl<O,P,DeclareStatement<O,P>,VariableDeclarationStatement>
        implements DeclareStatement<O,P> {

    private VariableDeclarationStatement var;

    private BasicExpressionFactory<O,DeclareStatement<O,P>> factory = new BasicExpressionFactoryImpl<O,DeclareStatement<O,P>, org.eclipse.jdt.core.dom.Expression>();

    private ExpressionSource<O,DeclareStatement<O,P>,?> initializer;
    private String name;
    private String type;

    public DeclareStatementImpl() { }

    @Override
    public String getVariableName() {
        return name;
    }

    @Override
    public String getVariableType() {
        return type;
    }

    @Override
    public DeclareStatement<O,P> setVariable( Class klass, String name ) {
        return setVariable( klass.getName(), name );
    }

    public DeclareStatement<O,P> setVariable( String type, String name ) {
        this.name = name;
        this.type = type;
        return this;
    }

    @Override
    public ExpressionSource<O, DeclareStatement<O, P>, ?> getInitExpression() {
        return initializer;
    }

    @Override
    public DeclareStatement<O,P> setInitExpression( ExpressionSource<?,?,?> init ) {
        ExpressionSource<O,DeclareStatement<O,P>,?> cast = (ExpressionSource<O, DeclareStatement<O, P>, ?>) init;
        this.initializer = cast;
        return this;
    }

    @Override
    public DeclareStatement<O,P> setDefaultInitExpression() {
        setInitExpression( factory.zeroLiteral( type ) );
        return this;
    }

    public VariableDeclarationFragment getMainVar() {
        return (VariableDeclarationFragment) var.fragments().iterator().next();
    }

    @Override
    public VariableDeclarationStatement materialize( AST ast ) {
        if (var != null) {
            return var;
        }
        var = ast.newVariableDeclarationStatement( ast.newVariableDeclarationFragment() );

        var.setType( JDTHelper.getType( type, ast ) );
        getMainVar().setName( ast.newSimpleName( name ) );

        if ( initializer != null ) {
            getMainVar().setInitializer( wireAndGetExpression( initializer, this, ast ) );
        }
        return var;
    }

    @Override
    public VariableDeclarationStatement getInternal() {
        return var;
    }

    @Override
    public void setInternal(VariableDeclarationStatement jdtNode) {
        super.setInternal(jdtNode);
        this.var = jdtNode;
    }
}
