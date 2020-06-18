package com.forte.test;

import love.forte.minini.Ini;
import love.forte.minini.IniReader;
import love.forte.minini.reader.BufferedIniReader;

import java.io.InputStream;
import java.nio.file.Paths;

/**
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class Demo1 {
    public static void main(String[] args) throws Exception {
        InputStream in = Demo1.class.getClassLoader().getResourceAsStream("test.ini");

        System.out.println(in);

        IniReader ir = new BufferedIniReader();
        Ini ini = ir.read(in);

        ini.toProperties().forEach((k, v) -> {
            System.out.println(k + "=" + v);
        });


//        ini.write(Paths.get("F:\\forstudy\\demo\\test2.ini"), false);

    }
}
