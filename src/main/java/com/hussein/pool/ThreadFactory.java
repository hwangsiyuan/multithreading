package com.hussein.pool;

/**
 * <p>Title: ThreadFactory</p>
 * <p>Description: 自定义线程工厂</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/25 2:07 PM
 */
@FunctionalInterface
public interface ThreadFactory {

    Thread createThread(Runnable runnable);
}
