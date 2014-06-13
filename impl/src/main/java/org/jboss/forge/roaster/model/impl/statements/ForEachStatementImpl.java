package org.jboss.forge.roaster.model.impl.statements;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.expressions.DeclareExpression;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.impl.BlockImpl;
import org.jboss.forge.roaster.model.impl.JDTHelper;
import org.jboss.forge.roaster.model.impl.StatementImpl;
import org.jboss.forge.roaster.model.impl.expressions.DeclareExpressionImpl;
import org.jboss.forge.roaster.model.impl.expressions.JdtBlockWrapper;
import org.jboss.forge.roaster.model.impl.expressions.JdtExpressionWrapper;
import org.jboss.forge.roaster.model.impl.expressions.MockArgumentImpl;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.statements.ForEachStatement;
import org.jboss.forge.roaster.model.statements.ForStatement;

public class ForEachStatementImpl<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends StatementImpl<O,T>
        implements ForEachStatement<O,T>,
                   JdtExpressionWrapper<O,ForStatement<O,T>> {

    private EnhancedForStatement iter;
    private Argument<O,ForEachStatement<O,T>> set;


    public ForEachStatementImpl( T origin, AST ast ) {
        super( origin );
        iter = ast.newEnhancedForStatement();
    }

    @Override
    public Statement getStatement() {
        return iter;
    }

    @Override
    public void wireExpression( ExpressionSource<O> child ) {
        iter.setExpression( ( (JdtExpressionWrapper) child ).getExpression() );
    }

    @Override
    public <T extends ExpressionSource<O>> ExpressionFactory<O,T> getBuilder() {
        return (ExpressionFactory<O,T>) set;
    }

    @Override
    public Expression getExpression() {
        return ( (JdtExpressionWrapper<O,T>) set ).getExpression();
    }

    @Override
    public ForEachStatement<O,T> iterator( String name ) {
        iter.getParameter().setName( iter.getAST().newSimpleName( name ) );
        return this;
    }

    @Override
    public ForEachStatement<O,T> type( String type ) {
        iter.getParameter().setType( JDTHelper.getType( type, iter.getAST() ) );
        return this;
    }

    @Override
    public ForEachStatement<O,T> type( Class<?> type ) {
        iter.getParameter().setType( JDTHelper.getType( type, iter.getAST() ) );
        return this;
    }

    @Override
    public ExpressionFactory<O,ForEachStatement<O,T>> in() {
        set = new MockArgumentImpl<O,ForEachStatement<O,T>>( this, iter.getAST() );
        return (ExpressionFactory<O,ForEachStatement<O,T>>) set;
    }

    @Override
    public Block<O,ForEachStatement<O,T>> repeat() {
        return new BlockImpl<O,ForEachStatement<O,T>>( this, iter.getAST() );
    }

    @Override
    public void wireBlock( Block block ) {
        iter.setBody( ((JdtBlockWrapper) block).getBlock() );
    }
}
