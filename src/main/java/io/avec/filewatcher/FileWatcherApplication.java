package io.avec.filewatcher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * The application references a FileRepository implementing FileListener and
 * a FileWatcherService using WatchService that notifies about changes done in
 * the spesified directory
 *
 * Start application and modify test.txt
 */
@Slf4j
@SpringBootApplication
public class FileWatcherApplication implements CommandLineRunner {

    @Autowired
    private FileWatcherService fileWatcherService;

    @Autowired
    private MyFileRepository myFileRepository;

    public static void main(String[] args) {
        SpringApplication.run(FileWatcherApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fileWatcherService.watch(myFileRepository);
    }

    @Bean
    MyFileRepository myFileRepository() {
        return new MyFileRepository();
    }
}
