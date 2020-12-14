package com.hussein.wt;

/**
 * <p>Title: InstructionBook</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/9 10:59 AM
 */
public abstract class InstructionBook {

    public final void create() {
        firstProcess();
        secondProcess();
    }

    abstract void firstProcess();

    abstract void secondProcess();
}
