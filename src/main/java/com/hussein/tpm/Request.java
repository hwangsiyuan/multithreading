package com.hussein.tpm;

/**
 * <p>Title: Request</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/8 11:10 AM
 */
public class Request {

    private String business;

    public Request(String business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return business;
    }
}
