/*
 * Copyright 2012-2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.forge.test.roaster.model.statements;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvokeStatementsTest
{

    @Test
    public void testInvoke() throws Exception
    {
        String target = "java.lang.Math.random();";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo()" );

        method.setBody().addInvoke()
                .staticMethod( "random", Math.class );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testInvokeWithArg() throws Exception
    {
        String target = "java.lang.Math.round(2.4);";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo()" );

        method.setBody().addInvoke()
                .addArgument()
                    .literal( 2.4f )
                .noMore()
                .staticMethod( "round", Math.class );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testInvokeWithArgs() throws Exception
    {
        String target = "java.lang.Math.min(3,4);";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo()" );

        method.setBody().addInvoke()
                .addArgument()
                    .literal( 3 ).nextArgument()
                    .literal( 4 )
                .noMore()
                .staticMethod( "min", Math.class );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testInvokeWithArgsRevOrder() throws Exception
    {
        String target = "java.lang.Math.min(3,4);";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo()" );

        method.setBody().addInvoke()
                .addArgument()
                    .literal( 3 ).nextArgument()
                    .literal( 4 ).noMore()
                .staticMethod( "min", Math.class );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testNestedInvoke() throws Exception
    {
        String target = "java.lang.Math.min(java.lang.Math.max(java.lang.Math.round(3.4),6),4);";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo()" );

        method.setBody().addInvoke()
                .addArgument()
                    .invoke()
                        .addArgument()
                            .invoke()
                                .staticMethod( "round", Math.class )
                                .addArgument().literal( 3.4 ).noMore()
                            .nextArgument()
                            .literal( 6 ).noMore()
                        .staticMethod( "max", Math.class )
                    .nextArgument()
                    .literal( 4 ).noMore()
                .staticMethod( "min", Math.class )
                ;

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testInvokeWithArgz() throws Exception
    {
        String target = "java.lang.Math.min(2 + 3,4 * 5);";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo()" );

        method.setBody().addInvoke()
                .addArgument()
                    .operator( "+" ).addArgument()
                        .literal( 2 ).nextArgument()
                        .literal( 3 ).noMore()
                    .nextArgument()
                    .operator( "*" ).addArgument()
                        .literal( 4 ).nextArgument()
                        .literal( 5 ).noMore()
                .noMore()
                .staticMethod( "min", Math.class )
                ;

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testInvokeOnThis() throws Exception
    {
        String target = "this.toString();";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo()" );

        method.setBody().addInvoke()
                .on().thisLiteral().noMore()
                .method( "toString" );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testInvokeOnLongChain() throws Exception
    {
        String target = "this.getName().foo().bar().baz.toString();";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo()" );

        method.setBody().addInvoke()
                .on().thisLiteral().dot().getter( "name", "String" ).dot().invoke().method( "foo" ).dot().invoke().method( "bar" ).dot().field( "baz" ).noMore()
                .method( "toString" );

        assertEquals( target, method.getBody().trim() );
    }

}
