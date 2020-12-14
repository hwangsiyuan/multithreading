package com.hussein.dirmonitor;

import com.hussein.eventbus.EventBus;

import java.nio.file.*;

/**
 * <p>Title: DirectoryMonitor</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/13 6:10 PM
 */
public class DirectoryMonitor {

    private EventBus eventBus;

    private WatchService watchService;

    private Path path;

    private volatile boolean start = false;

    public DirectoryMonitor(EventBus eventBus, final String targetPath) {
        this(eventBus, targetPath, "");
    }

    public DirectoryMonitor(EventBus eventBus, final String targetPath, String... morePath) {
        this.eventBus = eventBus;
        this.path = Paths.get(targetPath, morePath);
        System.out.println(Files.exists(path));
    }

    public void monitor() throws Exception {
        this.watchService = FileSystems.getDefault().newWatchService();
        this.path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
        System.out.printf("The directory [%s] is monitoring..\n", path);
        this.start = true;
        while (true) {
            WatchKey watchKey = null;
            try {
                watchKey = watchService.take();
                watchKey.pollEvents().forEach(e -> {
                    WatchEvent.Kind<?> kind = e.kind();
                    Path currentPath = (Path) e.context();
                    //将相对路径转为绝对路径
                    Path absolutePath = DirectoryMonitor.this.path.resolve(currentPath);
                    eventBus.post(new FileChangeEvent(absolutePath, kind));
                });
            } catch (Exception exception) {
                this.start = false;
            } finally {
                if (watchKey != null) {
                    watchKey.reset();
                }
            }
        }
    }

    public void stopMonitor() throws Exception {
        System.out.printf("The directory [%s] monitor will be stop.\n", path);
        Thread.currentThread().interrupt();
        this.start = false;
        this.watchService.close();
        System.out.printf("The directory [%s] monitor will be stop done.\n", path);
    }
}
