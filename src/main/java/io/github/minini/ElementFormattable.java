package io.github.minini;

import io.github.minini.element.IniElement;

/**
 *
 * Element formattable.
 * used by
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public interface ElementFormattable<E extends IniElement> {

    /**
     * format charSequence value as IniElement
     * @param value a CharSequence value
     * @param line line number
     * @return {@link IniElement}
     */
    E format(CharSequence value, int line);

}
