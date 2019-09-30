package io.avec.filewatcher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

@Slf4j
@Service
public class FileWatcherService {

    void watch(FileListener fileListener) {
        try {

            WatchService watcher = FileSystems.getDefault().newWatchService();
            Path logDir = Paths.get(fileListener.getDirectory());
//            logDir.register(watcher, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
            logDir.register(watcher, ENTRY_MODIFY);

            // Monitor the logDir at listen for change notification.
            for (;;) {
                WatchKey key = watcher.take();

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();


                    @SuppressWarnings("unchecked") WatchEvent<Path> ev = (WatchEvent<Path>)event;
                    Path context = ev.context();

                    // there are 2-3 events pr take(). Here we get the one with file matching test.txt
                    if(kind == ENTRY_MODIFY && context.endsWith(fileListener.getFileName())) {
                        fileListener.onChange();
                    }
                }
                boolean valid = key.reset();
                if(!valid) {
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            log.error("Exception occurred.", e);
        }
    }

}
