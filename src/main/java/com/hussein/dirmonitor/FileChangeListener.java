package com.hussein.dirmonitor;

import com.hussein.eventbus.Subscribe;

/**
 * <p>Title: FileChangeListener</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/14 10:40 AM
 */
public class FileChangeListener {

    @Subscribe
    public void onChange(FileChangeEvent event) {
        System.out.printf("[%s]-%s\n", event.getPath(), event.getKind());
    }
}
