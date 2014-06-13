package org.jboss.forge.roaster.model.impl.expressions;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import org.jboss.forge.roaster.model.expressions.Accessor;
import org.jboss.forge.roaster.model.expressions.Argument;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.expressions.ExpressionTypes;
import org.jboss.forge.roaster.model.source.JavaSource;

public class DotAccessorImpl<O extends JavaSource<O>,T extends ExpressionSource<O>>
        extends ExpressionFactoryImpl<O,T>
        implements Accessor<O,T> {

    Argument<O,T> parent;

    public DotAccessorImpl( T origin, Argument<O,T> parent, AST ast ) {
        super( origin, ast );
        this.parent = parent;
    }

    @Override
    protected <X extends Argument<O,T>> X subArg( org.eclipse.jdt.core.dom.Expression expr, ExpressionTypes type ) {
        X child = buildArgument( expr, type, false );

        JdtExpressionWrapper immediateParent = parent != null ? ((JdtExpressionWrapper) parent) : ((JdtExpressionWrapper) origin);
        org.eclipse.jdt.core.dom.Expression parentXp = immediateParent.getExpression();

        switch ( type ) {
            case INVOKE:
                MethodInvocation invoke = (MethodInvocation) expr;
                swap( parentXp, child );
                invoke.setExpression( parentXp );
                break;
            case FIELD:
                FieldAccess fax = (FieldAccess) expr;
                swap( parentXp, child );
                fax.setExpression( parentXp );
                break;
        }

        return child;
    }

    @Override
    protected void concatExpression( org.eclipse.jdt.core.dom.Expression parent, org.eclipse.jdt.core.dom.Expression child ) {
        throw new IllegalStateException( "Dot accessors will delegate child additions, but a child node was attempted : " + child );
    }

    @Override
    protected Expression getExpression() {
        throw new IllegalStateException( "Dot accessors will delegate expressions to children, but the expression was requested on this node " );
    }

}
