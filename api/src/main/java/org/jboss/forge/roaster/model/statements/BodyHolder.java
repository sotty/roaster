
package org.jboss.forge.roaster.model.statements;

import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.BlockSource;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface BodyHolder<O extends JavaSource<O>,
                            P extends BlockHolder<O>,
                            B extends BodyHolder<O,P,?>>
        extends BlockHolder<O>  {

    public BlockStatement<O,B> getBody();

    public B setBody( BlockSource<?,?,?> body );

    public B setBody( StatementSource<?,?,?> body );

}
