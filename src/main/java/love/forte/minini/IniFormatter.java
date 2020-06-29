/*
 * Copyright (c) 2020. ForteScarlet All rights reserved.
 * Project  mini-ini
 * File     IniFormatter.java
 *
 * You can contact the author through the following channels:
 * github https://github.com/ForteScarlet
 * gitee  https://gitee.com/ForteScarlet
 * email  ForteScarlet@163.com
 * QQ     1149159218
 *
 */

package love.forte.minini;

import love.forte.minini.element.IniElement;

import java.io.Closeable;

/**
 * <pre> ini formatter.
 * <pre> Generally the formatter requires three chars like
 * {@link IniReader#SECTIONS_HEAD},
 * {@link IniReader#SECTIONS_END},
 * {@link IniReader#PARAMETER_SPLIT}
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public interface IniFormatter extends Closeable, Initialisable {

    /** the new line separator on current system. */
    String NEW_LINE_SEPARATOR = System.getProperty("line.separator", "\n");

    /**
     * format a line as a element
     * @param line line string
     * @return {@link IniElement}
     */
    IniElement formatLine(String line);

    /**
     * by default, {@code close() = init()}
     */
    @Override
    default void close() {
        init();
    }

}
