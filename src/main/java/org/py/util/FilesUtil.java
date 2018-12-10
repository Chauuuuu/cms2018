package org.py.util;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FilesUtil {
    private String rootpath;
    private Path root;
    public static final String DIRS = "dirlist";
    public static final String FILES = "filelist";

    public FilesUtil(String rootpath) throws IOException {
        ClassPathResource resource = new ClassPathResource(rootpath);
        root = Paths.get(resource.getFile().getAbsolutePath());
    }

    public Map<String, List<Path>> childlist(Path dir) throws IOException {
        Map<String, List<Path>> map = new LinkedHashMap<>();
        List<Path> dirlist = new ArrayList<>();
        List<Path> filelist = new ArrayList<>();
        if(null != dir && dir.isAbsolute()) {
            Files.list(dir)
                    .forEach(it -> {
                        if(Files.isDirectory(it))
                            dirlist.add(it);
                        else
                            filelist.add(it);
                    });
            map.put("dirlist", dirlist);
            map.put("filelist", filelist);
        }
        return map;
    }

    public String getRootpath() {
        return rootpath;
    }

    public Path getRoot() {
        return root;
    }

}
