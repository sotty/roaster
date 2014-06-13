/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.impl.statements;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AssertStatement;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ContinueStatement;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.jboss.forge.roaster.model.impl.BlockImpl;
import org.jboss.forge.roaster.model.impl.expressions.ExpressionBuilder;
import org.jboss.forge.roaster.model.statements.BlockStatement;

import static org.jboss.forge.roaster.model.statements.Statements.newAssert;
import static org.jboss.forge.roaster.model.statements.Statements.newBlock;
import static org.jboss.forge.roaster.model.statements.Statements.newContinue;
import static org.jboss.forge.roaster.model.statements.Statements.newReturn;

public class StatementBuilder {

    public static org.jboss.forge.roaster.model.statements.StatementSource asRoasterStatement(Statement jdtStatement)
    {
        org.jboss.forge.roaster.model.statements.StatementSource roasterStmt = null;
        switch (jdtStatement.getNodeType()) {
            case ASTNode.RETURN_STATEMENT:
                ReturnStatement returnStatement = (ReturnStatement) jdtStatement;
                roasterStmt = newReturn()
                        .setReturn( ExpressionBuilder.asRoasterExpression( returnStatement.getExpression() ) );
                break;
            case ASTNode.ASSERT_STATEMENT:
                AssertStatement assertStatement = (AssertStatement) jdtStatement;
                roasterStmt = newAssert()
                        .setAssertion( ExpressionBuilder.asRoasterExpression( assertStatement.getExpression() ) )
                        .setMessage( ExpressionBuilder.asRoasterExpression( assertStatement.getMessage() ) );
                break;
            case ASTNode.ASSIGNMENT:
                //TODO
                break;
            case ASTNode.BLOCK:
                Block block = (Block) jdtStatement;
                roasterStmt = newBlock();
                ((BlockImpl) roasterStmt).setInternal( block );
                for ( Object o : block.statements() ) {
                    ((BlockStatement) roasterStmt).addStatement( StatementBuilder.asRoasterStatement( (Statement) o ) );
                }
                break;
            case ASTNode.CONTINUE_STATEMENT:
                ContinueStatement continueStatement = (ContinueStatement) jdtStatement;
                roasterStmt = newContinue();
                break;
            case ASTNode.VARIABLE_DECLARATION_STATEMENT:
                break;
            case ASTNode.DO_STATEMENT:
                break;
            case ASTNode.EXPRESSION_STATEMENT:
                break;
            case ASTNode.ENHANCED_FOR_STATEMENT:
                break;
            case ASTNode.FOR_STATEMENT:
                break;
            case ASTNode.IF_STATEMENT:
                break;
            case ASTNode.METHOD_INVOCATION:
                break;
            case ASTNode.SUPER_METHOD_INVOCATION:
                break;
            case ASTNode.CONSTRUCTOR_INVOCATION:
                break;
            case ASTNode.SUPER_CONSTRUCTOR_INVOCATION:
                break;
            case ASTNode.SWITCH_STATEMENT:
                break;
            case ASTNode.SYNCHRONIZED_STATEMENT:
                break;
            case ASTNode.TRY_STATEMENT:
                break;
            case ASTNode.WHILE_STATEMENT:
                break;
            case ASTNode.BREAK_STATEMENT:
                break;
            case ASTNode.LABELED_STATEMENT:
                break;
            default:
                throw new RuntimeException("Unknown statement: " + jdtStatement);
        }
        if ( roasterStmt != null ) {
            ( (org.jboss.forge.roaster.model.ASTNode) roasterStmt ).setInternal( jdtStatement );
        }
        return roasterStmt;
    }

}


