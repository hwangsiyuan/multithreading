package com.hussein.pool;

/**
 * <p>Title: RunableDenyException</p>
 * <p>Description: 任务队列无法接收新任务异常</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/25 2:14 PM
 */
public class RunableDenyException extends RuntimeException {

    public RunableDenyException(String message) {
        super(message);
    }
}
