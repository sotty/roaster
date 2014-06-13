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

public class IfStatementsTest
{
   @Test
   public void testSimpleIfWithCondition() throws Exception
   {
      String target = "if (x != null) {\n  x=null;\n}\n else {\n  x=y;\n  x=x + 1;\n}";
      MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void hello()" );

      method.openBody().doIf()
            .condition()
                .operator( "!=" ).args()
                    .var( "x" ).next()
                .nil().noMore()
                .noMore()
            .thenDo()
                .doAssign().toVar( "x" ).expr().nil().noMore().done()
            .close()
            .elseDo()
                .doAssign().toVar( "x" ).varExpr( "y" ).done()
                .doAssign().toVar( "x" ).expr().operator( "+" ).args().var( "x" ).next().literal( 1 ).noMore().noMore().done()
            .close()
        .done();

      assertEquals( target, method.getBody().trim() );
   }

  @Test
   public void testIfWithDotAccessor() throws Exception
   {
      String target = "if (this.x != y.z) {\n  return false;\n}";
      MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void hello()" );

      method.openBody().doIf()
              .condition().operator( "!=" ).args()
                    .field( "x" ).next()
                    .var( "y" )
                        .dot()
                            .field( "z" ).noMore().noMore()
              .thenDo()
                    .doReturn().no();

       assertEquals( target, method.getBody().trim() );
   }

   @Test
   public void testIfWithNegation() throws Exception
   {
      String target = "if (!x) {\n  return false;\n}";
      MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void hello()" );

      method.openBody().doIf()
              .condition().not().var( "x" ).noMore()
              .thenDo()
                    .doReturn().no();

       assertEquals( target, method.getBody().trim() );
   }

   @Test
   public void testIfWithDoubleNegation() throws Exception
   {
      String target = "if (!!x) {\n  return false;\n}";
      MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void hello()" );

      method.openBody().doIf()
              .condition().not().not().var( "x" ).noMore()
              .thenDo()
                    .doReturn().no();

       assertEquals( target, method.getBody().trim() );
   }




}
