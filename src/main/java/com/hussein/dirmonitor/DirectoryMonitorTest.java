package com.hussein.dirmonitor;

import com.hussein.eventbus.EventBus;

/**
 * <p>Title: DirectoryMonitorTest</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/14 10:42 AM
 */
public class DirectoryMonitorTest {

    public static void main(String[] args) throws Exception {
        EventBus eventBus = new EventBus();
        eventBus.register(new FileChangeListener());
        DirectoryMonitor monitor = new DirectoryMonitor(eventBus,"/Users/huangsiyuan/test");
        monitor.monitor();
    }
}
