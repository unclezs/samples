package com.unclezs;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * @author blog.unclezs.com
 * @date 2020/12/6 2:51 上午
 */
@Mojo(name = "param")
public class ParameterPlugin extends AbstractMojo {
    @Parameter(property = "param.name", defaultValue = "${project.artifactId}")
    private String name;
    @Parameter
    private Integer number;
    @Parameter
    private Boolean bool;
    @Parameter
    private Double doubleValue;
    @Parameter
    private Date date;
    @Parameter
    private File file;
    @Parameter
    private URL url;
    @Parameter
    private Color color;
    @Parameter
    private String[] array;
    @Parameter
    private List<String> list;
    @Parameter
    private Map<String, String> map;
    @Parameter
    private Properties properties;
    @Parameter
    private User obj;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("String:"+name);
        getLog().info("Integer:" + number);
        getLog().info("Boolean:" + bool);
        getLog().info("Double:" + doubleValue);
        getLog().info("Date:" + date);
        getLog().info("File:" + file.getName());
        getLog().info("URL:" + url);
        getLog().info("enum:" + color);
        getLog().info("array:" + Arrays.toString(array));
        getLog().info("List:" + list);
        getLog().info("Map:" + map);
        getLog().info("Properties:" + properties);
        getLog().info("obj:" + obj);

    }

    public enum Color {
        GREEN,
        RED,
        BLUE
    }
}
