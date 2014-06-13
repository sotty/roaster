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

public class ForStatementsTest
{

   @Test
   public void testSimpleForWithPrint() throws Exception
   {

      String target = "for (int j=0, k=0; j < 100; j++, k=k + 2) {\n  java.lang.System.out.println(j);\n}";
      MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void hello()" );

      method.openBody().doFor()
              .condition().operator( "<" ).args().var( "j" ).next().literal( 100 ).noMore().noMore()
              .declare().name( "j" ).type( int.class ).init().literal( 0 ).noMore()
              .declare().name( "k" ).type( int.class ).init().literal( 0 ).noMore()
              .update().var( "j" ).inc()
              .update().assign()
                    .to().var( "k" ).noMore()
                    .expr().operator( "+" ).args().var( "k" ).next().literal( 2 ).noMore().noMore()
                    .noMore()
              .repeat()
                .doInvoke()
                    .on().klass( System.class ).dot().field( "out" ).noMore()
                    .method( "println" )
                    .args().var( "j" );

      assertEquals( target, method.getBody().trim() );
   }

   @Test
   public void testSimpleForEachWithPrint() throws Exception
   {

      String target = "for (java.lang.String name : p.getNames()) {\n  java.lang.System.out.println(name);\n}";
      MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void hello()" );

      method.openBody().doForEach()
                    .iterator( "name" )
                    .type( String.class )
                    .in().var( "p" ).dot().getter( "names", String.class.getName() ).noMore()
              .repeat()
                .doInvoke()
                    .on().klass( System.class ).dot().field( "out" ).noMore()
                    .method( "println" )
                    .args().var( "name" );

      assertEquals( target, method.getBody().trim() );
   }

}
