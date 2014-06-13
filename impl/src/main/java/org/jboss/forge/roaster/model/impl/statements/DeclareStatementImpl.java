package org.jboss.forge.roaster.model.impl.statements;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.impl.JDTHelper;
import org.jboss.forge.roaster.model.impl.StatementImpl;
import org.jboss.forge.roaster.model.impl.expressions.ArgumentImpl;
import org.jboss.forge.roaster.model.impl.expressions.JdtBlockWrapper;
import org.jboss.forge.roaster.model.impl.expressions.JdtExpressionWrapper;
import org.jboss.forge.roaster.model.impl.expressions.MockArgumentImpl;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.statements.DeclareStatement;

public class DeclareStatementImpl<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends StatementImpl<O,T>
        implements DeclareStatement<O,T>,
                   JdtExpressionWrapper<O,DeclareStatement<O,T>> {

    private VariableDeclarationStatement var;
    private Argument<O,DeclareStatement<O,T>> initExpr;


    public DeclareStatementImpl( T origin, AST ast ) {
        super( origin );
        var = ast.newVariableDeclarationStatement( ast.newVariableDeclarationFragment() );

    }

    public DeclareStatement<O, T> name( String name ) {
        getMainVar().setName( var.getAST().newSimpleName( name ) );
        return this;
    }

    @Override
    public DeclareStatement<O, T> type( String typeName ) {
        var.setType( var.getAST().newSimpleType( var.getAST().newName( typeName ) ) );
        return this;
    }

    @Override
    public DeclareStatement<O, T> type( Class type ) {
        var.setType( JDTHelper.getType( type, var.getAST() ) );
        return this;
    }

    @Override
    public DeclareStatement<O, T> initDefault() {
        if ( var.getType() != null ) {
            initExpr = new MockArgumentImpl<O, DeclareStatement<O,T>>( this, var.getAST() );
            ( (ExpressionFactory<O, DeclareStatement<O,T>>) initExpr ).zero( var.getType().toString() );

        } else {
            throw new IllegalStateException( "Unable to initialize a var before its type has been specified " );
        }
        return this;
    }

    @Override
    public Statement getStatement() {
        return var;
    }

    public VariableDeclarationFragment getMainVar() {
        return (VariableDeclarationFragment) var.fragments().iterator().next();
    }

    @Override
    public ExpressionFactory<O,DeclareStatement<O,T>> init() {
        initExpr = new MockArgumentImpl<O, DeclareStatement<O,T>>( this, var.getAST() );
        return (ExpressionFactory<O, DeclareStatement<O,T>>) initExpr;
    }

    @Override
    public void wireExpression( ExpressionSource<O> child ) {
        getMainVar().setInitializer( ( (JdtExpressionWrapper) child ).getExpression() );
    }

    @Override
    public <T extends ExpressionSource<O>> ExpressionFactory<O,T> getBuilder() {
        return (ExpressionFactory<O,T>) initExpr;
    }

    @Override
    public Expression getExpression() {
        return ((ArgumentImpl<O,DeclareStatement<O,T>>) initExpr).getExpression();
    }
}
