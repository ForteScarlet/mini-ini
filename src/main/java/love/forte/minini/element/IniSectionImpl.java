/*
 * Copyright (c) 2020. ForteScarlet All rights reserved.
 * Project  mini-ini
 * File     IniSectionImpl.java
 *
 * This project is protected by the open source agreement Apache Licence 2.0.
 * You can find this agreement from https://github.com/ForteScarlet/mini-ini.
 *
 * You can contact the author through the following channels:
 * github https://github.com/ForteScarlet
 * gitee  https://gitee.com/ForteScarlet
 * email  ForteScarlet@163.com
 * QQ     1149159218
 *
 */

package love.forte.minini.element;

import love.forte.minini.util.ProxyList;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * Ini file's Section
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class IniSectionImpl extends BaseElement implements IniSection {
    private static final long serialVersionUID = 0xFF00FFFF;

    /** list of properties, or empty */
    private List<IniProperty> properties;

    public IniSectionImpl(String value, int lineNumber) {
        super(value, HEAD + value + END, lineNumber);
        properties = new ArrayList<>();
    }

    public IniSectionImpl(String value, String originalValue, int lineNumber) {
        super(value, originalValue, lineNumber);
        properties = new ArrayList<>();
    }

    public IniSectionImpl(String value, String originalValue, int lineNumber, Supplier<List<IniProperty>> listSupplier) {
        super(value, originalValue, lineNumber);
        properties = listSupplier.get();
    }

    /**
     * maybe have comment
     */
    public IniSectionImpl(String value, int lineNumber, IniComment comment) {
        super(value, HEAD + value + END, lineNumber, comment);
        properties = new ArrayList<>();
    }
    /**
     * maybe have comment
     */
    public IniSectionImpl(String value, String originalValue, int lineNumber, IniComment comment) {
        super(value, originalValue, lineNumber, comment);
        properties = new ArrayList<>();
    }
    /**
     * maybe have comment
     */
    public IniSectionImpl(String value, String originalValue, int lineNumber, IniComment comment, Supplier<List<IniProperty>> listSupplier) {
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
    @Override
    public String toPropertiesString(){
        StringJoiner joiner = new StringJoiner(System.getProperty("line.separator", "\n"));
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
    @Override
    public List<IniProperty> getList(){
        return new ArrayList<>(properties);
    }

    /**
     * get IniProperty list. will copy a new list.
     * @return list.
     */
    @Override
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
