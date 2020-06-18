package com.forte.test;

import love.forte.minini.Ini;
import love.forte.minini.IniReader;
import love.forte.minini.reader.BufferedIniReader;

import java.io.InputStream;

/**
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class Demo1 {
    public static void main(String[] args) throws Exception {
        InputStream in = Demo1.class.getClassLoader().getResourceAsStream("test.ini");

        System.out.println(in);

        /*
            If you want to change or use some of your own rules, you can see:
            IniReader
            BufferedIniReader
            IniFormatterFactory (function implement: DefaultIniFormatter::new)
            CommentElementFormatter
            SectionElementFormatter
            PropertyElementFormatter
         */
        IniReader ir = new BufferedIniReader();
        Ini ini = ir.read(in);

        ini.toProperties().forEach((k, v) -> {
            System.out.println(k + "=" + v);
        });


//        ini.write(Paths.get("F:\\forstudy\\demo\\test2.ini"), false);

    }
}
