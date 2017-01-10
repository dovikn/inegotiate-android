package com.google.common.collect;

import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

class ComputingConcurrentHashMap<K, V> extends MapMakerInternalMap<K, V> {
    private static final long serialVersionUID = 4;
    final Function<? super K, ? extends V> computingFunction;

    private static final class ComputationExceptionReference<K, V> implements ValueReference<K, V> {
        final Throwable f436t;

        ComputationExceptionReference(Throwable t) {
            this.f436t = t;
        }

        public V get() {
            return null;
        }

        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        public boolean isComputingReference() {
            return false;
        }

        public V waitForValue() throws ExecutionException {
            throw new ExecutionException(this.f436t);
        }

        public void clear(ValueReference<K, V> valueReference) {
        }
    }

    private static final class ComputedReference<K, V> implements ValueReference<K, V> {
        final V value;

        ComputedReference(@Nullable V value) {
            this.value = value;
        }

        public V get() {
            return this.value;
        }

        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        public boolean isComputingReference() {
            return false;
        }

        public V waitForValue() {
            return get();
        }

        public void clear(ValueReference<K, V> valueReference) {
        }
    }

    static final class ComputingMapAdapter<K, V> extends ComputingConcurrentHashMap<K, V> implements Serializable {
        private static final long serialVersionUID = 0;

        /* bridge */ /* synthetic */ Segment segmentFor(int x0) {
            return super.segmentFor(x0);
        }

        ComputingMapAdapter(MapMaker mapMaker, Function<? super K, ? extends V> computingFunction) {
            super(mapMaker, computingFunction);
        }

        public V get(Object key) {
            try {
                V value = getOrCompute(key);
                if (value != null) {
                    return value;
                }
                throw new NullPointerException(this.computingFunction + " returned null for key " + key + ".");
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                Throwables.propagateIfInstanceOf(cause, ComputationException.class);
                throw new ComputationException(cause);
            }
        }
    }

    static final class ComputingSegment<K, V> extends Segment<K, V> {
        ComputingSegment(MapMakerInternalMap<K, V> map, int initialCapacity, int maxSegmentSize) {
            super(map, initialCapacity, maxSegmentSize);
        }

        V getOrCompute(K r15, int r16, com.google.common.base.Function<? super K, ? extends V> r17) throws java.util.concurrent.ExecutionException {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:42)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:66)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r14 = this;
        L_0x0000:
            r4 = r14.getEntry(r15, r16);	 Catch:{ all -> 0x00b1 }
            if (r4 == 0) goto L_0x0013;	 Catch:{ all -> 0x00b1 }
        L_0x0006:
            r10 = r14.getLiveValue(r4);	 Catch:{ all -> 0x00b1 }
            if (r10 == 0) goto L_0x0013;	 Catch:{ all -> 0x00b1 }
        L_0x000c:
            r14.recordRead(r4);	 Catch:{ all -> 0x00b1 }
            r14.postReadCleanup();
        L_0x0012:
            return r10;
        L_0x0013:
            if (r4 == 0) goto L_0x001f;
        L_0x0015:
            r12 = r4.getValueReference();	 Catch:{ all -> 0x00b1 }
            r12 = r12.isComputingReference();	 Catch:{ all -> 0x00b1 }
            if (r12 != 0) goto L_0x00e7;	 Catch:{ all -> 0x00b1 }
        L_0x001f:
            r3 = 1;	 Catch:{ all -> 0x00b1 }
            r1 = 0;	 Catch:{ all -> 0x00b1 }
            r14.lock();	 Catch:{ all -> 0x00b1 }
            r14.preWriteCleanup();	 Catch:{ all -> 0x00a9 }
            r12 = r14.count;	 Catch:{ all -> 0x00a9 }
            r8 = r12 + -1;	 Catch:{ all -> 0x00a9 }
            r9 = r14.table;	 Catch:{ all -> 0x00a9 }
            r12 = r9.length();	 Catch:{ all -> 0x00a9 }
            r12 = r12 + -1;	 Catch:{ all -> 0x00a9 }
            r7 = r16 & r12;	 Catch:{ all -> 0x00a9 }
            r6 = r9.get(r7);	 Catch:{ all -> 0x00a9 }
            r6 = (com.google.common.collect.MapMakerInternalMap.ReferenceEntry) r6;	 Catch:{ all -> 0x00a9 }
            r4 = r6;	 Catch:{ all -> 0x00a9 }
        L_0x003c:
            if (r4 == 0) goto L_0x0061;	 Catch:{ all -> 0x00a9 }
        L_0x003e:
            r5 = r4.getKey();	 Catch:{ all -> 0x00a9 }
            r12 = r4.getHash();	 Catch:{ all -> 0x00a9 }
            r0 = r16;	 Catch:{ all -> 0x00a9 }
            if (r12 != r0) goto L_0x00dc;	 Catch:{ all -> 0x00a9 }
        L_0x004a:
            if (r5 == 0) goto L_0x00dc;	 Catch:{ all -> 0x00a9 }
        L_0x004c:
            r12 = r14.map;	 Catch:{ all -> 0x00a9 }
            r12 = r12.keyEquivalence;	 Catch:{ all -> 0x00a9 }
            r12 = r12.equivalent(r15, r5);	 Catch:{ all -> 0x00a9 }
            if (r12 == 0) goto L_0x00dc;	 Catch:{ all -> 0x00a9 }
        L_0x0056:
            r11 = r4.getValueReference();	 Catch:{ all -> 0x00a9 }
            r12 = r11.isComputingReference();	 Catch:{ all -> 0x00a9 }
            if (r12 == 0) goto L_0x008b;	 Catch:{ all -> 0x00a9 }
        L_0x0060:
            r3 = 0;	 Catch:{ all -> 0x00a9 }
        L_0x0061:
            if (r3 == 0) goto L_0x0079;	 Catch:{ all -> 0x00a9 }
        L_0x0063:
            r2 = new com.google.common.collect.ComputingConcurrentHashMap$ComputingValueReference;	 Catch:{ all -> 0x00a9 }
            r0 = r17;	 Catch:{ all -> 0x00a9 }
            r2.<init>(r0);	 Catch:{ all -> 0x00a9 }
            if (r4 != 0) goto L_0x00e2;
        L_0x006c:
            r0 = r16;	 Catch:{ all -> 0x0107 }
            r4 = r14.newEntry(r15, r0, r6);	 Catch:{ all -> 0x0107 }
            r4.setValueReference(r2);	 Catch:{ all -> 0x0107 }
            r9.set(r7, r4);	 Catch:{ all -> 0x0107 }
            r1 = r2;
        L_0x0079:
            r14.unlock();	 Catch:{ all -> 0x00b1 }
            r14.postWriteCleanup();	 Catch:{ all -> 0x00b1 }
            if (r3 == 0) goto L_0x00e7;	 Catch:{ all -> 0x00b1 }
        L_0x0081:
            r0 = r16;	 Catch:{ all -> 0x00b1 }
            r10 = r14.compute(r15, r0, r4, r1);	 Catch:{ all -> 0x00b1 }
            r14.postReadCleanup();
            goto L_0x0012;
        L_0x008b:
            r12 = r4.getValueReference();	 Catch:{ all -> 0x00a9 }
            r10 = r12.get();	 Catch:{ all -> 0x00a9 }
            if (r10 != 0) goto L_0x00b6;	 Catch:{ all -> 0x00a9 }
        L_0x0095:
            r12 = com.google.common.collect.MapMaker.RemovalCause.COLLECTED;	 Catch:{ all -> 0x00a9 }
            r0 = r16;	 Catch:{ all -> 0x00a9 }
            r14.enqueueNotification(r5, r0, r10, r12);	 Catch:{ all -> 0x00a9 }
        L_0x009c:
            r12 = r14.evictionQueue;	 Catch:{ all -> 0x00a9 }
            r12.remove(r4);	 Catch:{ all -> 0x00a9 }
            r12 = r14.expirationQueue;	 Catch:{ all -> 0x00a9 }
            r12.remove(r4);	 Catch:{ all -> 0x00a9 }
            r14.count = r8;	 Catch:{ all -> 0x00a9 }
            goto L_0x0061;
        L_0x00a9:
            r12 = move-exception;
        L_0x00aa:
            r14.unlock();	 Catch:{ all -> 0x00b1 }
            r14.postWriteCleanup();	 Catch:{ all -> 0x00b1 }
            throw r12;	 Catch:{ all -> 0x00b1 }
        L_0x00b1:
            r12 = move-exception;
            r14.postReadCleanup();
            throw r12;
        L_0x00b6:
            r12 = r14.map;	 Catch:{ all -> 0x00a9 }
            r12 = r12.expires();	 Catch:{ all -> 0x00a9 }
            if (r12 == 0) goto L_0x00ce;	 Catch:{ all -> 0x00a9 }
        L_0x00be:
            r12 = r14.map;	 Catch:{ all -> 0x00a9 }
            r12 = r12.isExpired(r4);	 Catch:{ all -> 0x00a9 }
            if (r12 == 0) goto L_0x00ce;	 Catch:{ all -> 0x00a9 }
        L_0x00c6:
            r12 = com.google.common.collect.MapMaker.RemovalCause.EXPIRED;	 Catch:{ all -> 0x00a9 }
            r0 = r16;	 Catch:{ all -> 0x00a9 }
            r14.enqueueNotification(r5, r0, r10, r12);	 Catch:{ all -> 0x00a9 }
            goto L_0x009c;	 Catch:{ all -> 0x00a9 }
        L_0x00ce:
            r14.recordLockedRead(r4);	 Catch:{ all -> 0x00a9 }
            r14.unlock();	 Catch:{ all -> 0x00b1 }
            r14.postWriteCleanup();	 Catch:{ all -> 0x00b1 }
            r14.postReadCleanup();
            goto L_0x0012;
        L_0x00dc:
            r4 = r4.getNext();	 Catch:{ all -> 0x00a9 }
            goto L_0x003c;
        L_0x00e2:
            r4.setValueReference(r2);	 Catch:{ all -> 0x0107 }
            r1 = r2;
            goto L_0x0079;
        L_0x00e7:
            r12 = java.lang.Thread.holdsLock(r4);	 Catch:{ all -> 0x00b1 }
            if (r12 != 0) goto L_0x0105;	 Catch:{ all -> 0x00b1 }
        L_0x00ed:
            r12 = 1;	 Catch:{ all -> 0x00b1 }
        L_0x00ee:
            r13 = "Recursive computation";	 Catch:{ all -> 0x00b1 }
            com.google.common.base.Preconditions.checkState(r12, r13);	 Catch:{ all -> 0x00b1 }
            r12 = r4.getValueReference();	 Catch:{ all -> 0x00b1 }
            r10 = r12.waitForValue();	 Catch:{ all -> 0x00b1 }
            if (r10 == 0) goto L_0x0000;	 Catch:{ all -> 0x00b1 }
        L_0x00fd:
            r14.recordRead(r4);	 Catch:{ all -> 0x00b1 }
            r14.postReadCleanup();
            goto L_0x0012;
        L_0x0105:
            r12 = 0;
            goto L_0x00ee;
        L_0x0107:
            r12 = move-exception;
            r1 = r2;
            goto L_0x00aa;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ComputingConcurrentHashMap.ComputingSegment.getOrCompute(java.lang.Object, int, com.google.common.base.Function):V");
        }

        V compute(K key, int hash, ReferenceEntry<K, V> e, ComputingValueReference<K, V> computingValueReference) throws ExecutionException {
            V v = null;
            long start = System.nanoTime();
            long end = 0;
            try {
                synchronized (e) {
                    v = computingValueReference.compute(key, hash);
                    end = System.nanoTime();
                }
                if (v != null) {
                    if (put(key, hash, v, true) != null) {
                        enqueueNotification(key, hash, v, RemovalCause.REPLACED);
                    }
                }
                if (end == 0) {
                    end = System.nanoTime();
                }
                if (v == null) {
                    clearValue(key, hash, computingValueReference);
                }
                return v;
            } catch (Throwable th) {
                if (end == 0) {
                    end = System.nanoTime();
                }
                if (v == null) {
                    clearValue(key, hash, computingValueReference);
                }
            }
        }
    }

    static final class ComputingSerializationProxy<K, V> extends AbstractSerializationProxy<K, V> {
        private static final long serialVersionUID = 4;
        final Function<? super K, ? extends V> computingFunction;

        ComputingSerializationProxy(Strength keyStrength, Strength valueStrength, Equivalence<Object> keyEquivalence, Equivalence<Object> valueEquivalence, long expireAfterWriteNanos, long expireAfterAccessNanos, int maximumSize, int concurrencyLevel, RemovalListener<? super K, ? super V> removalListener, ConcurrentMap<K, V> delegate, Function<? super K, ? extends V> computingFunction) {
            super(keyStrength, valueStrength, keyEquivalence, valueEquivalence, expireAfterWriteNanos, expireAfterAccessNanos, maximumSize, concurrencyLevel, removalListener, delegate);
            this.computingFunction = computingFunction;
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();
            writeMapTo(out);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            this.delegate = readMapMaker(in).makeComputingMap(this.computingFunction);
            readEntries(in);
        }

        Object readResolve() {
            return this.delegate;
        }
    }

    private static final class ComputingValueReference<K, V> implements ValueReference<K, V> {
        @GuardedBy("ComputingValueReference.this")
        volatile ValueReference<K, V> computedReference;
        final Function<? super K, ? extends V> computingFunction;

        public ComputingValueReference(Function<? super K, ? extends V> computingFunction) {
            this.computedReference = MapMakerInternalMap.unset();
            this.computingFunction = computingFunction;
        }

        public V get() {
            return null;
        }

        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        public boolean isComputingReference() {
            return true;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V waitForValue() throws java.util.concurrent.ExecutionException {
            /*
            r4 = this;
            r2 = r4.computedReference;
            r3 = com.google.common.collect.MapMakerInternalMap.UNSET;
            if (r2 != r3) goto L_0x001f;
        L_0x0006:
            r1 = 0;
            monitor-enter(r4);	 Catch:{ all -> 0x0029 }
        L_0x0008:
            r2 = r4.computedReference;	 Catch:{ all -> 0x0026 }
            r3 = com.google.common.collect.MapMakerInternalMap.UNSET;	 Catch:{ all -> 0x0026 }
            if (r2 != r3) goto L_0x0015;
        L_0x000e:
            r4.wait();	 Catch:{ InterruptedException -> 0x0012 }
            goto L_0x0008;
        L_0x0012:
            r0 = move-exception;
            r1 = 1;
            goto L_0x0008;
        L_0x0015:
            monitor-exit(r4);	 Catch:{ all -> 0x0026 }
            if (r1 == 0) goto L_0x001f;
        L_0x0018:
            r2 = java.lang.Thread.currentThread();
            r2.interrupt();
        L_0x001f:
            r2 = r4.computedReference;
            r2 = r2.waitForValue();
            return r2;
        L_0x0026:
            r2 = move-exception;
            monitor-exit(r4);	 Catch:{ all -> 0x0026 }
            throw r2;	 Catch:{ all -> 0x0029 }
        L_0x0029:
            r2 = move-exception;
            if (r1 == 0) goto L_0x0033;
        L_0x002c:
            r3 = java.lang.Thread.currentThread();
            r3.interrupt();
        L_0x0033:
            throw r2;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ComputingConcurrentHashMap.ComputingValueReference.waitForValue():V");
        }

        public void clear(ValueReference<K, V> newValue) {
            setValueReference(newValue);
        }

        V compute(K key, int hash) throws ExecutionException {
            try {
                V value = this.computingFunction.apply(key);
                setValueReference(new ComputedReference(value));
                return value;
            } catch (Throwable t) {
                setValueReference(new ComputationExceptionReference(t));
                ExecutionException executionException = new ExecutionException(t);
            }
        }

        void setValueReference(ValueReference<K, V> valueReference) {
            synchronized (this) {
                if (this.computedReference == MapMakerInternalMap.UNSET) {
                    this.computedReference = valueReference;
                    notifyAll();
                }
            }
        }
    }

    ComputingConcurrentHashMap(MapMaker builder, Function<? super K, ? extends V> computingFunction) {
        super(builder);
        this.computingFunction = (Function) Preconditions.checkNotNull(computingFunction);
    }

    Segment<K, V> createSegment(int initialCapacity, int maxSegmentSize) {
        return new ComputingSegment(this, initialCapacity, maxSegmentSize);
    }

    ComputingSegment<K, V> segmentFor(int hash) {
        return (ComputingSegment) super.segmentFor(hash);
    }

    V getOrCompute(K key) throws ExecutionException {
        int hash = hash(Preconditions.checkNotNull(key));
        return segmentFor(hash).getOrCompute(key, hash, this.computingFunction);
    }

    Object writeReplace() {
        return new ComputingSerializationProxy(this.keyStrength, this.valueStrength, this.keyEquivalence, this.valueEquivalence, this.expireAfterWriteNanos, this.expireAfterAccessNanos, this.maximumSize, this.concurrencyLevel, this.removalListener, this, this.computingFunction);
    }
}
