/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.impl.statements;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.jboss.forge.roaster.model.expressions.Accessor;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.BlockSource;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.statements.InvokeStatement;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class InvokeStatementImpl<O extends JavaSource<O>,
                                 P extends BlockHolder<O>>
        extends    StatementImpl<O,P,InvokeStatement<O,P>,ExpressionStatement>
        implements InvokeStatement<O,P> {

    private ExpressionStatement statement;
    private MethodInvocation invoke;

    private String method;
    private Accessor<O,InvokeStatement<O,P>,?> target;
    private List<Argument<O,InvokeStatement<O,P>,?>> argumentList = Collections.EMPTY_LIST;

    public InvokeStatementImpl() { }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public InvokeStatement<O, P> setMethod( String method ) {
        this.method = method;
        return this;
    }

    @Override
    public Accessor<O, InvokeStatement<O, P>, ?> getTarget() {
        return target;
    }

    @Override
    public InvokeStatement<O,P> setTarget( Accessor<?,?,?> target ) {
        Accessor<O,InvokeStatement<O,P>,?> cast = (Accessor<O, InvokeStatement<O, P>, ?>) target;
        this.target = cast;
        return this;
    }

    @Override
    public List<Argument<O, InvokeStatement<O, P>, ?>> getArguments() {
        return argumentList;
    }

    @Override
    public InvokeStatement<O,P> addArgument( Argument<?,?,?> argument ) {
        Argument<O,InvokeStatement<O,P>,?> cast = (Argument<O, InvokeStatement<O, P>, ?>) argument;
        if ( argumentList.isEmpty() ) {
            argumentList = new LinkedList<Argument<O,InvokeStatement<O,P>,?>>();
        }
        argumentList.add( cast );
        return this;
    }

    @Override
    public ExpressionStatement materialize( AST ast ) {
        if (statement != null) {
            return statement;
        }
        invoke = ast.newMethodInvocation();
        statement = ast.newExpressionStatement( invoke );

        invoke.setName( statement.getAST().newSimpleName( method ) );

        if ( target != null ) {
            invoke.setExpression( wireAndGetExpression( target, this, ast ) );
        }

        for ( Argument argument : argumentList ) {
            invoke.arguments().add( wireAndGetExpression( argument, this, ast ) );
        }
        return statement;
    }

    @Override
    public ExpressionStatement getInternal() {
        return statement;
    }

    @Override
    public void setInternal( ExpressionStatement jdtNode ) {
        super.setInternal(jdtNode);
        this.statement = jdtNode;
    }
}
