/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.impl.statements;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.DeclareExpression;
import org.jboss.forge.roaster.model.expressions.Expression;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.BlockSource;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.statements.BlockStatement;
import org.jboss.forge.roaster.model.statements.ForStatement;
import org.jboss.forge.roaster.model.statements.Statement;
import org.jboss.forge.roaster.model.statements.StatementSource;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ForStatementImpl<O extends JavaSource<O>,
                              P extends BlockHolder<O>>
        extends BodiedStatementImpl<O,P,ForStatement<O,P>,org.eclipse.jdt.core.dom.ForStatement>
        implements ForStatement<O,P> {

    private org.eclipse.jdt.core.dom.ForStatement iter;

    private List<DeclareExpression<O,ForStatement<O,P>>> declarations = Collections.EMPTY_LIST;
    private List<ExpressionSource<O,ForStatement<O,P>,?>> updaters = Collections.EMPTY_LIST;
    private ExpressionSource<O,ForStatement<O,P>,?> condition;
    private BlockStatement<O,ForStatement<O,P>> body;

    public ForStatementImpl() { }

    @Override
    public List<DeclareExpression<O, ForStatement<O, P>>> getDeclarations() {
        return declarations;
    }

    @Override
    public ForStatement<O,P> addDeclaration( DeclareExpression<?,?> declaration ) {
        DeclareExpression<O,ForStatement<O,P>> cast = (DeclareExpression<O, ForStatement<O, P>>) declaration;
        if ( declarations.isEmpty() ) {
            declarations = new LinkedList<DeclareExpression<O,ForStatement<O,P>>>();
        }
        declarations.add( cast );
        return this;
    }

    @Override
    public ExpressionSource<O, ForStatement<O, P>, ?> getCondition() {
        return condition;
    }

    @Override
    public ForStatement<O,P> addUpdate( ExpressionSource<?,?,?> expression ) {
        ExpressionSource<O,ForStatement<O,P>,?> cast = (ExpressionSource<O, ForStatement<O, P>, ?>) expression;
        if ( updaters.isEmpty() ) {
            updaters = new LinkedList<ExpressionSource<O,ForStatement<O,P>,?>>();
        }
        updaters.add( cast );
        return this;
    }

    @Override
    public ForStatement<O,P> setCondition( ExpressionSource<?,?,?> booleanExpression ) {
        ExpressionSource<O,ForStatement<O,P>,?> cast = (ExpressionSource<O, ForStatement<O, P>, ?>) booleanExpression;
        this.condition = cast;
        return this;
    }

    @Override
    public List<ExpressionSource<O, ForStatement<O, P>, ?>> getUpdates() {
        return updaters;
    }

    @Override
    public BlockStatement<O, ForStatement<O, P>> getBody() {
        return body;
    }

    @Override
    public ForStatement<O,P> setBody( BlockSource<?,?,?> body ) {
        BlockStatement<O,ForStatement<O,P>> cast = (BlockStatement<O, ForStatement<O, P>>) body;
        this.body = cast;
        return this;
    }

    @Override
    public ForStatement<O,P> setBody( StatementSource<?,?,?> statement ) {
        this.body = wrap( statement );
        return this;
    }

    @Override
    public org.eclipse.jdt.core.dom.ForStatement materialize( AST ast ) {
        if (iter != null) {
            return iter;
        }

        iter = ast.newForStatement();

        for ( DeclareExpression declaration : declarations ) {
            VariableDeclarationExpression declare = (VariableDeclarationExpression) wireAndGetExpression( declaration, this, ast );
            if ( iter.initializers().isEmpty() ) {
                iter.initializers().add( declare );
            } else {
                VariableDeclarationExpression existing = (VariableDeclarationExpression) iter.initializers().get( 0 );
                VariableDeclarationFragment frag = (VariableDeclarationFragment) declare.fragments().get( 0 );
                frag.delete();
                existing.fragments().add( frag );
            }
        }

        if ( condition != null ) {
            iter.setExpression( wireAndGetExpression( condition, this, ast ) );
        }

        for ( ExpressionSource<O,ForStatement<O,P>,?> updater : updaters ) {
            iter.updaters().add( wireAndGetExpression( updater, this, ast ) );
        }

        if ( body != null ) {
            iter.setBody( wireAndGetStatement( body, this, ast ) );
        }

        return iter;
    }

    @Override
    public org.eclipse.jdt.core.dom.ForStatement getInternal() {
        return iter;
    }

    @Override
    public void setInternal(org.eclipse.jdt.core.dom.ForStatement jdtNode) {
        super.setInternal(jdtNode);
        this.iter = jdtNode;
    }
}
