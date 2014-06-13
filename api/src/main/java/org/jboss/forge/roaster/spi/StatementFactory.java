/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.spi;

import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.BlockSource;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.statements.AssertStatement;
import org.jboss.forge.roaster.model.statements.AssignmentStatement;
import org.jboss.forge.roaster.model.statements.BlockStatement;
import org.jboss.forge.roaster.model.statements.BreakStatement;
import org.jboss.forge.roaster.model.statements.CallStatement;
import org.jboss.forge.roaster.model.statements.ContinueStatement;
import org.jboss.forge.roaster.model.statements.DeclareStatement;
import org.jboss.forge.roaster.model.statements.DoWhileStatement;
import org.jboss.forge.roaster.model.statements.ForEachStatement;
import org.jboss.forge.roaster.model.statements.ForStatement;
import org.jboss.forge.roaster.model.statements.IfStatement;
import org.jboss.forge.roaster.model.statements.InvokeStatement;
import org.jboss.forge.roaster.model.statements.ReturnStatement;
import org.jboss.forge.roaster.model.statements.SuperStatement;
import org.jboss.forge.roaster.model.statements.SwitchStatement;
import org.jboss.forge.roaster.model.statements.SynchStatement;
import org.jboss.forge.roaster.model.statements.ThisStatement;
import org.jboss.forge.roaster.model.statements.ThrowStatement;
import org.jboss.forge.roaster.model.statements.TryCatchStatement;
import org.jboss.forge.roaster.model.statements.WhileStatement;

public interface StatementFactory<O extends JavaSource<O>> {

    /**
     * Creates a return statement 
     * @return
     */
    ReturnStatement<O,?> newReturn();

    /**
     * Creates a variable assignment statement 
     * @return
     */
    AssignmentStatement<O,?> newAssign();

    /**
     * Creates a variable declaration statement 
     * @return
     */
    DeclareStatement<O,?> newDeclare();

    /**
     * Creates a method invocation statement 
     * @return
     */
    InvokeStatement<O,?> newInvoke();

    /**
     * Creates an if statement 
     * @return
     */
    IfStatement<O,?> newIf();

    /**
     * Creates a while statement 
     * @return
     */
    WhileStatement<O,?> newWhile();

    /**
     * Creates a for statement 
     * @return
     */
    ForStatement<O,?> newFor();

    /**
     * Creates an enhanced for statement
     * @return
     */
    ForEachStatement<O,?> newForEach();

    DoWhileStatement<O,?> newDoWhile();

    BreakStatement<O,?> newBreak();

    ContinueStatement<O,?> newContinue();

    ThrowStatement<O,?> newThrow();

    TryCatchStatement<O,?> newTryCatch();

    SynchStatement<O,?> newSynchronized();

    ThisStatement<O,?> newThis();

    SuperStatement<O,?> newSuper();

    AssertStatement<O,?> newAssert();

    SwitchStatement<O,?> newSwitch();

    CallStatement<O,?> newCall();

    /**
     * Creates a block statement
     * @return
     */
    BlockStatement<O,?> newBlock();

}
