/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.impl.statements;

import org.eclipse.jdt.core.dom.AST;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.BlockSource;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.statements.BreakStatement;

public class BreakStatementImpl<O extends JavaSource<O>,
                                P extends BlockHolder<O>>
        extends StatementImpl<O,P,BreakStatement<O,P>,org.eclipse.jdt.core.dom.BreakStatement>
        implements BreakStatement<O,P> {

    private org.eclipse.jdt.core.dom.BreakStatement ret;

    private String target;

    public BreakStatementImpl() { }

    @Override
    public org.eclipse.jdt.core.dom.BreakStatement materialize( AST ast ) {
        if (ret != null) {
            return ret;
        }
        ret = ast.newBreakStatement();
        if ( target != null ) {
            ret.setLabel( ast.newSimpleName( target ) );
        }
        return ret;
    }

    @Override
    public String getTarget() {
        return target;
    }

    @Override
    public BreakStatement<O,P> setTarget( String label ) {
        this.target = label;
        return this;
    }

    @Override
    public org.eclipse.jdt.core.dom.BreakStatement getInternal() {
        return ret;
    }

    @Override
    public void setInternal( org.eclipse.jdt.core.dom.BreakStatement jdtNode ) {
        super.setInternal(jdtNode);
        this.ret = jdtNode;
    }
}
