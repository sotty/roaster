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

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class ReturnStatementsTest
{
   @Test
   public void testReturn() throws Exception
   {
      String target = "return;";
      MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void hello()" );

      method.openBody().doReturn().done();

      assertEquals( target, method.getBody().trim() );
   }

   @Test
   public void testReturnArg() throws Exception
   {
      String target = "return x;";
      MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public String echo( String x )" );

      method.openBody().doReturn().var( "x" ).done();

      assertEquals( target, method.getBody().trim() );
   }

   @Test
   public void testReturnExpr() throws Exception
   {
      String target = "return x + 5;";
      MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public String echo( String x )" );

      method.openBody().doReturn().expr()
                .operator( "+" ).args()
                        .var( "x" ).next()
                        .literal( 5 )
            ;

      assertEquals( target, method.getBody().trim() );
   }

    @Test
    public void testReturnInvoke() throws Exception
    {
        String target = "return java.util.UUID.randomUUID();";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public String foo()" );

        method.openBody().doReturn().invoke().on( UUID.class ).method( "randomUUID" );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testReturnInvokeWithArgs() throws Exception
    {
        String target = "return java.util.UUID.fromString(\"xyz\");";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public String foo()" );

        method.openBody().doReturn()
                .invoke()
                    .on( UUID.class )
                    .args()
                        .literal( "xyz" )
                    .noMore()
                .method( "fromString" )
        ;

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testReturnTernary() throws Exception
    {
        String target = "return x > 0 ? 4 : 5;";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public int foo( int x )" );

        method.openBody().doReturn()
                .expr().ternary()
                    .condition().operator( ">" ).args().var( "x" ).next().literal( 0 ).noMore().noMore()
                    .yes().literal( 4 ).noMore()
                    .no().literal( 5 );
        ;

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testReturnTernaryWithParen() throws Exception
    {
        String target = "return 1 + (x > 3 ? 4 : 5) + 2;";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public int foo( int x )" );

        method.openBody().doReturn()
                .expr()
                .operator( "+" ).args()
                    .literal( 1 ).next()
                    .paren().ternary()
                        .condition().operator( ">" ).args().var( "x" ).next().literal( 3 ).noMore().noMore()
                        .yes().literal( 4 ).noMore()
                        .no().literal( 5 ).noMore()
                            .end()
                    .close()
                .next()
                    .literal( 2 );
        ;

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testReturnCast() throws Exception
    {
        String target = "return (long)(x);";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public long foo( int x )" );

        method.openBody().doReturn()
                .expr().cast()
                    .as( "long" )
                        .expr()
                            .var( "x" );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testReturnNull() throws Exception
    {
        String target = "return null;";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public String foo()" );

        method.openBody().doReturn().nil();

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testReturnChain() throws Exception
    {
        String target = "return getName().bar();";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public String foo()" );

        method.openBody().doReturn().expr()
                .getter( "name", "String" ).dot()
                .invoke().method( "bar" );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testReturnChainWithField() throws Exception
    {
        String target = "return getName().baz;";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public String foo()" );

        method.openBody().doReturn().expr()
                .getter( "name", "String" ).dot()
                .field( "baz" );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testReturnChainWithFields() throws Exception
    {
        String target = "return this.foo.bar.baz;";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public String foo()" );

        method.openBody().doReturn().expr().field( "foo" ).dot().field( "bar" ).dot().field( "baz" );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testReturnChainWithFieldsAndMethods() throws Exception
    {
        String target = "return this.foo.getBar().baz.doSomething(this.x.y).getRes();";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public String foo()" );

        method.openBody().doReturn().expr()
                .field( "foo" ).dot()
                .getter( "bar", String.class.getName() ).dot()
                .field( "baz" ).dot()
                .invoke().method( "doSomething" ).args()
                    .field( "x" )
                    .dot().field( "y" ).noMore().dot()
                .invoke().method( "getRes" );
                ;

        assertEquals( target, method.getBody().trim() );
    }

}
