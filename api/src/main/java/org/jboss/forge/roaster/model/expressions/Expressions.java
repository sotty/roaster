/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.expressions;

import org.jboss.forge.roaster.model.ExpressionHolder;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.spi.ExpressionFactory;

import java.util.ServiceLoader;

public abstract class Expressions {

    protected static ExpressionFactory factory;

    @SuppressWarnings( "unchecked" )
    protected static <O extends JavaSource<O>> ExpressionFactory<O,?,?> getFactory() {
        synchronized ( Expressions.class ) {
            ServiceLoader<ExpressionFactory> sl = ServiceLoader.load( ExpressionFactory.class, Expressions.class.getClassLoader() );
            if ( sl.iterator().hasNext() ) {
                factory = sl.iterator().next();
            } else {
                throw new IllegalStateException( "No ExpressionFactory implementation available, unable to continue" );
            }
        }
        return factory;
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> StringLiteral<O,?> literal( String val ) {
        return (StringLiteral<O, ?>) getFactory().literal( val );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> NumberLiteral<O,?> literal( Number val ) {
        return (NumberLiteral<O, ?>) getFactory().literal( val );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> CharacterLiteral<O,?> literal( Character val ) {
        return (CharacterLiteral<O, ?>) getFactory().literal( val );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> BooleanLiteral<O,?> literal( Boolean val ) {
        return (BooleanLiteral<O, ?>) getFactory().literal( val );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> Literal<O,?,?> zeroLiteral( String klass ) {
        return (Literal<O, ?, ?>) getFactory().zeroLiteral( klass );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> Literal<O,?,?> zeroLiteral( Class<?> klass ) {
        return (Literal<O, ?, ?>) getFactory().zeroLiteral( klass );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> ThisLiteral<O,?> thisLiteral() {
        return (ThisLiteral<O, ?>) getFactory().thisLiteral();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> NullLiteral<O,?> nullLiteral() {
        return (NullLiteral<O, ?>) getFactory().nullLiteral();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> ClassLiteral<O,?> classLiteral( String klass ) {
        return (ClassLiteral<O, ?>) getFactory().classLiteral( klass );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> ClassLiteral<O,?> classLiteral( Class<?> klass ) {
        return (ClassLiteral<O, ?>) getFactory().classLiteral( klass );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> Accessor<O,?,?> classStatic( String klass ) {
        return (Accessor<O, ?, ?>) getFactory().classStatic( klass );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> Accessor<O,?,?> classStatic( Class<?> klass ) {
        return (Accessor<O, ?, ?>) getFactory().classStatic( klass );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> ConstructorExpression<O,?> newInstance( String klass ) {
        return (ConstructorExpression<O, ?>) getFactory().newInstance( klass );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> ConstructorExpression<O,?> newInstance( Class<?> klass ) {
        return (ConstructorExpression<O, ?>) getFactory().newInstance( klass );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> ArrayConstructorExpression<O,?> newArray( String klass ) {
        return (ArrayConstructorExpression<O, ?>) getFactory().newArray( klass );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> ArrayConstructorExpression<O,?> newArray( Class<?> klass ) {
        return (ArrayConstructorExpression<O, ?>) getFactory().newArray( klass );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> ArrayInit<O,?> vec() {
        return (ArrayInit<O, ?>) getFactory().vec();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>>  Variable<O,?> var( String variable ) {
        return (Variable<O, ?>) getFactory().var( variable );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> OperatorExpression<O,?> operator( Op operator ) {
        return (OperatorExpression<O, ?>) getFactory().operator( operator );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> UnaryExpression<O,?> operator( PrefixOp operator, Argument arg ) {
        return (UnaryExpression<O, ?>) getFactory().operator( operator, arg );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> TernaryExpression<O,?> ternary() {
        return (TernaryExpression<O, ?>) getFactory().ternary();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> Argument<O,?,?> cast( String klass, ExpressionSource expression ) {
        return (Argument<O, ?, ?>) getFactory().cast( klass, expression );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> Argument<O,?,?> cast( Class klass, ExpressionSource expression ) {
        return (Argument<O, ?, ?>) getFactory().cast( klass, expression );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> ParenExpression<O,?> paren( ExpressionSource inner ) {
        return (ParenExpression<O, ?>) getFactory().paren( inner );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> UnaryExpression<O,?> not( Argument inner ) {
        return (UnaryExpression<O, ?>) getFactory().not( inner );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> AssignExpression<O,?> assign( Assignment operator ) {
        return (AssignExpression<O, ?>) getFactory().assign( operator );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> DeclareExpression<O,?> declare( String klass, String name ) {
        return (DeclareExpression<O, ?>) getFactory().declare( klass, name );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> DeclareExpression<O,?> declare( Class klass, String name ) {
        return (DeclareExpression<O, ?>) getFactory().declare( klass, name );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> MethodCallExpression<O,?> invoke( String method ) {
        return (MethodCallExpression<O, ?>) getFactory().invoke( method );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> Field<O,?> field( String field ) {
        return (Field<O, ?>) getFactory().field( field );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> Getter<O,?> getter( String field, String klass ) {
        return (Getter<O, ?>) getFactory().getter( field, klass );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> Getter<O,?> getter( String field, Class klass ) {
        return (Getter<O, ?>) getFactory().getter( field, klass );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> Setter<O,?> setter( String field, String klass, ExpressionSource value ) {
        return (Setter<O,?>) getFactory().setter( field, klass, value );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> Setter<O,?> setter( String field, Class klass, ExpressionSource value ) {
        return (Setter<O,?>) getFactory().setter( field, klass, value );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> Super<O,?> sup() {
        return (Super<O, ?>) getFactory().sup();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> InstanceofExpression<O,?> instanceOf( String klass, ExpressionSource expression ) {
        return (InstanceofExpression<O, ?>) getFactory().instanceOf( klass, expression );
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> InstanceofExpression<O,?> instanceOf( Class klass, ExpressionSource expression ) {
        return (InstanceofExpression<O, ?>) getFactory().instanceOf( klass, expression );
    }

}
