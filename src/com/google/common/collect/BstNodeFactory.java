package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import javax.annotation.Nullable;

@GwtCompatible
abstract class BstNodeFactory<N extends BstNode<?, N>> {
    public abstract N createNode(N n, @Nullable N n2, @Nullable N n3);

    BstNodeFactory() {
    }

    public final N createLeaf(N source) {
        return createNode(source, null, null);
    }
}
