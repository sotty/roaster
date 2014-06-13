/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.test.roaster.model.statements;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaClass;
import org.jboss.forge.roaster.model.expressions.BooleanLiteral;
import org.jboss.forge.roaster.model.expressions.CastExpression;
import org.jboss.forge.roaster.model.expressions.CharacterLiteral;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.expressions.Field;
import org.jboss.forge.roaster.model.expressions.Getter;
import org.jboss.forge.roaster.model.expressions.InstanceofExpression;
import org.jboss.forge.roaster.model.expressions.Literal;
import org.jboss.forge.roaster.model.expressions.NullLiteral;
import org.jboss.forge.roaster.model.expressions.NumberLiteral;
import org.jboss.forge.roaster.model.expressions.OperatorExpression;
import org.jboss.forge.roaster.model.expressions.PostFixExpression;
import org.jboss.forge.roaster.model.expressions.TernaryExpression;
import org.jboss.forge.roaster.model.expressions.ThisLiteral;
import org.jboss.forge.roaster.model.expressions.UnaryExpression;
import org.jboss.forge.roaster.model.expressions.Variable;
import org.jboss.forge.roaster.model.impl.expressions.StringLiteralImpl;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.statements.AssertStatement;
import org.jboss.forge.roaster.model.statements.BlockStatement;
import org.jboss.forge.roaster.model.statements.ReturnStatement;
import org.junit.Test;

import java.util.List;

import static org.jboss.forge.roaster.model.expressions.Expressions.classStatic;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RebuildStatementTest {

    @Test
    public void testRebuildReturnStatement() {
        String source = "public class Bean { " +
                        "public String echo(String x) { " +
                        "  return x; " +
                        "} " +
                        "}";
        List statements = parseSource( source );

        assertEquals(1, statements.size());
        assertTrue( statements.get( 0 ) instanceof ReturnStatement );
        assertEquals( "x", ((Variable) ((ReturnStatement) statements.get(0)).getReturn()).getName() );
    }

    @Test
    public void testRebuildReturnStatementBoolean() {
        String source = "public class Bean { " +
                        "public String echo(String x) { " +
                        "  return true; " +
                        "} " +
                        "}";
        List statements = parseSource( source );

        assertEquals(1, statements.size());
        assertTrue( statements.get( 0 ) instanceof ReturnStatement );
        assertTrue( ( (ReturnStatement) statements.get( 0 ) ).getReturn() instanceof BooleanLiteral );
    }

    @Test
    public void testRebuildReturnStatementWithCast() {
        String source = "public class Bean { " +
                        "public String echo(String x) { " +
                        "  return (my.own.Klass) foo; " +
                        "} " +
                        "}";
        List statements = parseSource( source );

        assertEquals(1, statements.size());
        assertTrue( statements.get( 0 ) instanceof ReturnStatement );
        assertTrue( ( (ReturnStatement) statements.get( 0 ) ).getReturn() instanceof CastExpression );
        CastExpression cast = (CastExpression) ( (ReturnStatement) statements.get( 0 ) ).getReturn();
        assertEquals( "my.own.Klass", cast.getType() );
        assertTrue( cast.getExpression() instanceof Variable );
    }

    @Test
    public void testRebuildReturnNull() {
        String source = "public class Bean { " +
                        "public String echo(String x) { " +
                        "  return null; " +
                        "} " +
                        "}";
        List statements = parseSource( source );

        assertEquals(1, statements.size());
        assertTrue( statements.get( 0 ) instanceof ReturnStatement );
        assertTrue( ( (ReturnStatement) statements.get( 0 ) ).getReturn() instanceof NullLiteral );
    }

    @Test
    public void testRebuildReturnWithInstanceOf() {
        String source = "public class Bean { " +
                        "public String echo(String x) { " +
                        "  return x instanceof Test; " +
                        "} " +
                        "}";
        List statements = parseSource( source );

        assertEquals(1, statements.size());
        assertTrue( statements.get( 0 ) instanceof ReturnStatement );
        assertTrue( ( (ReturnStatement) statements.get( 0 ) ).getReturn() instanceof InstanceofExpression );
        InstanceofExpression iof = (InstanceofExpression) ( (ReturnStatement) statements.get( 0 ) ).getReturn();
        assertEquals( "Test", iof.getTypeName() );
        assertTrue( iof.getExpression() instanceof Variable );
    }

    @Test
    public void testRebuildReturnWithPrefix() {
        String source = "public class Bean { " +
                        "public String echo(String x) { " +
                        "  return ++x; " +
                        "} " +
                        "}";
        List statements = parseSource( source );

        assertEquals(1, statements.size());
        assertTrue( statements.get( 0 ) instanceof ReturnStatement );
        assertTrue( ( (ReturnStatement) statements.get( 0 ) ).getReturn() instanceof UnaryExpression );
        UnaryExpression una = (UnaryExpression) ( (ReturnStatement) statements.get( 0 ) ).getReturn();
        assertTrue( una.getExpression() instanceof Variable );
        assertEquals( "++", una.getOperator() );
    }

    @Test
    public void testRebuildReturnWithPostfix() {
        String source = "public class Bean { " +
                        "public String echo(String x) { " +
                        "  return x--; " +
                        "} " +
                        "}";
        List statements = parseSource( source );

        assertEquals(1, statements.size());
        assertTrue( statements.get( 0 ) instanceof ReturnStatement );
        assertTrue( ( (ReturnStatement) statements.get( 0 ) ).getReturn() instanceof UnaryExpression );
        PostFixExpression una = (PostFixExpression) ( (ReturnStatement) statements.get( 0 ) ).getReturn();
        assertTrue( una.getExpression() instanceof Variable );
        assertEquals( "--", una.getOperator() );
    }

    @Test
    public void testComments() {
        String source = "public class Bean { " +
                        "public String echo(String x) { " +
                        "  // test" +
                        "  /* test2 */  " +
                        "} " +
                        "}";
        List statements = parseSource( source );
        // TODO tests are not supported yet
        assertTrue( statements.isEmpty() );
    }

    @Test
    public void testRebuildReturnStatementTernary() {
        String source = "public class Bean { " +
                        "public String echo(String x) { " +
                        "  return x > 0 ? 4 : 2; " +
                        "} " +
                        "}";
        List statements = parseSource( source );

        assertEquals(1, statements.size());
        assertTrue( statements.get( 0 ) instanceof ReturnStatement );
        assertTrue( ( (ReturnStatement) statements.get( 0 ) ).getReturn() instanceof TernaryExpression );
        TernaryExpression tern = (TernaryExpression) ((ReturnStatement) statements.get( 0 )).getReturn();
        assertTrue( tern.getCondition() instanceof OperatorExpression );
        assertTrue( tern.getThenExpression() instanceof NumberLiteral );
        assertTrue( tern.getElseExpression() instanceof NumberLiteral );
    }

    @Test
    public void testRebuildReturnStatementChar() {
        String source = "public class Bean { " +
                        "public String echo(String x) { " +
                        "  return 'x'; " +
                        "} " +
                        "}";
        List statements = parseSource( source );

        assertEquals(1, statements.size());
        assertTrue( statements.get( 0 ) instanceof ReturnStatement );
        assertTrue( ( (ReturnStatement) statements.get( 0 ) ).getReturn() instanceof CharacterLiteral );
        assertEquals( (Character) 'x', ( (CharacterLiteral) ( (ReturnStatement) statements.get( 0 ) ).getReturn() ).getValue() );
    }

    @Test
    public void testRebuildReturnStatementExpr() {
        String source = "public class Bean { " +
                        "public String echo(String x) { " +
                        "  return 3+5*4; " +
                        "} " +
                        "}";
        List statements = parseSource( source );

        assertEquals(1, statements.size());
        assertTrue( statements.get( 0 ) instanceof ReturnStatement );
        ExpressionSource ret = ( (ReturnStatement) statements.get( 0 ) ).getReturn();
        assertTrue( ret instanceof OperatorExpression );
        OperatorExpression opxp = (OperatorExpression) ret;
        assertTrue( opxp.getArguments().get( 0 ) instanceof NumberLiteral );
        assertEquals( "+", opxp.getOperator() );
        assertTrue( opxp.getArguments().get( 1 ) instanceof OperatorExpression );
        OperatorExpression sub = (OperatorExpression) opxp.getArguments().get( 1 );
        assertEquals( "*", sub.getOperator());
        assertEquals( 4L, ((NumberLiteral) sub.getArguments().get( 1 )).getValue());
        assertEquals( 5L, ((NumberLiteral) sub.getArguments().get( 0 )).getValue());
    }

    @Test
    public void testRebuildReturnStatementWithLiteralExpression() {
        String source = "public class Bean { " +
                        "public String echo(String x) { " +
                        "  return 4+x; " +
                        "} " +
                        "}";
        List statements = parseSource( source );

        assertEquals(1, statements.size());
        assertTrue( statements.get( 0 ) instanceof ReturnStatement );
        ExpressionSource ret = ( (ReturnStatement) statements.get( 0 ) ).getReturn();
        assertTrue( ret instanceof OperatorExpression );
        OperatorExpression opxp = (OperatorExpression) ret;
        assertTrue( opxp.getArguments().get( 0 ) instanceof NumberLiteral );
        assertTrue( opxp.getArguments().get( 1 ) instanceof Variable );
    }

    @Test
    public void testRebuildReturnStatementWithLiteralExpression2() {
        String source = "public class Bean { " +
                        "public String echo(String x) { " +
                        "  return 4+x+7; " +
                        "} " +
                        "}";
        List statements = parseSource( source );

        assertEquals(1, statements.size());
        assertTrue( statements.get( 0 ) instanceof ReturnStatement );
        ExpressionSource ret = ( (ReturnStatement) statements.get( 0 ) ).getReturn();
        assertTrue( ret instanceof OperatorExpression );
        OperatorExpression opxp = (OperatorExpression) ret;
        assertEquals( 3, opxp.getArguments().size() );
        assertTrue( opxp.getArguments().get( 0 ) instanceof NumberLiteral );
        assertTrue( opxp.getArguments().get( 1 ) instanceof Variable );
    }

    @Test
    public void testRebuildReturnStatementWithThis() {
        String source = "public class Bean { " +
                        "public String echo(String x) { " +
                        "  return this.x; " +
                        "} " +
                        "}";
        List statements = parseSource( source );

        assertEquals( 1, statements.size() );
        assertTrue( statements.get( 0 ) instanceof ReturnStatement );
        ExpressionSource val = ((ReturnStatement)statements.get( 0 )).getReturn();
        assertTrue( val instanceof Field );
        Field f = (Field) val;
        assertEquals( "x", f.getFieldName() );
        assertTrue( f.getSource() instanceof ThisLiteral );
    }

    @Test
    public void testRebuildReturnStatementWithChain() {
        String source = "public class Bean { " +
                        "public String echo(String x) { " +
                        "  return this.a.b.c.d; " +
                        "} " +
                        "}";
        List statements = parseSource( source );

        assertEquals( 1, statements.size() );
        assertTrue( statements.get( 0 ) instanceof ReturnStatement );
        ExpressionSource val = ((ReturnStatement)statements.get( 0 )).getReturn();
        Field f = null;
        String[] names = { "d", "c", "b", "a" };
        for ( int j = 0; j < 4; j++ ) {
            assertTrue( val instanceof Field );
            f = (Field) val;
            assertEquals( names[j], f.getFieldName() );
            val = f.getSource();
        }
        assertTrue( f.getSource() instanceof ThisLiteral );
    }

    @Test
    public void testRebuildReturnStatementWithGetter() {
        String source = "public class Bean { " +
                        "public String echo(String x) { " +
                        "  return getFoo(); " +
                        "} " +
                        "}";
        List statements = parseSource( source );

        assertEquals( 1, statements.size() );
        assertTrue( statements.get( 0 ) instanceof ReturnStatement );
        ExpressionSource val = ((ReturnStatement)statements.get( 0 )).getReturn();
        assertTrue( val instanceof Getter );
        assertEquals( "foo", ((Getter) val).getFieldName() );
    }

    @Test
    public void testRebuildAssertStatement() {
        String source = "public class Bean { " +
                        "public void foo() { " +
                        "  assert x : \"So be it\"; " +
                        "} " +
                        "} ";
        List statements  = parseSource( source );

        assertEquals( 1, statements.size() );
        assertTrue( statements.get( 0 ) instanceof AssertStatement );
        AssertStatement as = (AssertStatement) statements.get( 0 );
        assertEquals( "So be it", ((StringLiteralImpl)as.getMessage()).getValue() );
        assertEquals( "x", ((Variable) as.getAssertion()).getName() );
    }

    @Test
    public void testRebuildBlockStatement() {
        String source = "public class Bean { " +
                        "public int foo() { " +
                        "  { return 0; } " +
                        "} " +
                        "} ";
        List statements  = parseSource( source );

        assertEquals( 1, statements.size() );
        assertTrue( statements.get( 0 ) instanceof BlockStatement );
        BlockStatement bs = (BlockStatement) statements.get( 0 );
        assertEquals( 1, bs.getStatements().size() );
        assertTrue( bs.getStatements().get( 0 ) instanceof ReturnStatement );
        ReturnStatement rs = (ReturnStatement) bs.getStatements().get( 0 );
        ExpressionSource val = rs.getReturn();
        assertTrue( val instanceof NumberLiteral );
        assertEquals( 0L, ((NumberLiteral) val).getValue() );
    }

    private List parseSource( String source ) {
        JavaClassSource javaClassSource = Roaster.parse(JavaClass.class, source).asJavaClassSource();
        List<MethodSource<JavaClassSource>> methods = javaClassSource.getMethods();
        assertEquals(1, methods.size());
        MethodSource<JavaClassSource> method = methods.get(0);

        return method.getBodyAsBlock().getStatements();
    }

}
