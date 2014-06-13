package org.jboss.forge.roaster.model.impl.statements;

import org.eclipse.jdt.core.dom.AST;
import org.jboss.forge.roaster.model.ASTNode;
import org.jboss.forge.roaster.model.expressions.Expression;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.impl.BlockImpl;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.BlockSource;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.statements.CallStatement;
import org.jboss.forge.roaster.model.statements.Statement;
import org.jboss.forge.roaster.model.statements.StatementSource;
import org.jboss.forge.roaster.model.statements.Statements;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class InnerBlockImpl<O extends JavaSource<O>,
                                     P extends BlockHolder<O>,
                                     B extends BlockSource<O,P,B>>
        extends BlockImpl<O,P,B>
        implements BlockSource<O,P,B> {

    private List<StatementSource<O,B,?>> statements = Collections.EMPTY_LIST;

    @Override
    public org.eclipse.jdt.core.dom.Block materialize( AST ast ) {
        if (isMaterialized()) {
            return block;
        }

        block = ast.newBlock();

        for ( Statement<O,B,?> statement : statements ) {
           block.statements().add(wireAndGetStatement(statement,(B)this,ast));
        }

        return block;
    }


    @Override
    public List<StatementSource<O,B,?>> getStatements() {
        return statements;
    }

    @Override
    public BlockSource<O,P,B> addStatement( StatementSource<?,?,?> statement ) {
        return addStatement( statements.size(), statement );
    }

    @Override
    public BlockSource<O,P,B> addStatement( int index, StatementSource<?,?,?> statement ) {
        StatementSource<O,B,?> cast = (StatementSource<O, B, ?>) statement;
        ASTNode<?> jdtStatement = (ASTNode<?>) statement;
        if (jdtStatement.isMaterialized() && jdtStatement.getInternal().getParent() != block) {
            throw new RuntimeException("Trying to add a statement belonging to a different block");
        }
        if ( statements.isEmpty() ) {
            statements = new LinkedList<StatementSource<O,B,?>>();
        }
        statements.add( index, cast );
        if (isMaterialized() && !jdtStatement.isMaterialized()) {
            block.statements().add(index, wireAndGetStatement(cast,(B)this,getAst()));
        }
        return this;
    }

    @Override
    public BlockSource<O,P,B> addStatement( ExpressionSource<?,?,?> expression ) {
        return addStatement( new ExpressionStatementImpl<O,B>( (ExpressionSource<O, ?, ?>) expression ) );
    }

    @Override
    public BlockSource<O,P,B> addStatement( int index, ExpressionSource<?,?,?> expression ) {
        return addStatement( index, new ExpressionStatementImpl<O,B>( (ExpressionSource<O, ?, ?>) expression ) );
    }

    public BlockSource<O,P,B> removeStatement( StatementSource<O,B,?> statement ) {
        if ( !statements.remove(statement) ) {
            throw new RuntimeException("Trying to remove a not existing statement");
        }

        if (((JdtStatementWrapper) statement).isMaterialized()) {
            ((JdtStatementWrapper) statement).getInternal().delete();
        }
        return this;
    }

    public BlockSource<O,P,B> removeStatement( int index ) {
        if ( statements == null ) {
            throw new RuntimeException("Trying to remove a not existing statement");
        }
        return removeStatement( statements.get( index ) );
    }

}
