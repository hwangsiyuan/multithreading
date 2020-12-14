package com.hussein.tg;

/**
 * <p>Title: ThreadGroupCreator</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/24 2:44 PM
 */
public class ThreadGroupCreator {

    public static void main(String[] args) {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup group1 = new ThreadGroup("group1");
        System.out.println(group1.getParent() == mainGroup);
        ThreadGroup group2 = new ThreadGroup(group1, "group2");
        System.out.println(group1 == group2.getParent());
    }
}
