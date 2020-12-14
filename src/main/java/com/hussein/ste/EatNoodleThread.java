package com.hussein.ste;

/**
 * <p>Title: EatNoodleThread</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/1 7:58 PM
 */
public class EatNoodleThread extends Thread {

    private String name;

    private final Tableware leftTool;

    private final Tableware rightTool;

    public EatNoodleThread(String name, Tableware leftTool, Tableware rightTool) {
        super();
        this.name = name;
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (leftTool) {
                System.out.println(name + " take up leftTool:" + leftTool);
                synchronized (rightTool) {
                    System.out.println(name + " take up rightTool:" + rightTool);
                    System.out.println(name + " start eating..");
                    System.out.println(name + " put down rightTool:" + rightTool);
                }
                System.out.println(name + " put down leftTool:" + rightTool);
            }
        }
    }

    public static void main(String[] args) {
        Tableware knife = new Tableware("knife");
        Tableware fork = new Tableware("fork");
        new EatNoodleThread("A", knife, fork).start();
        new EatNoodleThread("B", fork, knife).start();
    }
}
