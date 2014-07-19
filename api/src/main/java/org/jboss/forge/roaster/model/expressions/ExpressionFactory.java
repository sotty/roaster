package org.jboss.forge.roaster.model.expressions;


import org.jboss.forge.roaster.model.source.JavaSource;

import java.util.Arrays;
import java.util.regex.MatchResult;

public interface ExpressionFactory<O extends JavaSource<O>, T extends ExpressionSource<O>>
    extends Accessor<O,T>,
            LiteralBuilder<O,T> {

    public ConstructorExpression<O,T> newInstance( String klass );

    public ConstructorExpression<O,T> newInstance( Class<?> klass );

    public Variable<O,T> variableRef( String varName );

    public OperatorExpression<O,T> operator( String op );

    public TernaryExpression<O,T> ternary();

    public ExpressionFactory<O,T> cast( String klass );

    public ExpressionFactory<O,T> cast( Class klass );

    public ExpressionFactory<O,ParenExpression<O,T>> paren();

    public UnaryExpression<O,T> not();

    public AssignExpression<O,T> assign( String operator );

}
