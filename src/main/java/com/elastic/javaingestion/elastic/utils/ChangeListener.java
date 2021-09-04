package com.elastic.javaingestion.elastic.utils;

import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
@Controller
public class ChangeListener implements FileChangeListener {
    private final ContentProcessor cp;

    public ChangeListener(ContentProcessor contentProcessor){
        this.cp = contentProcessor;
    }

    @Override
    public void onChange(Set<ChangedFiles> changeSet) {
        for(ChangedFiles cfiles : changeSet) {
            for(ChangedFile cfile: cfiles.getFiles()) {
                /* (cfile.getType().equals(Type.MODIFY)
                     || cfile.getType().equals(Type.ADD)
                     || cfile.getType().equals(Type.DELETE) ) && */
                if( !isLocked(cfile.getFile().toPath())) {
                    System.out.println("Operation: " + cfile.getType()
                            + " On file: "+ cfile.getFile().getName() + " is done");

                    System.out.println(cfile.getFile().getName() +" * "+ cfile.getFile().toPath());


                    readFile(cfile.getFile().getName(), cfile.getFile().toPath());


                }else{
                    System.out.println("A "+cfile.getType()+" file detected is LOCKED");
                }
            }
        }
    }

    private boolean isLocked(Path path) {
        try (FileChannel ch = FileChannel.open(path, StandardOpenOption.WRITE); FileLock lock = ch.tryLock()) {
            return lock == null;
        } catch (IOException e) {
            return true;
        }
    }

    private void uploadFile(String name, String ext, Path path){

    }

    private void readFile(String name, Path path){
        try {
            String imgs = "jpg jpeg png gif pdf doc docx xlcx";
            String ext = StringUtils.getFilenameExtension(path.toString());
            if(imgs.contains(ext)){
                cp.uploadFile(name, ext, path);
            }else{
                BufferedReader reader = Files.newBufferedReader(path);
                cp.run(reader, ext, path);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
