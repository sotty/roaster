/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.statements;

import org.jboss.forge.roaster.model.source.BlockSource;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.spi.StatementFactory;

import java.util.ServiceLoader;

public abstract class Statements {

    protected static StatementFactory factory;

    @SuppressWarnings( "unchecked" )
    protected static <O extends JavaSource<O>> StatementFactory<O> getFactory() {
        synchronized ( Statements.class ) {
            ServiceLoader<StatementFactory> sl = ServiceLoader.load( StatementFactory.class, Statements.class.getClassLoader() );
            if ( sl.iterator().hasNext() ) {
                factory = sl.iterator().next();
            } else {
                throw new IllegalStateException( "No StatementFactory implementation available, unable to continue" );
            }
        }
        return factory;
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> ReturnStatement<O,?> newReturn() {
        return (ReturnStatement<O, ?>) getFactory().newReturn();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> IfStatement<O,BlockSource<O,?,?>> newIf() {
        return (IfStatement<O, BlockSource<O, ?, ?>>) getFactory().newIf();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> AssignmentStatement<O,BlockSource<O,?,?>> newAssign() {
        return (AssignmentStatement<O, BlockSource<O, ?, ?>>) getFactory().newAssign();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> DeclareStatement<O,BlockSource<O,?,?>> newDeclare() {
        return (DeclareStatement<O, BlockSource<O, ?, ?>>) getFactory().newDeclare();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> ForStatement<O,BlockSource<O,?,?>> newFor() {
        return (ForStatement<O, BlockSource<O, ?, ?>>) getFactory().newFor();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> ForEachStatement<O,BlockSource<O,?,?>> newForEach() {
        return (ForEachStatement<O, BlockSource<O, ?, ?>>) getFactory().newForEach();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> InvokeStatement<O,BlockSource<O,?,?>> newInvoke() {
        return (InvokeStatement<O, BlockSource<O, ?, ?>>) getFactory().newInvoke();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> WhileStatement<O,BlockSource<O,?,?>> newWhile() {
        return (WhileStatement<O, BlockSource<O, ?, ?>>) getFactory().newWhile();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> DoWhileStatement<O,BlockSource<O,?,?>> newDoWhile() {
        return (DoWhileStatement<O, BlockSource<O, ?, ?>>) getFactory().newDoWhile();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> BlockStatement<O,BlockSource<O,?,?>> newBlock() {
        return (BlockStatement<O, BlockSource<O, ?, ?>>) getFactory().newBlock();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> BreakStatement<O,BlockSource<O,?,?>> newBreak() {
        return (BreakStatement<O, BlockSource<O, ?, ?>>) getFactory().newBreak();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> ContinueStatement<O,BlockSource<O,?,?>> newContinue() {
        return (ContinueStatement<O, BlockSource<O, ?, ?>>) getFactory().newContinue();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> ThrowStatement<O,BlockSource<O,?,?>> newThrow() {
        return (ThrowStatement<O, BlockSource<O, ?, ?>>) getFactory().newThrow();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> TryCatchStatement<O,BlockSource<O,?,?>> newTryCatch() {
        return (TryCatchStatement<O, BlockSource<O, ?, ?>>) getFactory().newTryCatch();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> SynchStatement<O,BlockSource<O,?,?>> newSynchronized() {
        return (SynchStatement<O, BlockSource<O, ?, ?>>) getFactory().newSynchronized();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> ThisStatement<O,BlockSource<O,?,?>> newThis() {
        return (ThisStatement<O, BlockSource<O, ?, ?>>) getFactory().newThis();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> SuperStatement<O,BlockSource<O,?,?>> newSuper() {
        return (SuperStatement<O, BlockSource<O, ?, ?>>) getFactory().newSuper();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> AssertStatement<O,BlockSource<O,?,?>> newAssert() {
        return (AssertStatement<O, BlockSource<O, ?, ?>>) getFactory().newAssert();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> SwitchStatement<O,BlockSource<O,?,?>> newSwitch() {
        return (SwitchStatement<O, BlockSource<O, ?, ?>>) getFactory().newSwitch();
    }

    @SuppressWarnings( "unchecked" )
    public static <O extends JavaSource<O>> CallStatement<O,BlockSource<O,?,?>> newCall() {
        return (CallStatement<O, BlockSource<O, ?, ?>>) getFactory().newCall();
    }
}
