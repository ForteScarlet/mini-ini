package com.forte.test;

import love.forte.minini.Ini;
import love.forte.minini.IniBuilder;
import love.forte.minini.reader.BufferedIniReader;

import java.io.File;
import java.nio.file.Paths;

/**
 * Demo for create a ini and write
 */
public class Demo2 {
    public static void main(String[] args) throws Exception {
        // Create an IniBuilder and
        IniBuilder b = new IniBuilder()
                .plusComment("this is a test ini")
                .skipLine(2)
                .plusSection("sec1", "this is a section")
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

        // Build ini
        final Ini ini = b.build();

        System.out.println(ini);

//        System.out.println("————————————————");
//
//        // Write to file
//        ini.write(Paths.get("F:\\test3.ini"), true);
//
//        // And read it.
//        Ini ri = new BufferedIniReader().read(new File("F:\\test3.ini"));
//
//        // Show
//        System.out.println(ri);
//
//        System.out.println("————————————————");
//
//        // Show by properties
//        ri.toProperties("-").forEach((k, v) -> System.out.println(k + "=" + v));
    }
}
