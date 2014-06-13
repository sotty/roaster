package org.jboss.forge.roaster.model;


import org.jboss.forge.roaster.Origin;
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

public interface Block<O extends JavaSource<O>, T extends BlockHolder<O,?>>
        extends Origin<T> {

    ReturnStatement<O,Block<O,T>> doReturn();

    AssignStatement<O,Block<O,T>> doAssign();

    DeclareStatement<O,Block<O,T>> doDeclare();

    InvokeStatement<O,Block<O,T>> doInvoke();

    IfStatement<O,Block<O,T>> doIf();

    WhileStatement<O,Block<O,T>> doWhile();

    ForStatement<O,Block<O,T>> doFor();

    ForEachStatement<O,Block<O,T>> doForEach();

    T close();

}
