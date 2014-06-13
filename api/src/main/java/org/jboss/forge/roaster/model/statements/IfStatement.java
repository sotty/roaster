package org.jboss.forge.roaster.model.statements;

import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.ExpressionFactory;
import org.jboss.forge.roaster.model.expressions.OperatorExpression;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.source.MethodSource;

public interface IfStatement<O extends JavaSource<O>, T extends Block<O,? extends BlockHolder<O,?>>>
        extends Statement<O,T>,
                BlockHolder<O,IfStatement<O,T>> {

    ExpressionFactory<O,IfStatement<O,T>> condition();

    Block<O,IfStatement<O,T>> thenDo();

    Block<O,IfStatement<O,T>> elseDo();

}
