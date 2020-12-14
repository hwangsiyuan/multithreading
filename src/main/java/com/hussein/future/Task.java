package com.hussein.future;

/**
 * <p>Title: Task</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/6 4:34 PM
 */
@FunctionalInterface
public interface Task<IN, OUT> {

    OUT get(IN input);
}
