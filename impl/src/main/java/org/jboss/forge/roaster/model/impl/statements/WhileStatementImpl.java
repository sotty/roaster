package org.jboss.forge.roaster.model.impl.statements;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Statement;
import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.impl.BlockImpl;
import org.jboss.forge.roaster.model.impl.StatementImpl;
import org.jboss.forge.roaster.model.impl.expressions.JdtBlockWrapper;
import org.jboss.forge.roaster.model.impl.expressions.JdtExpressionWrapper;
import org.jboss.forge.roaster.model.impl.expressions.MockArgumentImpl;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.statements.IfStatement;
import org.jboss.forge.roaster.model.statements.WhileStatement;

public class WhileStatementImpl<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends StatementImpl<O,T>
        implements WhileStatement<O,T>,
                   JdtExpressionWrapper<O,WhileStatement<O,T>> {

    private org.eclipse.jdt.core.dom.WhileStatement rep;
    private Argument<O,WhileStatement<O,T>> cond;

    public WhileStatementImpl( T origin, AST ast ) {
        super( origin );
        rep = ast.newWhileStatement();
    }

    @Override
    public Statement getStatement() {
        return rep;
    }

    @Override
    public void wireExpression( ExpressionSource<O> child ) {
        rep.setExpression( ((JdtExpressionWrapper) child).getExpression() );
    }

    @Override
    public <T extends ExpressionSource<O>> ExpressionFactory<O,T> getBuilder() {
        return (ExpressionFactory<O,T>) cond;
    }

    @Override
    public Expression getExpression() {
        return ( (JdtExpressionWrapper<O,T>) cond ).getExpression();
    }


    @Override
    public ExpressionFactory<O, WhileStatement<O, T>> setCondition() {
        cond = new MockArgumentImpl<O, WhileStatement<O, T>>( this, rep.getAST() );
        return (ExpressionFactory<O, WhileStatement<O, T>>) cond;
    }

    @Override
    public Block<O, WhileStatement<O,T>> setBody() {
        return new BlockImpl<O,WhileStatement<O,T>>( this, rep.getAST() );
    }

    @Override
    public void wireBlock( Block block ) {
        rep.setBody( ((JdtBlockWrapper) block).getBlock() );
    }
}
