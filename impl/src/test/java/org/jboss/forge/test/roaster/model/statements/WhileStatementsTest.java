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

public class WhileStatementsTest
{
   @Test
   public void testSimpleWhile() throws Exception
   {
      String target = "while (x < 0) {\n  x=x + 1;\n}";
      MethodSource<JavaClassSource> method = Roaster.create( JavaClassSource.class ).addMethod( "public void hello()" );

      method.openBody().doWhile()
            .condition().operator( "<" ).args()
                .var( "x" ).next()
                .literal( 0 ).noMore()
            .noMore()
            .repeat()
                .doAssign().toVar( "x" ).expr().operator( "+" ).args().var( "x" ).next().literal( 1 ).noMore().noMore().done()
            .close()
            .done().close();

      assertEquals( target, method.getBody().trim() );
   }

}
