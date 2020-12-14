package com.hussein.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * <p>Title: CLHLock</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/17 1:58 PM
 */
public class CLHLock {

    private class CHLNode {

        private volatile boolean isLocked = true;
    }

    private volatile CHLNode tail;

    private final static ThreadLocal<CHLNode> nodeHolder = new ThreadLocal<>();

    private final static AtomicReferenceFieldUpdater<CLHLock, CHLNode> cas = AtomicReferenceFieldUpdater.newUpdater(CLHLock.class, CHLNode.class, "tail");

    public void lock() {
        CHLNode node = new CHLNode();
        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread.getName() + "创建节点 " + node);
        CHLNode preNode = cas.getAndSet(this, node);
        //已经被别的线程占用
        if (preNode != null) {
            //查看前驱节点是否已经释放锁
            int spinCount = 0;
            while (preNode.isLocked) {
                spinCount++;
            }
            System.out.println("前驱节点释放锁" + preNode + "，自旋次数:" + spinCount);
            preNode = null;
        }
        nodeHolder.set(node);
    }

    public void unlock() {
        CHLNode node = nodeHolder.get();
        if (!cas.compareAndSet(this, node, null)) {
            node.isLocked = false;
        }
        node = null;
    }

}
