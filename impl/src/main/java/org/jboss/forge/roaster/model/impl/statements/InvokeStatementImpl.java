package org.jboss.forge.roaster.model.impl.statements;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.impl.JDTHelper;
import org.jboss.forge.roaster.model.impl.StatementImpl;
import org.jboss.forge.roaster.model.impl.expressions.JdtBlockWrapper;
import org.jboss.forge.roaster.model.impl.expressions.JdtExpressionWrapper;
import org.jboss.forge.roaster.model.impl.expressions.MockArgumentImpl;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.statements.InvokeStatement;

public class InvokeStatementImpl<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends    StatementImpl<O,T>
        implements InvokeStatement<O,T>,
                   JdtExpressionWrapper<O,InvokeStatement<O,T>> {

    private enum State { ON, ARGS }
    private State state;

    private ExpressionStatement statement;
    private MethodInvocation invoke;
    private Argument<O,InvokeStatement<O,T>> arg;

    public InvokeStatementImpl( T origin, AST ast ) {
        super( origin );
        invoke = ast.newMethodInvocation();
        statement = ast.newExpressionStatement( invoke );
    }

    @Override
    public org.eclipse.jdt.core.dom.Statement getStatement() {
        return statement;
    }

    @Override
    public ExpressionFactory<O, InvokeStatement<O,T>> on() {
        state = State.ON;
        Argument<O,InvokeStatement<O,T>> onExp = new MockArgumentImpl<O, InvokeStatement<O, T>>( this, invoke.getAST() );
        return (ExpressionFactory<O, InvokeStatement<O,T>>) onExp;
    }

    @Override
    public InvokeStatement<O, T> onVariable( String varName ) {
        invoke.setExpression( statement.getAST().newSimpleName( varName ) );
        return this;
    }

    @Override
    public InvokeStatement<O, T> method( String methodName ) {
        invoke.setName( statement.getAST().newSimpleName( methodName ) );
        return this;
    }

    @Override
    public InvokeStatement<O, T> staticMethod( String methodName, Class klass ) {
        invoke.setName( statement.getAST().newSimpleName( methodName ) );
        invoke.setExpression( statement.getAST().newName( JDTHelper.getTypeName( JDTHelper.getType( klass, statement.getAST() ) ) ) );
        return this;
    }

    @Override
    public InvokeStatement<O, T> staticMethod( String methodName, String klass ) {
        invoke.setName( statement.getAST().newSimpleName( methodName ) );
        invoke.setExpression( statement.getAST().newName( JDTHelper.getTypeName( JDTHelper.getType( klass, statement.getAST() ) ) ) );
        return this;
    }

    @Override
    public ExpressionFactory<O, InvokeStatement<O,T>> addArgument() {
        state = State.ARGS;
        arg = new MockArgumentImpl<O, InvokeStatement<O, T>>( this, invoke.getAST() );
        return (ExpressionFactory<O, InvokeStatement<O, T>>) arg;
    }

    @Override
    public void wireExpression( ExpressionSource<O> child ) {
        switch ( state ) {
            case ON : invoke.setExpression( ( (JdtExpressionWrapper) child ).getExpression() );
                break;
            case ARGS:
                default:
                    invoke.arguments().add( ((JdtExpressionWrapper) child).getExpression() );
        }

    }

    @Override
    public <T extends ExpressionSource<O>> ExpressionFactory<O,T> getBuilder() {
        return (ExpressionFactory<O,T>) arg;
    }

    @Override
    public Expression getExpression() {
        return invoke.getExpression();
    }
}
