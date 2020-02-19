package io.github.minini.element;

import java.util.List;

/**
 *
 * Ini file's Section
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class IniSection {

    /** ini sections head, {@code "[sections]" -> '['}*/
    public static final char HEAD = '[';
    /** ini sections end,  {@code "[sections]" -> ']'} */
    public static final char END = ']';

    /** title */
    private String title;
    /** ending comment, or null */
    private IniComment comment;
    /** list of properties, or empty */
    private List<IniProperty> properties;


}
