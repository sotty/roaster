package org.jboss.forge.roaster.model.statements;


import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.source.MethodSource;

public interface AssignStatement<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends Statement<O,T> {

    public AssignStatement<O,T> toField( String name );

    public AssignStatement<O,T> toVar( String name );

    public ExpressionFactory<O,AssignStatement<O,T>> to();

    public AssignStatement<O,T> varExpr( String name );

    public ExpressionFactory<O,AssignStatement<O,T>> expr();

}
