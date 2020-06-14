package com.leetcode.qiaok;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @since 2020-05-20
 * @author qiaokE:\work_idea
 */
public class TaskCal {
    private static String root = "E:\\work_idea/";
    public static void main(String[] args) throws IOException {
        File dir = new File(root + "learn/leetcode/leetcode-java/src/main/java/com/leetcode/qiaok");
        String now = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now()/*.plusDays(-1)*/);
        System.out.println("今日任务：" + now);

        if(dir!=null&&dir.isDirectory()){
            File[] subDirs = dir.listFiles();
            if(subDirs!=null){
                for (File subDir:subDirs) {
                    if(subDir.isDirectory()){
                        File[] files = subDir.listFiles();
                        if(files!=null){
                            for (File file:files) {
                                String content = FileUtils.readFileToString(file, "utf8");
                                if(content.indexOf(now)>0&&!(file.getPath().indexOf("practice1")>=0)){
                                    System.out.println(file.getPath());
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}
