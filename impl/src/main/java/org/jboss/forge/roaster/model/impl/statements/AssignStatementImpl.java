/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.impl.statements;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.jboss.forge.roaster.model.expressions.Accessor;
import org.jboss.forge.roaster.model.expressions.Expression;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.BlockSource;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.statements.AssignmentStatement;

public class AssignStatementImpl<O extends JavaSource<O>, 
                                 P extends BlockSource<O,? extends BlockHolder<O>,P>>
        extends StatementImpl<O,P,AssignmentStatement<O,P>,ExpressionStatement>
        implements AssignmentStatement<O,P> {

    private Assignment axx;
    private ExpressionStatement statement;

    private Accessor<O,AssignmentStatement<O,P>,?> left;
    private ExpressionSource<O,AssignmentStatement<O,P>,?> right;

    public AssignStatementImpl() {}

    @Override
    public AssignmentStatement<O,P> setRight( ExpressionSource<?,?,?> right ) {
        ExpressionSource<O,AssignmentStatement<O,P>,?> cast = (ExpressionSource<O, AssignmentStatement<O, P>, ?>) right;
        this.right = cast;
        return this;
    }

    @Override
    public AssignmentStatement<O,P> setLeft( Accessor<?,?,?> left ) {
        Accessor<O,AssignmentStatement<O,P>,?> cast = (Accessor<O, AssignmentStatement<O, P>, ?>) left;
        this.left = cast;
        return this;
    }

    @Override
    public Accessor<O, AssignmentStatement<O, P>, ?> getLeft() {
        return left;
    }

    @Override
    public ExpressionSource<O, AssignmentStatement<O, P>, ?> getRight() {
        return right;
    }

    public ExpressionStatement materialize( AST ast ) {
        if (statement != null) {
            return statement;
        }
        axx = ast.newAssignment();
        axx.setOperator( Assignment.Operator.ASSIGN );
        statement = ast.newExpressionStatement( axx );

        if ( left != null ) {
            axx.setLeftHandSide( wireAndGetExpression( left, this, getAst() ) );
        }
        if ( right != null ) {
            axx.setRightHandSide( wireAndGetExpression( right, this, getAst() ) );
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
