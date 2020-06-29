/*
 * Copyright (c) 2020. ForteScarlet All rights reserved.
 * Project  mini-ini
 * File     IniFormatterFactory.java
 *
 * You can contact the author through the following channels:
 * github https://github.com/ForteScarlet
 * gitee  https://gitee.com/ForteScarlet
 * email  ForteScarlet@163.com
 * QQ     1149159218
 *
 */

package love.forte.minini;

import love.forte.minini.element.IniComment;
import love.forte.minini.element.IniProperty;
import love.forte.minini.element.IniSection;
import love.forte.minini.formatter.ElementFormatter;

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
