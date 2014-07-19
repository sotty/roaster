package org.jboss.forge.roaster.model.impl.expressions;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.TypeLiteral;
import org.jboss.forge.roaster.model.expressions.Accessor;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.expressions.AssignExpression;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.expressions.CastExpression;
import org.jboss.forge.roaster.model.expressions.ConstructorExpression;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.expressions.ExpressionTypes;
import org.jboss.forge.roaster.model.expressions.InvokeExpression;
import org.jboss.forge.roaster.model.expressions.Literal;
import org.jboss.forge.roaster.model.expressions.OperatorExpression;
import org.jboss.forge.roaster.model.expressions.ParenExpression;
import org.jboss.forge.roaster.model.expressions.TernaryExpression;
import org.jboss.forge.roaster.model.expressions.UnaryExpression;
import org.jboss.forge.roaster.model.expressions.Variable;
import org.jboss.forge.roaster.model.impl.JDTHelper;
import org.jboss.forge.roaster.model.source.JavaSource;

public abstract class ExpressionFactoryImpl<O extends JavaSource<O>, T extends ExpressionSource<O>>
        implements ExpressionFactory<O,T> {

    protected AST ast;
    protected T origin;

    public ExpressionFactoryImpl( T origin, AST ast ) {
        this.ast = ast;
        this.origin = origin;
    }

    public AST getAst() {
        return ast;
    }

    protected Argument<O,T> subArg( org.eclipse.jdt.core.dom.Expression expr ) {
        return subArg( expr, ExpressionTypes.LITERAL );
    }

    protected abstract <X extends Argument<O,T>> X subArg( org.eclipse.jdt.core.dom.Expression expr, ExpressionTypes type );

    @Override
    public Literal<O, T> literal( String val ) {
        StringLiteral lit = ast.newStringLiteral();
        lit.setLiteralValue( val );
        return subArg( lit, ExpressionTypes.LITERAL );
    }

    @Override
    public Literal<O, T> literal( Number val ) {
        NumberLiteral lit = ast.newNumberLiteral();
        lit.setToken( val.toString() );
        return subArg( lit, ExpressionTypes.LITERAL );
    }

    @Override
    public Literal<O, T> literal( Character val ) {
        CharacterLiteral lit = ast.newCharacterLiteral();
        lit.setCharValue( val );
        return subArg( lit, ExpressionTypes.LITERAL );
    }

    @Override
    public Literal<O, T> literal( Class<?> val ) {
        TypeLiteral lit = ast.newTypeLiteral();
        lit.setType( ast.newSimpleType( ast.newName( val.getName() ) ) );
        return subArg( lit, ExpressionTypes.LITERAL );
    }

    @Override
    public Literal<O, T> literal( Boolean val ) {
        BooleanLiteral lit = ast.newBooleanLiteral( Boolean.valueOf( val ) );
        return subArg( lit, ExpressionTypes.LITERAL );
    }

    protected boolean isNumericPrimitive( Class<?> klass ) {
        return klass == long.class || klass == int.class || klass == short.class || klass == double.class || klass == float.class;
    }


    public Argument<O,T> zeroLiteral( Class klass ) {
        return zeroLiteral( klass.getName() );
    }

    public Argument<O,T> zeroLiteral( String klass ) {
        org.eclipse.jdt.core.dom.Expression init;
        if ( boolean.class.getName().equals( klass ) ) {
            init = ast.newBooleanLiteral( false );
        } else if ( byte.class.getName().equals( klass  ) ) {
            NumberLiteral zero = ast.newNumberLiteral();
            zero.setToken( "0b" );
            init = zero;
        } else if ( char.class.getName().equals( klass ) ) {
            NumberLiteral zero = ast.newNumberLiteral();
            zero.setToken( "0" );
            init = zero;
        } else if ( double.class.getName().equals( klass  ) ) {
            NumberLiteral zero = ast.newNumberLiteral();
            zero.setToken( "0.0" );
            init = zero;
        } else if ( float.class.getName().equals( klass ) ) {
            NumberLiteral zero = ast.newNumberLiteral();
            zero.setToken( "0.0f" );
            init = zero;
        } else if ( int.class.getName().equals( klass ) ) {
            NumberLiteral zero = ast.newNumberLiteral();
            zero.setToken( "0" );
            init = zero;
        } else if ( long.class.getName().equals( klass ) ) {
            NumberLiteral zero = ast.newNumberLiteral();
            zero.setToken( "0L" );
            init = zero;
        } else if ( short.class.getName().equals( klass ) ) {
            NumberLiteral zero = ast.newNumberLiteral();
            zero.setToken( "0" );
            init = zero;
        } else {
            init = ast.newNullLiteral();
        }
        return subArg( init, ExpressionTypes.LITERAL );
    }

    @Override
    public OperatorExpression<O,T> operator( String op ) {
        InfixExpression opXp = ast.newInfixExpression();
        opXp.setOperator( InfixExpression.Operator.toOperator( op ) );
        return (OperatorExpression<O,T>) subArg( opXp, ExpressionTypes.OPERATOR );
    }

    @Override
    public Argument<O,T> nullLiteral() {
        NullLiteral nil = ast.newNullLiteral();
        return subArg( nil, ExpressionTypes.LITERAL );
    }

    @Override
    public UnaryExpression<O, T> not() {
        PrefixExpression not = ast.newPrefixExpression();
        not.setOperator( PrefixExpression.Operator.NOT );
        return (UnaryExpression<O, T>) subArg( not, ExpressionTypes.NOT );
    }


    @Override
    public Argument<O,T> field( String fieldName ) {
        FieldAccess exp = ast.newFieldAccess();
        exp.setName( ast.newSimpleName( fieldName ) );
        exp.setExpression( ast.newThisExpression() );
        return subArg( exp, ExpressionTypes.FIELD );
    }

    @Override
    public Argument<O,T> classLiteral( Class<?> klass ) {
        return classLiteral( klass.getName() );
    }

    @Override
    public Argument<O,T> classLiteral( String klass ) {
        Name klassName = ast.newName( klass );
        return subArg( klassName, ExpressionTypes.LITERAL );
    }

    @Override
    public Argument<O,T> getter( String fieldName, String type ) {
        MethodInvocation exp = ast.newMethodInvocation();
        exp.setName( ast.newSimpleName( JDTHelper.getter( fieldName, type ) ) );
        return subArg( exp, ExpressionTypes.INVOKE );
    }

    @Override
    public InvokeExpression<O,T> invoke() {
        MethodInvocation inv = ast.newMethodInvocation();
        return (InvokeExpression<O,T>) subArg( inv, ExpressionTypes.INVOKE );
    }

    @Override
    public ExpressionFactory<O,T> cast( String klass ) {
        org.eclipse.jdt.core.dom.CastExpression cast = ast.newCastExpression();
        cast.setType( JDTHelper.getType( klass, ast ) );
        CastExpression<O,T> expr = (CastExpression<O,T>) subArg( cast, ExpressionTypes.CAST );
        return (ExpressionFactory<O, T>) new MockArgumentImpl<O, CastExpression<O,T>>( expr, ast );
    }

    @Override
    public ExpressionFactory<O,T> cast( Class klass ) {
        org.eclipse.jdt.core.dom.CastExpression cast = ast.newCastExpression();
        cast.setType( JDTHelper.getType( klass, ast ) );
        CastExpression<O,T> expr = (CastExpression<O,T>) subArg( cast, ExpressionTypes.CAST );
        return (ExpressionFactory<O, T>) new MockArgumentImpl<O, CastExpression<O,T>>( expr, ast );
    }

    @Override
    public ConstructorExpression<O,T> newInstance( String klass ) {
        ClassInstanceCreation gnu = ast.newClassInstanceCreation();
        gnu.setType( JDTHelper.getType( klass, ast ) );
        return (ConstructorExpression<O,T>) subArg( gnu, ExpressionTypes.NEW );
    }

    @Override
    public ConstructorExpression<O,T> newInstance( Class<?> klass ) {
        ClassInstanceCreation gnu = ast.newClassInstanceCreation();
        gnu.setType( JDTHelper.getType( klass, ast ) );
        return (ConstructorExpression<O,T>) subArg( gnu, ExpressionTypes.NEW );
    }

    @Override
    public Variable<O,T> variableRef( String varName ) {
        Name exp = ast.newName( varName );
        return subArg( exp, ExpressionTypes.VAR );
    }

    @Override
    public AssignExpression<O,T> assign( String operator ) {
        Assignment axx = ast.newAssignment();
        axx.setOperator( Assignment.Operator.toOperator( operator ) );
        return subArg( axx, ExpressionTypes.ASSIGN );
    }

    @Override
    public Argument<O,T> thisLiteral() {
        ThisExpression exp = ast.newThisExpression();
        return subArg( exp, ExpressionTypes.SELF );
    }

    public Accessor<O,T> dot() {
        return new DotAccessorImpl<O,T>( origin, null, ast );
    }

    public ExpressionFactory<O,ParenExpression<O,T>> paren() {
        ParenthesizedExpression parenXp = ast.newParenthesizedExpression();
        return (ExpressionFactory<O, ParenExpression<O, T>>) subArg( parenXp, ExpressionTypes.PAREN );
    }

    @Override
    public TernaryExpression<O,T> ternary() {
        ConditionalExpression exp = ast.newConditionalExpression();
        return (TernaryExpression<O,T>) subArg( exp, ExpressionTypes.CONDITIONAL );
    }

    protected abstract void concatExpression( org.eclipse.jdt.core.dom.Expression parent, org.eclipse.jdt.core.dom.Expression child );

    protected <X extends Argument<O,T>> X buildArgument( org.eclipse.jdt.core.dom.Expression expr, ExpressionTypes type, boolean wireToParent ) {
        switch ( type ) {
            case INVOKE:
                return (X) new InvokeableImpl<O,T>( getParent(), expr, wireToParent );
            case OPERATOR:
                return (X) new OperatorImpl<O,T>( getParent(), expr, wireToParent );
            case LITERAL:
                return (X) new LiteralImpl<O,T>( getParent(), expr, wireToParent );
            case CONDITIONAL:
                return (X) new TernaryImpl<O,T>( getParent(), expr, wireToParent );
            case CAST:
                return (X) new CastImpl<O,T>( getParent(), expr, wireToParent );
            case NEW:
                return (X) new ConstructorImpl<O,T>( getParent(), expr, wireToParent );
            case PAREN:
                return (X) new ParenImpl<O,T>( getParent(), expr, wireToParent );
            case SELF:
                return (X) new SelfArgumentImpl<O,T>( getParent(), expr, wireToParent );
            case VAR:
            case FIELD:
                return (X) new VarArgumentImpl<O,T>( getParent(), expr, wireToParent );
            case NOT:
                return (X) new NotImpl<O,T>( getParent(), expr, wireToParent );
            case ASSIGN:
                return (X) new AssignImpl<O,T>( getParent(), expr, wireToParent );
            case GENERIC:
            default:
                throw new IllegalStateException( "Abstract argument required" );

        }
    }

    protected void swap( Expression xp, ExpressionSource<O> replacement ) {
        StructuralPropertyDescriptor spd = xp.getLocationInParent();
        if ( spd.isChildProperty() ) {
            xp.getParent().setStructuralProperty( spd, ((JdtExpressionWrapper)replacement).getExpression() );
            xp.delete();
        } else if ( spd.isChildListProperty() ) {
            xp.delete();
            ((JdtExpressionWrapper) origin).wireExpression( replacement );
        }
    }

    protected T applyPostFixOperator( PostfixExpression.Operator op ) {
        PostfixExpression post = ast.newPostfixExpression();
        post.setOperator( op );
        OperatorImpl<O,T> operator = new OperatorImpl<O,T>( origin, post, true );

        getExpression().delete();
        post.setOperand( getExpression() );

        return (T) ((JdtExpressionWrapper) origin).asExpressionSource();
    }

    protected T getParent() {
        return origin;
    }

    protected abstract Expression getExpression();

}
