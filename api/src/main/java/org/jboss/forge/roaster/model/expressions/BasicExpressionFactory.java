/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.expressions;


import org.jboss.forge.roaster.model.ExpressionHolder;
import org.jboss.forge.roaster.model.expressions.AccessBuilder;
import org.jboss.forge.roaster.model.expressions.Accessor;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.expressions.ArrayConstructorExpression;
import org.jboss.forge.roaster.model.expressions.ArrayInit;
import org.jboss.forge.roaster.model.expressions.AssignExpression;
import org.jboss.forge.roaster.model.expressions.Assignment;
import org.jboss.forge.roaster.model.expressions.CastExpression;
import org.jboss.forge.roaster.model.expressions.ConstructorBuilder;
import org.jboss.forge.roaster.model.expressions.DeclareExpression;
import org.jboss.forge.roaster.model.expressions.Expression;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.expressions.InstanceofExpression;
import org.jboss.forge.roaster.model.expressions.LiteralBuilder;
import org.jboss.forge.roaster.model.expressions.NonPrimitiveExpression;
import org.jboss.forge.roaster.model.expressions.Op;
import org.jboss.forge.roaster.model.expressions.OperatorExpression;
import org.jboss.forge.roaster.model.expressions.ParenExpression;
import org.jboss.forge.roaster.model.expressions.PrefixOp;
import org.jboss.forge.roaster.model.expressions.Super;
import org.jboss.forge.roaster.model.expressions.TernaryExpression;
import org.jboss.forge.roaster.model.expressions.UnaryExpression;
import org.jboss.forge.roaster.model.expressions.Variable;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface BasicExpressionFactory<O extends JavaSource<O>,
                                        P extends ExpressionHolder<O>>
    extends LiteralBuilder<O,P>,
            ConstructorBuilder<O,P> {

    public Variable<O,P> var( String varName );

    public OperatorExpression<O,P> operator( Op op );

    public UnaryExpression<O,P> operator( PrefixOp operator, Argument<O,?,?> arg );

    public TernaryExpression<O,P> ternary();

    public CastExpression<O,P> cast( String klass, ExpressionSource<O,?,?> arg );

    public CastExpression<O,P> cast( Class klass, ExpressionSource<O,?,?> arg );

    public ParenExpression<O,P> paren( ExpressionSource<O,?,?> inner );

    public UnaryExpression<O,P> not( Argument<O,?,?> arg );

    public AssignExpression<O,P> assign( Assignment operator );

    public DeclareExpression<O,P> declare( String klass, String name );

    public DeclareExpression<O,P> declare( Class klass, String name );

    public Accessor<O,P,?> classStatic( String klass );

    public Accessor<O,P,?> classStatic( Class klass );

    public InstanceofExpression<O,P> instanceOf( String klass, ExpressionSource<O,?,?> arg );

    public InstanceofExpression<O,P> instanceOf( Class klass, ExpressionSource<O,?,?> arg );

    public Super<O,P> sup();

    public ArrayInit<O,ArrayConstructorExpression<O,P>> vec();
}
