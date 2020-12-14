package com.hussein.wt;

/**
 * <p>Title: Production</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/9 11:01 AM
 */
public class Production extends InstructionBook {

    private String productId;

    public Production(String productId) {
        this.productId = productId;
    }

    @Override
    void firstProcess() {
        System.out.println(Thread.currentThread().getName() + " execute the " + productId + " first process");
    }

    @Override
    void secondProcess() {
        System.out.println(Thread.currentThread().getName() + " execute the " + productId + " second process");
    }

    @Override
    public String toString() {
        return productId;
    }
}
