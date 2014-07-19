package org.jboss.forge.roaster.model.statements;


import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.source.MethodSource;

public interface DeclareStatement<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends Statement<O,T> {

    public DeclareStatement<O,T> setVariable( String name, Class type );

    public DeclareStatement<O,T> setVariable( String name, String type );

    public ExpressionFactory<O,DeclareStatement<O,T>> setInitExpression();

    public T initDefault();

}
