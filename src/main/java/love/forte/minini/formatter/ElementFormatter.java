/*
 * Copyright (c) 2020. ForteScarlet All rights reserved.
 * Project  mini-ini
 * File     ElementFormatter.java
 *
 * You can contact the author through the following channels:
 * github https://github.com/ForteScarlet
 * gitee  https://gitee.com/ForteScarlet
 * email  ForteScarlet@163.com
 *
 */

package love.forte.minini.formatter;

import love.forte.minini.ElementFormattable;
import love.forte.minini.element.IniElement;

/**
 * <pre> elementFormatter, format a String value as {@link IniElement}.
 * <pre> Other than CommentElementFormatter, All element formatters should additionally require a comment element formatter
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public interface ElementFormatter<E extends IniElement> extends ElementFormattable<E> {

    /**
     * check this value.
     * @param value value
     * @return true if can.
     */
    boolean check(String value);

    /**
     * <pre> this method will not check value, so you should {@link #check(String)} first.
     * <pre> However, not checking will not necessarily report an error, but may result in non-compliance.
     * @param value a String value
     * @param line line number
     * @return {@link E}, can not be null.
     */
    @Override
    E format(String value, int line);

}
