package com.hussein.hook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: PreventDuplicated</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/24 5:51 PM
 */
public class PreventDuplicated {

    private static final String LOCK_PATH = System.getProperty("user.dir");

    private static final String LOCK_FILE = ".lock";

    private static final String PERMISSIONS = "rw-------";

    public static void main(String[] args) {
        PreventDuplicated pd = new PreventDuplicated();
        pd.run();
    }

    private void run() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("the program is stopping..");
            getLockFile().toFile().delete();
        }));
        checkRunning();
        for (; ; ) {
            System.out.println("the program is running");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkRunning() {
        Path path = getLockFile();
        if (path.toFile().exists()) {
            throw new RuntimeException("the program already running...");
        }
        try {
            Files.createFile(path, PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString(PERMISSIONS)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Path getLockFile() {
        return Paths.get(LOCK_PATH, LOCK_FILE);
    }
}
