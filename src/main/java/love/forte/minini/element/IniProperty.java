package love.forte.minini.element;

import java.util.Map;

/**
 * Ini file's parameters, like {@code property1=value1 }
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public interface IniProperty extends Map.Entry<String, String>, IniElement {

    /**
     * {@code property=value}, split by {@code '='}
     */
    char P_V_SPLIT = '=';


    /**
     * section setter.
     * @param section from section
     */
    void setSection(IniSection section);

    /**
     * section getter.
     * @return from section
     */
    IniSection getSection();

    /**
     * get key value
     *
     * @return String field: key
     */
    String key();

    /**
     * change key value.
     *
     * @param newKey new key.
     */
    void changeKey(String newKey);

    /**
     * set a new Key.
     *
     * @param newKey new Key
     * @return old value.
     */
    String setKey(String newKey);



    //**************** implements Map.Empty methods ****************//

    /**
     * get key
     * @see #key()
     * @return key
     */
    @Override
    default String getKey(){
        return key();
    }


    /**
     * get value
     * @see #value()
     * @return value
     */
    @Override
    default String getValue() {
        return value();
    }
}
