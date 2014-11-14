package org.summer.ioc.scanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClassScanner {
    public static String[] scanClasses(File directory, String packageName)
            throws ClassNotFoundException {
        List<String> classNames = new ArrayList<String>();
        if (!directory.exists()) {
            return (String[]) classNames.toArray(new String[classNames.size()]);
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.endsWith(".class")) {
                String className = fileName.substring(0,
                        fileName.lastIndexOf("."));
                classNames.add(packageName + '.' + className);
            }
        }
        return (String[]) classNames.toArray(new String[classNames.size()]);
    }
}
