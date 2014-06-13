package org.jboss.forge.roaster.model.expressions;

import org.jboss.forge.roaster.model.source.JavaSource;

public interface Accessor<O extends JavaSource<O>,T extends ExpressionSource<O>> {

    public Argument<O,T> field( String fldName );

    public Argument<O,T> getter( String fldName, String type );

    public InvokeExpression<O,T> invoke();


}
