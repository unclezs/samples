package com.unclezs.jcommander;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.IParameterSplitter;

import java.util.Arrays;
import java.util.List;

/**
 * @author blog.unclezs.com
 * @date 2020/12/1 12:01 上午
 */
public class SplitterSample {
    @Parameter(names = "-l", splitter = SemiColonSplitter.class)
    private List<String> list;

    public static void main(String... argv) {
        String[] args = {"-l", "1;2;3;4","-l","5"};
        SplitterSample main = new SplitterSample();
        JCommander.newBuilder()
                .verbose(1)
                .addObject(main)
                .build()
                .parse(args);
        System.out.println("list: " + main.list);
    }

    public static class SemiColonSplitter implements IParameterSplitter {
        @Override
        public List<String> split(String value) {
            return Arrays.asList(value.split(";"));
        }
    }
}
