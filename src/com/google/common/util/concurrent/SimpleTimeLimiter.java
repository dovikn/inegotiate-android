package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Sets;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Beta
public final class SimpleTimeLimiter implements TimeLimiter {
    private final ExecutorService executor;

    /* renamed from: com.google.common.util.concurrent.SimpleTimeLimiter.1 */
    class C07101 implements InvocationHandler {
        final /* synthetic */ Set val$interruptibleMethods;
        final /* synthetic */ Object val$target;
        final /* synthetic */ long val$timeoutDuration;
        final /* synthetic */ TimeUnit val$timeoutUnit;

        /* renamed from: com.google.common.util.concurrent.SimpleTimeLimiter.1.1 */
        class C07091 implements Callable<Object> {
            final /* synthetic */ Object[] val$args;
            final /* synthetic */ Method val$method;

            C07091(Method method, Object[] objArr) {
                this.val$method = method;
                this.val$args = objArr;
            }

            public Object call() throws Exception {
                try {
                    return this.val$method.invoke(C07101.this.val$target, this.val$args);
                } catch (InvocationTargetException e) {
                    SimpleTimeLimiter.throwCause(e, false);
                    throw new AssertionError("can't get here");
                }
            }
        }

        C07101(Object obj, long j, TimeUnit timeUnit, Set set) {
            this.val$target = obj;
            this.val$timeoutDuration = j;
            this.val$timeoutUnit = timeUnit;
            this.val$interruptibleMethods = set;
        }

        public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
            return SimpleTimeLimiter.this.callWithTimeout(new C07091(method, args), this.val$timeoutDuration, this.val$timeoutUnit, this.val$interruptibleMethods.contains(method));
        }
    }

    public SimpleTimeLimiter(ExecutorService executor) {
        this.executor = (ExecutorService) Preconditions.checkNotNull(executor);
    }

    public SimpleTimeLimiter() {
        this(Executors.newCachedThreadPool());
    }

    public <T> T newProxy(T target, Class<T> interfaceType, long timeoutDuration, TimeUnit timeoutUnit) {
        Preconditions.checkNotNull(target);
        Preconditions.checkNotNull(interfaceType);
        Preconditions.checkNotNull(timeoutUnit);
        Preconditions.checkArgument(timeoutDuration > 0, "bad timeout: " + timeoutDuration);
        Preconditions.checkArgument(interfaceType.isInterface(), "interfaceType must be an interface type");
        return newProxy(interfaceType, new C07101(target, timeoutDuration, timeoutUnit, findInterruptibleMethods(interfaceType)));
    }

    public <T> T callWithTimeout(Callable<T> callable, long timeoutDuration, TimeUnit timeoutUnit, boolean amInterruptible) throws Exception {
        boolean z;
        T t;
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(timeoutUnit);
        if (timeoutDuration > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "timeout must be positive: %s", Long.valueOf(timeoutDuration));
        Future<T> future = this.executor.submit(callable);
        if (amInterruptible) {
            try {
                t = future.get(timeoutDuration, timeoutUnit);
            } catch (InterruptedException e) {
                future.cancel(true);
                throw e;
            } catch (ExecutionException e2) {
                throw throwCause(e2, true);
            } catch (Throwable e3) {
                future.cancel(true);
                throw new UncheckedTimeoutException(e3);
            }
        }
        t = Uninterruptibles.getUninterruptibly(future, timeoutDuration, timeoutUnit);
        return t;
    }

    private static Exception throwCause(Exception e, boolean combineStackTraces) throws Exception {
        Throwable cause = e.getCause();
        if (cause == null) {
            throw e;
        }
        if (combineStackTraces) {
            cause.setStackTrace((StackTraceElement[]) ObjectArrays.concat(cause.getStackTrace(), e.getStackTrace(), StackTraceElement.class));
        }
        if (cause instanceof Exception) {
            throw ((Exception) cause);
        } else if (cause instanceof Error) {
            throw ((Error) cause);
        } else {
            throw e;
        }
    }

    private static Set<Method> findInterruptibleMethods(Class<?> interfaceType) {
        Set<Method> set = Sets.newHashSet();
        for (Method m : interfaceType.getMethods()) {
            if (declaresInterruptedEx(m)) {
                set.add(m);
            }
        }
        return set;
    }

    private static boolean declaresInterruptedEx(Method method) {
        for (Class<?> exType : method.getExceptionTypes()) {
            if (exType == InterruptedException.class) {
                return true;
            }
        }
        return false;
    }

    private static <T> T newProxy(Class<T> interfaceType, InvocationHandler handler) {
        return interfaceType.cast(Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class[]{interfaceType}, handler));
    }
}
