/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.impl.expressions;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.jboss.forge.roaster.model.ExpressionHolder;
import org.jboss.forge.roaster.model.expressions.DeclareExpression;
import org.jboss.forge.roaster.model.expressions.Expression;
import org.jboss.forge.roaster.model.expressions.ExpressionSource;
import org.jboss.forge.roaster.model.impl.JDTHelper;
import org.jboss.forge.roaster.model.source.JavaSource;

public class DeclareExpressionImpl<O extends JavaSource<O>,
                                   P extends ExpressionHolder<O>>
        extends NonPrimitiveExpressionImpl<O,P,DeclareExpression<O,P>,VariableDeclarationExpression>
        implements DeclareExpression<O,P> {

    private VariableDeclarationExpression var;

    private String type;
    private String name;
    private ExpressionSource<O,DeclareExpression<O,P>,?> init;

    public DeclareExpressionImpl( String type, String name ) {
        this.type = type;
        this.name = name;
    }

    @Override
    public DeclareExpression<O,P> init( ExpressionSource<?,?,?> expr ) {
        this.init = (ExpressionSource<O, DeclareExpression<O, P>, ?>) expr;
        return this;
    }

    @Override
    public VariableDeclarationExpression materialize( AST ast ) {
        if (isMaterialized()) {
            return var;
        }
        VariableDeclarationFragment frag = ast.newVariableDeclarationFragment();
        frag.setName( ast.newSimpleName( name ) );
        var = ast.newVariableDeclarationExpression( frag );
        var.setType( JDTHelper.getType( type, ast ) );

        if ( init != null ) {
            frag.setInitializer( wireAndGetExpression( init, this, ast ) );
        }
        return var;
    }

    @Override
    public VariableDeclarationExpression getInternal() {
        return var;
    }

    @Override
    public void setInternal(VariableDeclarationExpression jdtNode) {
        super.setInternal(jdtNode);
        this.var = jdtNode;
    }
}
