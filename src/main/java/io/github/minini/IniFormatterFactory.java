package io.github.minini;

import io.github.minini.element.IniComment;
import io.github.minini.element.IniProperty;
import io.github.minini.element.IniSection;
import io.github.minini.formatter.ElementFormatter;

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
     * @param commentElementFormatter  a formatter for comment
     * @param sectionElementFormatter  a formatter for section
     * @param propertyElementFormatter a formatter for property
     * @return an {@link IniFormatter}
     */
    IniFormatter apply(ElementFormatter<IniComment> commentElementFormatter,
                       ElementFormatter<IniSection> sectionElementFormatter,
                       ElementFormatter<IniProperty> propertyElementFormatter);

}
