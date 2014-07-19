package org.jboss.forge.roaster.model.impl.statements;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.expressions.DeclareExpression;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.impl.BlockImpl;
import org.jboss.forge.roaster.model.impl.StatementImpl;
import org.jboss.forge.roaster.model.impl.expressions.DeclareExpressionImpl;
import org.jboss.forge.roaster.model.impl.expressions.JdtBlockWrapper;
import org.jboss.forge.roaster.model.impl.expressions.JdtExpressionWrapper;
import org.jboss.forge.roaster.model.impl.expressions.MockArgumentImpl;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.statements.ForStatement;

public class ForStatementImpl<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends StatementImpl<O,T>
        implements ForStatement<O,T>,
                   JdtExpressionWrapper<O,ForStatement<O,T>> {

    private enum State { INIT, COND, UPD };
    private org.eclipse.jdt.core.dom.ForStatement iter;
    private VariableDeclarationExpression var;
    private Argument<O,ForStatement<O,T>> cond;
    private State state;

    public ForStatementImpl( T origin, AST ast ) {
        super( origin );
        iter = ast.newForStatement();
    }

    @Override
    public Statement getStatement() {
        return iter;
    }

    @Override
    public void wireExpression( ExpressionSource<O> child ) {
        switch ( state ) {
            case COND:
                iter.setExpression( ((JdtExpressionWrapper) child).getExpression() );
                break;
            case INIT:
                if ( iter.initializers().isEmpty() ) {
                    iter.initializers().add( ( (JdtExpressionWrapper) child ).getExpression() );
                }
                break;
            case UPD:
                iter.updaters().add( ((JdtExpressionWrapper) child).getExpression() );
                break;
        }
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
    public ExpressionFactory<O, ForStatement<O, T>> setCondition() {
        state = State.COND;
        cond = new MockArgumentImpl<O, ForStatement<O, T>>( this, iter.getAST() );
        return (ExpressionFactory<O, ForStatement<O, T>>) cond;
    }

    @Override
    public DeclareExpression<O, ForStatement<O, T>> addDeclaration() {
        state = State.INIT;
        if ( var == null ) {
            var = iter.getAST().newVariableDeclarationExpression( iter.getAST().newVariableDeclarationFragment() );
        } else {
            var.fragments().add( iter.getAST().newVariableDeclarationFragment() );
        }
        return new DeclareExpressionImpl<O,ForStatement<O,T>>( this, var, true );
    }

    @Override
    public ExpressionFactory<O, ForStatement<O, T>> addUpdate() {
        state = State.UPD;
        return new MockArgumentImpl<O,ForStatement<O,T>>( this, iter.getAST() );
    }

    @Override
    public Block<O, ForStatement<O,T>> setBody() {
        return new BlockImpl<O,ForStatement<O,T>>( this, iter.getAST() );
    }

    @Override
    public void wireBlock( Block block ) {
        iter.setBody( ((JdtBlockWrapper) block).getBlock() );
    }
}
