package com.forte.test;

import love.forte.minini.ElementFormattable;
import love.forte.minini.Ini;
import love.forte.minini.IniBuilder;
import love.forte.minini.element.IniSection;
import love.forte.minini.formatter.ElementFormatter;
import love.forte.minini.formatter.SectionElementFormatter;
import love.forte.minini.reader.BufferedIniReader;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;

/**
 * Demo for create a ini and write
 */
public class Demo3 {
    public static void main(String[] args) throws Exception {
        InputStream iniInput = Demo1.class.getClassLoader().getResourceAsStream("test.ini");
        // Read by customize
        final BufferedIniReader iniReader = new BufferedIniReader();
        // set section formatter supplier
        iniReader.setSectionElementFormatterSupplier(MyIniSectionFormatter::new);


        final Ini ini = iniReader.read(iniInput);

        // remember

        System.out.println(ini);
    }
}

/**
 * my section formatter
 */
class MyIniSectionFormatter extends SectionElementFormatter {
    // maybe .. change some

    @Override
    public IniSection format(String value, int line) {
        System.out.println("my format ! " + value + "("+ line +")");
        // super
        return super.format(value, line);
    }
}
