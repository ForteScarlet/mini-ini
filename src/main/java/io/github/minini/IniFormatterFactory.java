package io.github.minini;

import io.github.minini.element.IniComment;
import io.github.minini.element.IniProperty;
import io.github.minini.element.IniSection;

/**
 * <pre> function interface for iniFormatter
 * <pre> Generally the formatter requires three chars like
 * {@link IniReader#SECTIONS_HEAD},
 * {@link IniReader#SECTIONS_END},
 * {@link IniReader#PARAMETER_SPLIT}
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public interface IniFormatterFactory {

    /**
     * get an iniFormatter by three chars.
     * @param sectionsHead   like {@link IniSection#HEAD}
     * @param sectionsEnd    like {@link IniSection#END}
     * @param parameterSplit like {@link IniProperty#P_V_SPLIT}
     * @param parameterSplit like {@link IniComment#HEAD}
     * @return an {@link IniFormatter}
     */
    IniFormatter apply(char sectionsHead, char sectionsEnd, char parameterSplit, char commentHead);

}
