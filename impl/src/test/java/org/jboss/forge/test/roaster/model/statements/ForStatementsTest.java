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

      method.setBody().addFor()
              .setCondition().operator( "<" ).addArgument().variableRef( "j" ).nextArgument().literal( 100 ).noMore().noMore()
              .addDeclaration().setVariable( "j", int.class ).init().literal( 0 ).noMore()
              .addDeclaration().setVariable( "k", int.class ).init().literal( 0 ).noMore()
              .addUpdate().variableRef( "j" ).inc()
              .addUpdate().assign( "=" )
                    .setVariableLeftExpression( "k" )
                    .setRightExpression().operator( "+" ).addArgument().variableRef( "k" ).nextArgument().literal( 2 ).noMore().noMore()
                    .noMore()
              .setBody()
                .addInvoke()
                    .on().classLiteral( System.class ).dot().field( "out" ).noMore()
                    .method( "println" )
                    .addArgument().variableRef( "j" );

      assertEquals( target, method.getBody().trim() );
   }

   @Test
   public void testSimpleForEachWithPrint() throws Exception
   {

      String target = "for (java.lang.String name : p.getNames()) {\n  java.lang.System.out.println(name);\n}";
      MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void hello()" );

      method.setBody().addForEach()
                    .iterator( "name", String.class )
                    .in().variableRef( "p" ).dot().getter( "names", String.class.getName() ).noMore()
              .repeat()
                .addInvoke()
                    .on().classLiteral( System.class ).dot().field( "out" ).noMore()
                    .method( "println" )
                    .addArgument().variableRef( "name" );

      assertEquals( target, method.getBody().trim() );
   }

}
