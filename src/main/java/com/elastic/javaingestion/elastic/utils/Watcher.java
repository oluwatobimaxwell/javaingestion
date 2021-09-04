package com.elastic.javaingestion.elastic.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.io.File;
import java.time.Duration;

@Configuration
public class Watcher {

    private final ChangeListener listener;

    @Autowired
    public Watcher(ChangeListener listener){
        this.listener = listener;
    }

    @Bean
    public FileSystemWatcher fileSystemWatcher() {
        FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(true, Duration.ofMillis(5000L), Duration.ofMillis(3000L));
        //fileSystemWatcher.addSourceFolder(new File("/path/to/folder"));
        fileSystemWatcher.addSourceDirectory(new File("/Users/oluwatobisholanke/Documents/elasticsearch"));
        fileSystemWatcher.addListener(this.listener);
        fileSystemWatcher.start();
        System.out.println("started fileSystemWatcher");
        return fileSystemWatcher;
    }

    @PreDestroy
    public void onDestroy() throws Exception {
        fileSystemWatcher().stop();
    }
}
