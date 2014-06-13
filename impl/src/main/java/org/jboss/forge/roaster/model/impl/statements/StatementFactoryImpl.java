/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.impl.statements;

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
import org.jboss.forge.roaster.model.statements.ExpressionStatement;
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
import org.jboss.forge.roaster.spi.StatementFactory;

public class StatementFactoryImpl<O extends JavaSource<O>,
                                  P extends BlockSource<O,? extends BlockHolder<O>,P>>
        implements StatementFactory<O> {
    
    public StatementFactoryImpl() { }


    public ReturnStatement<O,P> newReturn() {
        ReturnStatementImpl<O,P> returnSt = new ReturnStatementImpl<O,P>();
        return returnSt;
    }

    public AssignmentStatement<O,P> newAssign() {
        AssignStatementImpl<O,P> assign = new AssignStatementImpl<O,P>();
        return assign;
    }

    public DeclareStatement<O,P> newDeclare() {
        DeclareStatementImpl<O,P> declare = new DeclareStatementImpl<O,P>();
        return declare;
    }

    public InvokeStatement<O,P> newInvoke() {
        InvokeStatementImpl<O,P> invoke = new InvokeStatementImpl<O,P>();
        return invoke;
    }

    public IfStatement<O,P> newIf() {
        IfStatementImpl<O,P> iff = new IfStatementImpl<O,P>();
        return iff;
    }

    public WhileStatement<O,P> newWhile() {
        WhileStatementImpl<O,P> loop = new WhileStatementImpl<O,P>();
        return loop;
    }

    public DoWhileStatement<O,P> newDoWhile() {
        DoWhileStatementImpl<O,P> loop = new DoWhileStatementImpl<O,P>();
        return loop;
    }

    public ForStatement<O,P> newFor() {
        ForStatementImpl<O,P> loop = new ForStatementImpl<O,P>();
        return loop;
    }

    public ForEachStatement<O,P> newForEach() {
        ForEachStatementImpl<O,P> loop = new ForEachStatementImpl<O,P>();
        return loop;
    }

    @Override
    public BlockStatement<O,P> newBlock() {
        BlockStatementImpl<O,P> child = new BlockStatementImpl<O,P>();
        return child;
    }

    @Override
    public BreakStatement<O,P> newBreak() {
        BreakStatement<O,P> stop = new BreakStatementImpl<O,P>();
        return stop;
    }

    @Override
    public ContinueStatement<O,P> newContinue() {
        ContinueStatement<O,P> goon = new ContinueStatementImpl<O,P>();
        return goon;
    }
    
    @Override
    public ThrowStatement<O,P> newThrow() {
        ThrowStatement<O,P> throwX = new ThrowStatementImpl<O,P>();
        return throwX;
    }

    @Override
    public TryCatchStatement<O,P> newTryCatch() {
        TryCatchStatement<O,P> tryCatch = new TryCatchStatementImpl<O,P>();
        return tryCatch;
    }

    @Override
    public SynchStatement<O,P> newSynchronized() {
        SynchStatement<O,P> synch = new SynchStatementImpl<O,P>();
        return synch;
    }

    @Override
    public ThisStatement<O,P> newThis() {
        ThisStatement<O,P> self = new ThisStatementImpl<O,P>();
        return self;
    }

    @Override
    public SuperStatement<O,P> newSuper() {
        SuperStatement<O,P> sup = new SuperStatementImpl<O,P>();
        return sup;
    }

    @Override
    public AssertStatement<O,P> newAssert() {
        AssertStatement<O,P> sup = new AssertStatementImpl<O,P>();
        return sup;
    }

    @Override
    public SwitchStatement<O,P> newSwitch() {
        SwitchStatement<O,P> sup = new SwitchStatementImpl<O,P>();
        return sup;
    }

    @Override
    public CallStatement<O,P> newCall() {
        ExpressionStatementImpl<O,P> call = new ExpressionStatementImpl<O,P>();
        return call;
    }
}
