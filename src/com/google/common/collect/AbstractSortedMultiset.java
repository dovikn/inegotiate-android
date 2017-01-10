package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset.Entry;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

@GwtCompatible
abstract class AbstractSortedMultiset<E> extends AbstractMultiset<E> implements SortedMultiset<E> {
    final Comparator<? super E> comparator;
    private transient SortedMultiset<E> descendingMultiset;

    /* renamed from: com.google.common.collect.AbstractSortedMultiset.1 */
    class C04131 extends ElementSet<E> {
        C04131() {
        }

        SortedMultiset<E> multiset() {
            return AbstractSortedMultiset.this;
        }
    }

    /* renamed from: com.google.common.collect.AbstractSortedMultiset.2 */
    class C04142 extends DescendingMultiset<E> {
        C04142() {
        }

        SortedMultiset<E> forwardMultiset() {
            return AbstractSortedMultiset.this;
        }

        Iterator<Entry<E>> entryIterator() {
            return AbstractSortedMultiset.this.descendingEntryIterator();
        }

        public Iterator<E> iterator() {
            return AbstractSortedMultiset.this.descendingIterator();
        }
    }

    abstract Iterator<Entry<E>> descendingEntryIterator();

    AbstractSortedMultiset() {
        this(Ordering.natural());
    }

    AbstractSortedMultiset(Comparator<? super E> comparator) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
    }

    public SortedSet<E> elementSet() {
        return (SortedSet) super.elementSet();
    }

    SortedSet<E> createElementSet() {
        return new C04131();
    }

    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    public Entry<E> firstEntry() {
        Iterator<Entry<E>> entryIterator = entryIterator();
        return entryIterator.hasNext() ? (Entry) entryIterator.next() : null;
    }

    public Entry<E> lastEntry() {
        Iterator<Entry<E>> entryIterator = descendingEntryIterator();
        return entryIterator.hasNext() ? (Entry) entryIterator.next() : null;
    }

    public Entry<E> pollFirstEntry() {
        Iterator<Entry<E>> entryIterator = entryIterator();
        if (!entryIterator.hasNext()) {
            return null;
        }
        Entry<E> result = (Entry) entryIterator.next();
        result = Multisets.immutableEntry(result.getElement(), result.getCount());
        entryIterator.remove();
        return result;
    }

    public Entry<E> pollLastEntry() {
        Iterator<Entry<E>> entryIterator = descendingEntryIterator();
        if (!entryIterator.hasNext()) {
            return null;
        }
        Entry<E> result = (Entry) entryIterator.next();
        result = Multisets.immutableEntry(result.getElement(), result.getCount());
        entryIterator.remove();
        return result;
    }

    public SortedMultiset<E> subMultiset(E fromElement, BoundType fromBoundType, E toElement, BoundType toBoundType) {
        return tailMultiset(fromElement, fromBoundType).headMultiset(toElement, toBoundType);
    }

    Iterator<E> descendingIterator() {
        return Multisets.iteratorImpl(descendingMultiset());
    }

    public SortedMultiset<E> descendingMultiset() {
        SortedMultiset<E> sortedMultiset = this.descendingMultiset;
        if (sortedMultiset != null) {
            return sortedMultiset;
        }
        sortedMultiset = createDescendingMultiset();
        this.descendingMultiset = sortedMultiset;
        return sortedMultiset;
    }

    SortedMultiset<E> createDescendingMultiset() {
        return new C04142();
    }
}
