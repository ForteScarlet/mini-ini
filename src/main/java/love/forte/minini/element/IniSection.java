package love.forte.minini.element;

import love.forte.minini.util.ProxyList;

import java.util.List;
import java.util.function.Supplier;

/**
 *
 * Ini file's Section
 *
 * @see IniSectionImpl
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public interface IniSection extends ProxyList<IniProperty>, IniElement {

    /** ini sections head, {@code "[sections]" -> '['}*/
    char HEAD = '[';
    /** ini sections end,  {@code "[sections]" -> ']'} */
    char END = ']';


    /**
     * toString, with all iniProperties value.
     * @return string with properties value.
     */
    String toPropertiesString();

    /**
     * get IniProperty list. will copy a new list.
     * @return list.
     */
    List<IniProperty> getList();

    /**
     * get IniProperty list. will copy a new list.
     * @param listSupplier ini property list supplier
     * @return list.
     */
    List<IniProperty> getList(Supplier<List<IniProperty>> listSupplier);

}
