package com.hussein.ste;

/**
 * <p>Title: EatNoodleThread</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/1 7:58 PM
 */
public class EatNoodleSafeThread extends Thread {

    private String name;

    private final TablewarePair tablewarePair;

    public EatNoodleSafeThread(String name, TablewarePair tablewarePair) {
        this.name = name;
        this.tablewarePair = tablewarePair;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (tablewarePair) {
                System.out.println(name + " take up leftTool:" + tablewarePair.getLeftTool());
                System.out.println(name + " take up rightTool:" + tablewarePair.getRightTool());
                System.out.println(name + " start eating..");
                System.out.println(name + " put down rightTool:" + tablewarePair.getRightTool());
                System.out.println(name + " put down leftTool:" + tablewarePair.getLeftTool());
            }
        }
    }

    public static void main(String[] args) {
        Tableware knife = new Tableware("knife");
        Tableware fork = new Tableware("fork");
        new EatNoodleSafeThread("A", new TablewarePair(knife, fork)).start();
        new EatNoodleSafeThread("B", new TablewarePair(fork, knife)).start();
    }
}
