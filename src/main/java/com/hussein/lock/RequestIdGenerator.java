package com.hussein.lock;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Title: RequestIdGenerator</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/11 6:23 PM
 */
public class RequestIdGenerator {

    private static final RequestIdGenerator INSTANCE = new RequestIdGenerator();

    private static final int SEQ_MAX_LIMIT = 999;

    private int seq = -1;

    private RequestIdGenerator() {
    }

    public String nextId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        DecimalFormat df = new DecimalFormat("000");
        return sdf.format(new Date()) + df.format(seq >= SEQ_MAX_LIMIT ? 0 : ++seq);
    }

    public static RequestIdGenerator getInstance() {
        return INSTANCE;
    }
}
