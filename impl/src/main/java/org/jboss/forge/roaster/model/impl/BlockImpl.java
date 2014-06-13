package org.jboss.forge.roaster.model.impl;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.jboss.forge.roaster.Origin;
import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.impl.expressions.JdtBlockWrapper;
import org.jboss.forge.roaster.model.impl.statements.AssignStatementImpl;
import org.jboss.forge.roaster.model.impl.statements.DeclareStatementImpl;
import org.jboss.forge.roaster.model.impl.statements.ForEachStatementImpl;
import org.jboss.forge.roaster.model.impl.statements.ForStatementImpl;
import org.jboss.forge.roaster.model.impl.statements.IfStatementImpl;
import org.jboss.forge.roaster.model.impl.statements.InvokeStatementImpl;
import org.jboss.forge.roaster.model.impl.statements.ReturnStatementImpl;
import org.jboss.forge.roaster.model.impl.statements.WhileStatementImpl;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.statements.AssignStatement;
import org.jboss.forge.roaster.model.statements.DeclareStatement;
import org.jboss.forge.roaster.model.statements.ForEachStatement;
import org.jboss.forge.roaster.model.statements.ForStatement;
import org.jboss.forge.roaster.model.statements.IfStatement;
import org.jboss.forge.roaster.model.statements.InvokeStatement;
import org.jboss.forge.roaster.model.statements.ReturnStatement;
import org.jboss.forge.roaster.model.statements.WhileStatement;

public class BlockImpl<O extends JavaSource<O>, T extends BlockHolder<O,?>>
        implements Block<O,T>,
                   Origin<T>,
                   JdtBlockWrapper<O,T> {

    private T origin;
    private org.eclipse.jdt.core.dom.Block block;

    public BlockImpl( T parent, AST ast ) {
        this.origin = parent;
        this.block = ast.newBlock();
        parent.wireBlock( this );
    }

    @Override
    public ReturnStatement<O, Block<O, T>> doReturn() {
        ReturnStatementImpl<O,Block<O,T>> returnSt = new ReturnStatementImpl<O, Block<O, T>>( this, block.getAST() );
        block.statements().add( returnSt.getStatement() );
        return returnSt;
    }

    @Override
    public AssignStatement<O, Block<O, T>> doAssign() {
        AssignStatementImpl<O,Block<O,T>> assign = new AssignStatementImpl<O, Block<O, T>>( this, block.getAST() );
        block.statements().add( assign.getStatement() );
        return assign;
    }

    @Override
    public DeclareStatement<O, Block<O, T>> doDeclare() {
        DeclareStatementImpl<O,Block<O,T>> declare = new DeclareStatementImpl<O, Block<O, T>>( this, block.getAST() );
        block.statements().add( declare.getStatement() );
        return declare;
    }

    @Override
    public InvokeStatement<O, Block<O, T>> doInvoke() {
        InvokeStatementImpl<O,Block<O,T>> invoke = new InvokeStatementImpl<O, Block<O, T>>( this, block.getAST() );
        block.statements().add( invoke.getStatement() );
        return invoke;
    }

    @Override
    public IfStatement<O, Block<O, T>> doIf() {
        IfStatementImpl<O,Block<O,T>> iff = new IfStatementImpl<O, Block<O, T>>( this, block.getAST() );
        block.statements().add( iff.getStatement() );
        return iff;
    }

    @Override
    public WhileStatement<O, Block<O, T>> doWhile() {
        WhileStatementImpl<O,Block<O,T>> rep = new WhileStatementImpl<O, Block<O, T>>( this, block.getAST() );
        block.statements().add( rep.getStatement() );
        return rep;
    }

    @Override
    public ForStatement<O, Block<O, T>> doFor() {
        ForStatementImpl<O,Block<O,T>> rep = new ForStatementImpl<O, Block<O, T>>( this, block.getAST() );
        block.statements().add( rep.getStatement() );
        return rep;
    }

    @Override
    public ForEachStatement<O, Block<O, T>> doForEach() {
        ForEachStatementImpl<O,Block<O,T>> rep = new ForEachStatementImpl<O, Block<O, T>>( this, block.getAST() );
        block.statements().add( rep.getStatement() );
        return rep;
    }

    @Override
    public T close() {
        return origin;
    }

    @Override
    public T getOrigin() {
        return origin;
    }

    @Override
    public org.eclipse.jdt.core.dom.Block getBlock() {
        return block;
    }
}
