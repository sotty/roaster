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

    /**
     * Adds a return statement to the current block
     * @return
     */
    ReturnStatement<O,Block<O,T>> addReturn();

    /**
     * Adds a variable assignment statement to the current block
     * @return
     */
    AssignStatement<O,Block<O,T>> addAssign();

    /**
     * Adds a variable declaration statement to the current block
     * @return
     */
    DeclareStatement<O,Block<O,T>> addDeclare();

    /**
     * Adds a method invocation statement to the current block
     * @return
     */
    InvokeStatement<O,Block<O,T>> addInvoke();

    /**
     * Adds an if statement to the current block
     * @return
     */
    IfStatement<O,Block<O,T>> addIf();

    /**
     * Adds a while statement to the current block
     * @return
     */
    WhileStatement<O,Block<O,T>> addWhile();

    /**
     * Adds a for statement to the current block
     * @return
     */
    ForStatement<O,Block<O,T>> addFor();

    /**
     * Adds an enhanced for statement to the current block
     * @return
     */
    ForEachStatement<O,Block<O,T>> addForEach();

    /**
     * Returns the block's parent
     * @return
     */
    T closeBlock();

}
