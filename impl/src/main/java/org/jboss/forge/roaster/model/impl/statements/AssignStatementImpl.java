package org.jboss.forge.roaster.model.impl.statements;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.Statement;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.impl.StatementImpl;
import org.jboss.forge.roaster.model.impl.expressions.JdtBlockWrapper;
import org.jboss.forge.roaster.model.impl.expressions.JdtExpressionWrapper;
import org.jboss.forge.roaster.model.impl.expressions.MockArgumentImpl;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.statements.AssignStatement;

public class AssignStatementImpl<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends StatementImpl<O,T>
        implements AssignStatement<O,T>,
                   JdtExpressionWrapper<O,AssignStatement<O,T>> {

    private enum State { LEFT, RIGHT };

    private Assignment axx;
    private ExpressionStatement statement;
    private Argument<O,AssignStatement<O,T>> rightExpr;
    private Argument<O,AssignStatement<O,T>> leftExpr;
    private State state;

    public AssignStatementImpl( T origin, AST ast ) {
        super( origin );
        axx = ast.newAssignment();
        axx.setOperator( Assignment.Operator.ASSIGN );
        statement = ast.newExpressionStatement( axx );
    }

    public AssignStatement<O,T> setFieldLeftExpression( String name ) {
        FieldAccess fld = axx.getAST().newFieldAccess();
        fld.setName( axx.getAST().newSimpleName( name ) );
        fld.setExpression( axx.getAST().newThisExpression() );
        axx.setLeftHandSide( fld );
        return this;
    }

    public AssignStatement<O,T> setVariableLeftExpression( String name ) {
        axx.setLeftHandSide( axx.getAST().newSimpleName( name ) );
        return this;
    }

    @Override
    public ExpressionFactory<O, AssignStatement<O, T>> setLeftExpression() {
        state = State.LEFT;
        leftExpr = new MockArgumentImpl<O, AssignStatement<O, T>>( this, axx.getAST() );
        return (ExpressionFactory<O, AssignStatement<O,T>>) leftExpr;
    }

    public T setVariableRightExpression( String name ) {
        axx.setRightHandSide( axx.getAST().newSimpleName( name ) );
        return getOrigin();
    }

    public T setFieldRightExpression( String name ) {
        FieldAccess right = axx.getAST().newFieldAccess();
        right.setName( axx.getAST().newSimpleName( name ) );
        axx.setRightHandSide( right );
        return getOrigin();
    }

    @Override
    public ExpressionFactory<O,AssignStatement<O,T>> setRightExpression() {
        state = State.RIGHT;
        rightExpr = new MockArgumentImpl<O, AssignStatement<O, T>>( this, axx.getAST() );
        return (ExpressionFactory<O, AssignStatement<O,T>>) rightExpr;
    }


    @Override
    public Statement getStatement() {
        return statement;
    }


    @Override
    public void wireExpression( ExpressionSource<O> child ) {
        switch ( state ) {
            case LEFT:
                axx.setLeftHandSide( ( (JdtExpressionWrapper) child ).getExpression() );
                break;
            case RIGHT:
                axx.setRightHandSide( ((JdtExpressionWrapper) child).getExpression() );
                break;
        }
    }

    @Override
    public <T extends ExpressionSource<O>> ExpressionFactory<O,T> getBuilder() {
        switch ( state ) {
            case LEFT:
                return (ExpressionFactory<O,T>) leftExpr;
            case RIGHT:
                return (ExpressionFactory<O,T>) rightExpr;
        }
        return null;
    }

    @Override
    public Expression getExpression() {
        switch ( state ) {
            case LEFT:
                return ((JdtExpressionWrapper) leftExpr).getExpression();
            case RIGHT:
                return ((JdtExpressionWrapper) rightExpr).getExpression();
        }
        return null;
    }
}
