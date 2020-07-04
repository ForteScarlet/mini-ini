package com.forte.test;

import love.forte.minini.Ini;
import love.forte.minini.IniReader;
import love.forte.minini.reader.BufferedIniReader;

import java.io.InputStream;

/**
 *
 * Demo for read a ini file from stream
 *
 */
public class Demo1 {
    public static void main(String[] args) throws Exception {
        // Get input stream from resources.
        InputStream iniInput = Demo1.class.getClassLoader().getResourceAsStream("test.ini");
        System.out.println(iniInput);

        /*
            If you want to change or use some of your own rules, you can see:
            IniReader
            BufferedIniReader
            IniFormatterFactory (function implement: DefaultIniFormatter::new)
            CommentElementFormatter
            SectionElementFormatter
            PropertyElementFormatter
         */

        // Get ini reader by default reader: BufferedIniReader.
        // By default, there is only this one implementation class. You can implement it independently.
        IniReader ir = new BufferedIniReader();
        // read to ini
        Ini ini = ir.read(iniInput);

        // show ini
        System.out.println(ini);

        // to properties and show
        ini.toProperties().forEach((k, v) -> {
            System.out.println(k + "=" + v);
        });


    }
}
