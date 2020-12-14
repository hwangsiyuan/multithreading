package com.hussein.tg;

/**
 * <p>Title: ThreadGroupDestroy</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/24 3:33 PM
 */
public class ThreadGroupDestroy {

    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("group1");
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        mainGroup.list();
        group.destroy();
        mainGroup.list();
    }
}
