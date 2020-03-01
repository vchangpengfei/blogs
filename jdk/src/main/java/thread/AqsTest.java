package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class AqsTest {


    /**
     * 共享锁实现
     */
    private static final class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 4982264981922014374L;

        Sync(int count) {
            setState(count);
        }

        int getCount() {
            return getState();
        }

        /**
         * 判断是否还有资源
         * @param acquires
         * @return
         */
        protected int tryAcquireShared(int acquires) {
            return (getState() == 0) ? 1 : -1;
        }

        /**
         * 尝试释放资源
         * @param releases
         * @return
         */
        protected boolean tryReleaseShared(int releases) {
            // Decrement count; signal when transition to zero
            for (;;) {
                int c = getState();
                if (c == 0)
                    return false;
                int nextc = c-1;
                if (compareAndSetState(c, nextc))
                    return nextc == 0;
            }
        }
    }

    private final AqsTest.Sync sync;


    public AqsTest(int count) {
        if (count < 0) throw new IllegalArgumentException("count < 0");
        this.sync = new AqsTest.Sync(count);
    }


    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }


    public boolean await(long timeout, TimeUnit unit)
            throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
    }


    public void countDown() {
        sync.releaseShared(1);
    }


    public long getCount() {
        return sync.getCount();
    }


    public String toString() {
        return super.toString() + "[Count = " + sync.getCount() + "]";
    }

    public static void main(String args[])
    {

    }
}
