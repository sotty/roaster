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

public class IfStatementImpl<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends StatementImpl<O,T>
        implements IfStatement<O,T>,
                   JdtExpressionWrapper<O,IfStatement<O,T>> {

    private enum State { THEN, ELSE };

    private org.eclipse.jdt.core.dom.IfStatement iff;
    private Argument<O,IfStatement<O,T>> cond;
    private State state;

    public IfStatementImpl( T origin, AST ast ) {
        super( origin );
        iff = ast.newIfStatement();
    }

    @Override
    public Statement getStatement() {
        return iff;
    }

    @Override
    public void wireExpression( ExpressionSource<O> child ) {
        iff.setExpression( ((JdtExpressionWrapper) child).getExpression() );
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
    public ExpressionFactory<O, IfStatement<O, T>> condition() {
        cond = new MockArgumentImpl<O, IfStatement<O, T>>( this, iff.getAST() );
        return (ExpressionFactory<O, IfStatement<O, T>>) cond;
    }

    @Override
    public Block<O, IfStatement<O,T>> thenDo() {
        state = State.THEN;
        return new BlockImpl<O,IfStatement<O,T>>( this, iff.getAST() );
    }

    @Override
    public Block<O, IfStatement<O,T>> elseDo() {
        state = State.ELSE;
        return new BlockImpl<O,IfStatement<O,T>>( this, iff.getAST() );
    }

    @Override
    public void wireBlock( Block block ) {
        switch ( state ) {
            case THEN:
                iff.setThenStatement( ((JdtBlockWrapper) block).getBlock() );
                break;
            case ELSE:
                iff.setElseStatement( ((JdtBlockWrapper) block).getBlock() );
                break;
        }
    }
}
