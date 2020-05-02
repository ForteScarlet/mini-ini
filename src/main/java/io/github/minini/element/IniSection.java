package io.github.minini.element;

import io.github.minini.Ini;
import io.github.minini.util.ProxyList;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * Ini file's Section
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class IniSection extends BaseElement implements ProxyList<IniProperty> {

    /** ini sections head, {@code "[sections]" -> '['}*/
    public static final char HEAD = '[';
    /** ini sections end,  {@code "[sections]" -> ']'} */
    public static final char END = ']';

    /** list of properties, or empty */
    private List<IniProperty> properties;

    public IniSection(String value, String originalValue, int lineNumber) {
        super(value, originalValue, lineNumber);
        properties = new ArrayList<>();
    }
    public IniSection(String value, String originalValue, int lineNumber, Supplier<List<IniProperty>> listSupplier) {
        super(value, originalValue, lineNumber);
        properties = listSupplier.get();
    }
    /**
     * maybe have comment
     */
    public IniSection(String value, String originalValue, int lineNumber, IniComment comment) {
        super(value, originalValue, lineNumber, comment);
        properties = new ArrayList<>();
    }
    /**
     * maybe have comment
     */
    public IniSection(String value, String originalValue, int lineNumber, IniComment comment, Supplier<List<IniProperty>> listSupplier) {
        super(value, originalValue, lineNumber, comment);
        properties = listSupplier.get();
    }


    /**
     * If the {@code value} changed, change the originalValue
     *
     * @param newValue when {@code value} changes, like {@link #setValue(String)} or {@link #setValue(Function)}
     * @return new originalValue
     */
    @Override
    protected String valueChanged(String newValue) {
        return "[" + newValue + "]";
    }

    /**
     * toString, with all iniProperties value.
     * @return string with properties value.
     */
    public String toPropertiesString(){
        StringJoiner joiner = new StringJoiner(System.getProperty("line.separator", "\r\n"));
        joiner.add(toString());
        for (IniProperty p : this) {
            joiner.add(p);
        }
        return joiner.toString();
    }

    /**
     * get IniProperty list. will copy a new list.
     * @return list.
     */
    public List<IniProperty> getList(){
        return new ArrayList<>(properties);
    }

    /**
     * get IniProperty list. will copy a new list.
     * @return list.
     */
    public List<IniProperty> getList(Supplier<List<IniProperty>> listSupplier){
        List<IniProperty> list = listSupplier.get();
        list.addAll(properties);
        return list;
    }



    /**
     * <pre> if you want to get the {@code IniProperty} list, use {@link #getList()} or {@link #getList(Supplier)}.
     * <pre> this method is used by {@link ProxyList}
     * @return the real list.
     */
    @Override
    public List<IniProperty> getProxyList() {
        return properties;
    }
}
