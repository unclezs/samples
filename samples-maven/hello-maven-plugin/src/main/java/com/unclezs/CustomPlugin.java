package com.unclezs;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * @author blog.unclezs.com
 * @date 2020/12/6 2:51 上午
 */
@Mojo(name = "custom")
public class CustomPlugin extends AbstractMojo {
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("hello maven plugin!!!");
    }
}
