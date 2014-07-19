package org.jboss.forge.roaster.model.impl.expressions;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.jboss.forge.roaster.model.expressions.DeclareExpression;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.impl.JDTHelper;
import org.jboss.forge.roaster.model.source.JavaSource;

public class DeclareExpressionImpl<O extends JavaSource<O>, T extends ExpressionSource<O>>
        extends ArgumentImpl<O,T>
        implements DeclareExpression<O,T>,
                   ExpressionSource<O> {


    public DeclareExpressionImpl( T origin, Expression expr, boolean wireToParent ) {
        super( origin, expr, wireToParent );
    }

    @Override
    public DeclareExpression<O, T> setVariable( String name, Class type ) {
        VariableDeclarationExpression decl = ( VariableDeclarationExpression ) expr;
        (( VariableDeclarationFragment ) decl.fragments().get( decl.fragments().size() - 1 )).setName( ast.newSimpleName( name ) );
        decl.setType( JDTHelper.getType( type, ast ) );
        return this;
    }

    @Override
    public DeclareExpression<O, T> setVariable( String name, String type ) {
        VariableDeclarationExpression decl = ( VariableDeclarationExpression ) expr;
        (( VariableDeclarationFragment ) decl.fragments().get( decl.fragments().size() - 1 )).setName( ast.newSimpleName( name ) );
        decl.setType( JDTHelper.getType( type, ast ) );
        return this;
    }

    @Override
    protected void concatExpression( Expression parent, Expression child ) {
        VariableDeclarationExpression decl = ( VariableDeclarationExpression ) expr;
        (( VariableDeclarationFragment ) decl.fragments().get( decl.fragments().size() - 1 )).setInitializer( child );
    }

    @Override
    public ExpressionFactory<O, T> init() {
        return (ExpressionFactory<O, T>) new MockArgumentImpl<O,DeclareExpression<O,T>>( this, ast );
    }

    @Override
    public ExpressionSource<O> asExpressionSource() {
        return ((JdtExpressionWrapper) origin).asExpressionSource();
    }

}
