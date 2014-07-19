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

      method.setBody().addReturn().done();

      assertEquals( target, method.getBody().trim() );
   }

   @Test
   public void testReturnArg() throws Exception
   {
      String target = "return x;";
      MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public String echo( String x )" );

      method.setBody().addReturn().variable( "x" );

      assertEquals( target, method.getBody().trim() );
   }

   @Test
   public void testReturnExpr() throws Exception
   {
      String target = "return x + 5;";
      MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public String echo( String x )" );

      method.setBody().addReturn().setReturnExpression()
                .operator( "+" ).addArgument()
                        .variableRef( "x" ).nextArgument()
                        .literal( 5 )
            ;

      assertEquals( target, method.getBody().trim() );
   }

    @Test
    public void testReturnInvoke() throws Exception
    {
        String target = "return java.util.UUID.randomUUID();";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public String foo()" );

        method.setBody().addReturn().invoke().staticMethod( "randomUUID", UUID.class );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testReturnInvokeWithArgs() throws Exception
    {
        String target = "return java.util.UUID.fromString(\"xyz\");";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public String foo()" );

        method.setBody().addReturn()
                .invoke()
                .staticMethod( "fromString", UUID.class )
                    .addArgument()
                        .literal( "xyz" )
        ;

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testReturnTernary() throws Exception
    {
        String target = "return x > 0 ? 4 : 5;";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public int foo( int x )" );

        method.setBody().addReturn()
                .setReturnExpression().ternary()
                    .setCondition().operator( ">" ).addArgument().variableRef( "x" ).nextArgument().literal( 0 ).noMore().noMore()
                    .setIfExpression().literal( 4 ).noMore()
                    .setElseExpression().literal( 5 );
        ;

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testReturnTernaryWithParen() throws Exception
    {
        String target = "return 1 + (x > 3 ? 4 : 5) + 2;";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public int foo( int x )" );

        method.setBody().addReturn()
                .setReturnExpression()
                .operator( "+" ).addArgument()
                    .literal( 1 ).nextArgument()
                    .paren().ternary()
                        .setCondition().operator( ">" ).addArgument().variableRef( "x" ).nextArgument().literal( 3 ).noMore().noMore()
                        .setIfExpression().literal( 4 ).noMore()
                        .setElseExpression().literal( 5 ).noMore()
                            .end()
                    .close()
                .nextArgument()
                    .literal( 2 );
        ;

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testReturnCast() throws Exception
    {
        String target = "return (long)(x);";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public long foo( int x )" );

        method.setBody().addReturn()
                .setReturnExpression().cast( "long" ).variableRef( "x" );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testReturnNull() throws Exception
    {
        String target = "return null;";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public String foo()" );

        method.setBody().addReturn().nullLiteral();

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testReturnChain() throws Exception
    {
        String target = "return getName().bar();";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public String foo()" );

        method.setBody().addReturn().setReturnExpression()
                .getter( "name", "String" ).dot()
                .invoke().method( "bar" );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testReturnChainWithField() throws Exception
    {
        String target = "return getName().baz;";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public String foo()" );

        method.setBody().addReturn().setReturnExpression()
                .getter( "name", "String" ).dot()
                .field( "baz" );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testReturnChainWithFields() throws Exception
    {
        String target = "return this.foo.bar.baz;";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public String foo()" );

        method.setBody().addReturn().setReturnExpression().field( "foo" ).dot().field( "bar" ).dot().field( "baz" );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testReturnChainWithFieldsAndMethods() throws Exception
    {
        String target = "return this.foo.getBar().baz.doSomething(this.x.y).getRes();";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public String foo()" );

        method.setBody().addReturn().setReturnExpression()
                .field( "foo" ).dot()
                .getter( "bar", String.class.getName() ).dot()
                .field( "baz" ).dot()
                .invoke().method( "doSomething" ).addArgument()
                    .field( "x" )
                    .dot().field( "y" ).noMore().dot()
                .invoke().method( "getRes" );
                ;

        assertEquals( target, method.getBody().trim() );
    }

}
