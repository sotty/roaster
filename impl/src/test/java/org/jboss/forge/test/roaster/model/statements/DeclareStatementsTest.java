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

public class DeclareStatementsTest
{

   @Test
   public void testDeclare() throws Exception
   {
      String target = "java.lang.Integer y=new java.lang.Integer();";
      MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo( String x )" );

      method.openBody().doDeclare().type( Integer.class ).name( "y" ).init().newInstance( Integer.class )
             .noMore().done();

      assertEquals( target, method.getBody().trim() );
   }

   @Test
   public void testDeclarePrimitive() throws Exception
   {
      String target = "int y=0;";
      MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo( String x )" );

      method.openBody().doDeclare().type( int.class ).name( "y" ).initDefault().done();

      assertEquals( target, method.getBody().trim() );
   }


    @Test
    public void testDeclareAndInit() throws Exception
    {
        String target = "java.lang.Integer y=0;";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo( String x )" );

        method.openBody().doDeclare().type( Integer.class ).name( "y" )
                            .init()
                                .literal( 0 )
                            .noMore()
                         .done();

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testSimpleAssignment() throws Exception
    {
        String target = "this.name=x;";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo( String x )" );

        method.openBody().doAssign().toField( "name" ).varExpr( "x" );

        assertEquals( target, method.getBody().trim() );
    }

    @Test
    public void testAssignmentExpr() throws Exception
    {
        String target = "this.name=this.name + \" Doe\";";
        MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void foo( String x )" );

        method.openBody().doAssign()
                            .toField( "name" )
                            .expr().operator( "+" ).args()
                                .field( "name" ).next()
                                .literal( " Doe" );

        assertEquals( target, method.getBody().trim() );
    }



}
