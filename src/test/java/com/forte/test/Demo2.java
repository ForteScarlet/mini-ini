package com.forte.test;

import love.forte.minini.Ini;
import love.forte.minini.IniBuilder;
import love.forte.minini.IniReader;
import love.forte.minini.reader.BufferedIniReader;

import java.io.File;
import java.nio.file.Paths;

/**
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class Demo2 {
    public static void main(String[] args) throws Exception {
        IniBuilder b = new IniBuilder().plusSection("sec1", "this is a section")
                .plusProperty("key1", "value")
                .plusProperty("key2", "value")
                .plusProperty("key3", "value")
                .plusProperty("key4", "value")
                .plusProperty("key5", "value")
                .plusProperty("key6", "value")
                .plusSection("sec2")
                .plusProperty("key1", "value")
                .plusProperty("key2", "value")
                .plusProperty("key3", "value")
                .plusProperty("key4", "value")
                .plusProperty("key5", "value")
                .plusProperty("key6", "value")
        ;

        final Ini ini = b.build();

        ini.write(Paths.get("F:\\forstudy\\demo\\test3.ini"), true);

        Ini ri = new BufferedIniReader().read(new File("F:\\forstudy\\demo\\test3.ini"));

        System.out.println(ri);
        System.out.println("--");
        ri.toProperties().forEach((k, v) -> System.out.println(k + "=" + v));

    }
}
