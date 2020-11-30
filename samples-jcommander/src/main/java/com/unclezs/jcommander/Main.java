package com.unclezs.jcommander;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.UnixStyleUsageFormatter;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 用法示例
 *
 * @author blog.unclezs.com
 * @date 2020/12/1 12:15 上午
 */
public class Main {
    @Parameter(names = {"--path", "-p"}, description = "文件路径", order = 1)
    private String path;
    @Parameter(names = {"--version", "-v"}, description = "版本", order = 2, arity = 0)
    private boolean version;
    @Parameter(names = {"-h"}, description = "帮助", order = 3, arity = 0)
    private boolean usage;
    @Parameter(names = "--help", help = true)
    private boolean help;


    public static void main(String[] args) {
        Main pathUtil = new Main();
        JCommander jCommander = JCommander.newBuilder()
                .programName("pathUtil")
                .addObject(pathUtil)
                .build();
        jCommander.setUsageFormatter(new UnixStyleUsageFormatter(jCommander));
        jCommander.parse(args);
        if (pathUtil.version) {
            System.out.println("pathUtil version 6.6.6");
            return;
        }
        if (pathUtil.path != null) {
            System.out.println("path exist? " + Files.exists(Paths.get(pathUtil.path)));
            return;
        }
        if (pathUtil.usage) {
            jCommander.usage();
            return;
        }
        if (pathUtil.help) {
            System.out.println("help invoke");
        }
    }
}
