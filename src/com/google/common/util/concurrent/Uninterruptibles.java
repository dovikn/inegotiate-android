package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Beta
public final class Uninterruptibles {
    public static void awaitUninterruptibly(CountDownLatch latch) {
        boolean interrupted = false;
        while (true) {
            try {
                latch.await();
                break;
            } catch (InterruptedException e) {
                interrupted = true;
            } catch (Throwable th) {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
    }

    public static boolean awaitUninterruptibly(CountDownLatch latch, long timeout, TimeUnit unit) {
        long end;
        boolean interrupted = false;
        long remainingNanos;
        try {
            boolean await;
            remainingNanos = unit.toNanos(timeout);
            end = System.nanoTime() + remainingNanos;
            while (true) {
                await = latch.await(remainingNanos, TimeUnit.NANOSECONDS);
                break;
            }
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
            return await;
        } catch (InterruptedException e) {
            interrupted = true;
            remainingNanos = end - System.nanoTime();
        } catch (Throwable th) {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void joinUninterruptibly(Thread toJoin) {
        boolean interrupted = false;
        while (true) {
            try {
                toJoin.join();
                break;
            } catch (InterruptedException e) {
                interrupted = true;
            } catch (Throwable th) {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
    }

    public static <V> V getUninterruptibly(Future<V> future) throws ExecutionException {
        V v;
        boolean interrupted = false;
        while (true) {
            try {
                v = future.get();
                break;
            } catch (InterruptedException e) {
                interrupted = true;
            } catch (Throwable th) {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
        return v;
    }

    public static <V> V getUninterruptibly(Future<V> future, long timeout, TimeUnit unit) throws ExecutionException, TimeoutException {
        long end;
        boolean interrupted = false;
        long remainingNanos;
        try {
            V v;
            remainingNanos = unit.toNanos(timeout);
            end = System.nanoTime() + remainingNanos;
            while (true) {
                v = future.get(remainingNanos, TimeUnit.NANOSECONDS);
                break;
            }
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
            return v;
        } catch (InterruptedException e) {
            interrupted = true;
            remainingNanos = end - System.nanoTime();
        } catch (Throwable th) {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void joinUninterruptibly(Thread toJoin, long timeout, TimeUnit unit) {
        long end;
        Preconditions.checkNotNull(toJoin);
        boolean interrupted = false;
        long remainingNanos;
        try {
            remainingNanos = unit.toNanos(timeout);
            end = System.nanoTime() + remainingNanos;
            while (true) {
                TimeUnit.NANOSECONDS.timedJoin(toJoin, remainingNanos);
                break;
            }
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        } catch (InterruptedException e) {
            interrupted = true;
            remainingNanos = end - System.nanoTime();
        } catch (Throwable th) {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static <E> E takeUninterruptibly(BlockingQueue<E> queue) {
        E take;
        boolean interrupted = false;
        while (true) {
            try {
                take = queue.take();
                break;
            } catch (InterruptedException e) {
                interrupted = true;
            } catch (Throwable th) {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
        return take;
    }

    public static <E> void putUninterruptibly(BlockingQueue<E> queue, E element) {
        boolean interrupted = false;
        while (true) {
            try {
                queue.put(element);
                break;
            } catch (InterruptedException e) {
                interrupted = true;
            } catch (Throwable th) {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
    }

    public static void sleepUninterruptibly(long sleepFor, TimeUnit unit) {
        long end;
        boolean interrupted = false;
        long remainingNanos;
        try {
            remainingNanos = unit.toNanos(sleepFor);
            end = System.nanoTime() + remainingNanos;
            while (true) {
                TimeUnit.NANOSECONDS.sleep(remainingNanos);
                break;
            }
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        } catch (InterruptedException e) {
            interrupted = true;
            remainingNanos = end - System.nanoTime();
        } catch (Throwable th) {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private Uninterruptibles() {
    }
}
