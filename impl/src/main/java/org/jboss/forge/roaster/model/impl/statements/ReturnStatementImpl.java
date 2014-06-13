package org.jboss.forge.roaster.model.impl.statements;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Statement;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.expressions.InvokeExpression;
import org.jboss.forge.roaster.model.impl.StatementImpl;
import org.jboss.forge.roaster.model.impl.expressions.ArgumentImpl;
import org.jboss.forge.roaster.model.impl.expressions.JdtExpressionWrapper;
import org.jboss.forge.roaster.model.impl.expressions.MockArgumentImpl;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.statements.ReturnStatement;

public class ReturnStatementImpl<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends StatementImpl<O,T>
        implements ReturnStatement<O,T>,
                   JdtExpressionWrapper<O,ReturnStatement<O,T>> {

    private org.eclipse.jdt.core.dom.ReturnStatement ret;
    private Argument<O,ReturnStatement<O,T>> returnExpr;

    public ReturnStatementImpl( T origin, AST ast ) {
        super( origin );
        ret = ast.newReturnStatement();
    }

    public ReturnStatement<O,T> var( String name ) {
        ret.setExpression( ret.getAST().newName( name ) );
        return this;
    }

    @Override
    public ExpressionFactory<O, ReturnStatement<O,T>> expr() {
        returnExpr = new MockArgumentImpl<O, ReturnStatement<O, T>>( this, ret.getAST() );
        return (ExpressionFactory<O, ReturnStatement<O,T>>) returnExpr;
    }

    @Override
    public InvokeExpression<O,ReturnStatement<O,T>> invoke() {
        returnExpr = expr().invoke();
        return (InvokeExpression<O, ReturnStatement<O, T>>) returnExpr;
    }

    @Override
    public ReturnStatement<O,T> nil() {
        ret.setExpression( ret.getAST().newNullLiteral() );
        return this;
    }

    @Override
    public ReturnStatement<O,T> yes() {
        ret.setExpression( ret.getAST().newBooleanLiteral( true ) );
        return this;
    }

    @Override
    public ReturnStatement<O,T> no() {
        ret.setExpression( ret.getAST().newBooleanLiteral( false ) );
        return this;
    }

    @Override
    public Statement getStatement() {
        return ret;
    }


    @Override
    public void wireExpression( ExpressionSource<O> child ) {
        returnExpr = (Argument<O, ReturnStatement<O,T>>) child;
        ret.setExpression( ((JdtExpressionWrapper) child ).getExpression() );
    }

    @Override
    public <T extends ExpressionSource<O>> ExpressionFactory<O,T> getBuilder() {
        return (ExpressionFactory<O,T>) returnExpr;
    }

    @Override
    public Expression getExpression() {
        return ((ArgumentImpl<O,ReturnStatement<O,T>>) returnExpr).getExpression();
    }
}
