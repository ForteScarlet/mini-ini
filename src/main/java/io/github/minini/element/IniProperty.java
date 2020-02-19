package io.github.minini.element;

/**
 *
 * Ini file's parameters, like {@code property1=value1 }
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class IniProperty {

    /** {@code property=value}, split by {@code '='} */
    public static final char P_V_SPLIT = '=';

    /** from section */
    private IniSection section;

    private String key;
    private String value;

    public IniProperty(IniSection section, String key, String value){
        this.section = section;
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString(){
        return key + '=' + value;
    }


    public String toSectionString(){
        return String.valueOf(section);
    }


}
