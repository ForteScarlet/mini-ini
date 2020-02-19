package io.github.minini;

import io.github.minini.element.IniElement;

import java.io.Closeable;

/**
 * <pre> ini formatter.
 * <pre> Generally the formatter requires three chars like
 * {@link IniReader#SECTIONS_HEAD},
 * {@link IniReader#SECTIONS_END},
 * {@link IniReader#PARAMETER_SPLIT}
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public interface IniFormatter extends Closeable, Initializable {

    /** the new line separator on current system. */
    String NEW_LINE_SEPARATOR = System.getProperty("line.separator", "\r\n");

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
