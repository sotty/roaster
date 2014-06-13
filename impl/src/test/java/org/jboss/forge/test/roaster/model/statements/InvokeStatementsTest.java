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

import java.util.Collections;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class InvokeStatementsTest
{

    @Test
    public void testInvoke() throws Exception
    {
        String target = "java.lang.Math.random();";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo()" );

        method.openBody().doInvoke()
                .on( Math.class )
                .method( "random" );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testInvokeWithArg() throws Exception
    {
        String target = "java.lang.Math.round(2.4);";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo()" );

        method.openBody().doInvoke()
                .on( Math.class )
                .args()
                    .literal( 2.4f )
                .noMore()
                .method( "round" );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testInvokeWithArgs() throws Exception
    {
        String target = "java.lang.Math.min(3,4);";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo()" );

        method.openBody().doInvoke()
                .on( Math.class )
                .args()
                    .literal( 3 ).next()
                    .literal( 4 )
                .noMore()
                .method( "min" );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testInvokeWithArgsRevOrder() throws Exception
    {
        String target = "java.lang.Math.min(3,4);";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo()" );

        method.openBody().doInvoke()
                .args()
                    .literal( 3 ).next()
                    .literal( 4 ).noMore()
                .method( "min" )
                .on( Math.class );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testNestedInvoke() throws Exception
    {
        String target = "java.lang.Math.min(java.lang.Math.max(java.lang.Math.round(3.4),6),4);";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo()" );

        method.openBody().doInvoke()
                .on( Math.class )
                .args()
                    .invoke()
                        .on( Math.class )
                        .args()
                            .invoke()
                                .on( Math.class )
                                .method( "round" )
                                .args().literal( 3.4 ).noMore()
                            .next()
                            .literal( 6 ).noMore()
                        .method( "max" )
                    .next()
                    .literal( 4 ).noMore()
                .method( "min" )
                ;

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testInvokeWithArgz() throws Exception
    {
        String target = "java.lang.Math.min(2 + 3,4 * 5);";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo()" );

        method.openBody().doInvoke()
                .on( Math.class )
                .args()
                    .operator( "+" ).args()
                        .literal( 2 ).next()
                        .literal( 3 ).noMore()
                    .next()
                    .operator( "*" ).args()
                        .literal( 4 ).next()
                        .literal( 5 ).noMore()
                .noMore()
                .method( "min" )
                ;

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testInvokeOnThis() throws Exception
    {
        String target = "this.toString();";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo()" );

        method.openBody().doInvoke()
                .on().self().noMore()
                .method( "toString" );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testInvokeOnLongChain() throws Exception
    {
        String target = "this.getName().foo().bar().baz.toString();";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo()" );

        method.openBody().doInvoke()
                .on().self().dot().getter( "name", "String" ).dot().invoke().method( "foo" ).dot().invoke().method( "bar" ).dot().field( "baz" ).noMore()
                .method( "toString" );

        assertEquals( target, method.getBody().trim() );
    }

}
