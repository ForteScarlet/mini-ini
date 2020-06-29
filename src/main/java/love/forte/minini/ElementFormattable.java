/*
 * Copyright (c) 2020. ForteScarlet All rights reserved.
 * Project  mini-ini
 * File     ElementFormattable.java
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

/**
 *
 * Element formattable.
 * used by
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public interface ElementFormattable<E extends IniElement> {

    /**
     * 
     * format String value as IniElement
     * @param value a String value
     * @param line line number
     * @return {@link IniElement}
     */
    E format(String value, int line);

}
