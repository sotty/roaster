/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.statements;

import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface BranchingStatement<O extends JavaSource<O>,
                                    P extends BlockHolder<O>,
                                    S extends BranchingStatement<O,P,S>>
        extends ControlFlowStatement<O,P,S> {
}
