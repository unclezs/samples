package com.unclezs.jcommander;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author blog.unclezs.com
 * @since 2020/11/30 18:34
 */
public class QuickStartSample {
    public static void main(String[] a) {
        Args args = new Args();
        String[] argv = {"-log", "2", "-groups", "unit"};
        JCommander.newBuilder()
                .verbose(1)
                .addObject(args)
                .build()
                .parse(argv);
    }

    static class Args {
        @Parameter
        private final List<String> parameters = new ArrayList<>();

        @Parameter(names = {"-log", "-verbose"}, description = "Level of verbosity")
        private final Integer verbose = 1;

        @Parameter(names = "-groups", description = "Comma-separated list of group names to be run")
        private String groups;

        @Parameter(names = "-debug", description = "Debug mode")
        private final boolean debug = false;
    }
}


