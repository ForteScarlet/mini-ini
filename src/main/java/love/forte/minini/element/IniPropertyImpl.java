/*
 * Copyright (c) 2020. ForteScarlet All rights reserved.
 * Project  mini-ini
 * File     IniPropertyImpl.java
 *
 * You can contact the author through the following channels:
 * github https://github.com/ForteScarlet
 * gitee  https://gitee.com/ForteScarlet
 * email  ForteScarlet@163.com
 *
 */

package love.forte.minini.element;

import java.util.function.Function;

/**
 * Ini file's parameters, like {@code property1=value1 }
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class IniPropertyImpl extends BaseElement implements IniProperty {

    /**
     * from section
     */
    private IniSection section;

    private String key;
    // private value. The value in super class, no need here.

    /**
     * IniProperty constructor
     * @param section       property section
     * @param key           the property's key, not null
     * @param value         the property's value, null able
     * @param originalValue the original value of this property line
     * @param lineNumber    line number
     */
    public IniPropertyImpl(IniSection section, String key, String value, String originalValue, int lineNumber) {
        super(value, originalValue, lineNumber);
        this.section = section;
        this.key = key;
    }

    /**
     * IniProperty constructor without section. maybe init later
     * @see #IniPropertyImpl(IniSection, String, String, String, int)
     * */
    public IniPropertyImpl(String key, String value, String originalValue, int lineNumber) {
        super(value, originalValue, lineNumber);
        this.key = key;
    }

    /**
     * IniProperty constructor
     * @param section       property section
     * @param key           the property's key, not null
     * @param value         the property's value, null able
     * @param lineNumber    line number
     */
    public IniPropertyImpl(IniSection section, String key, String value, int lineNumber) {
        super(value, key + P_V_SPLIT + value, lineNumber);
        this.section = section;
        this.key = key;
    }

    /**
     * IniProperty constructor without section. maybe init later
     * @see #IniPropertyImpl(IniSection, String, String, String, int)
     * */
    public IniPropertyImpl(String key, String value, int lineNumber) {
        super(value, key + P_V_SPLIT + value, lineNumber);
        this.key = key;
    }


    /**
     * section setter.
     */
    @Override
    public void setSection(IniSection section) {
        this.section = section;
    }

    /**
     * section getter.
     */
    @Override
    public IniSection getSection() {
        return this.section;
    }

    /**
     * get key value
     *
     * @return String field: key
     */
    @Override
    public String key() {
        return this.key;
    }

    /**
     * change key value.
     *
     * @param newKey new key.
     */
    @Override
    public void changeKey(String newKey) {
        this.key = newKey;
    }

    /**
     * set a new Key.
     *
     * @param newKey new Key
     * @return old value.
     */
    @Override
    public String setKey(String newKey) {
        // old
        String old = key;
        // change key
        changeKey(newKey);
        // update originalValue
        setOriginalValue(keyChanged(newKey));
        return old;
    }

    /**
     * when key changed, get the new originalValue.
     *
     * @param newKey new key.
     * @return original value.
     */
    protected String keyChanged(String newKey) {
        return key + P_V_SPLIT + newKey;
    }

    /**
     * when value changed, update originalValue.
     *
     * @param newValue when {@code value} changes, like {@link #setValue(String)} or {@link #setValue(Function)}
     * @return new originalValue
     */
    @Override
    protected String valueChanged(String newValue) {
        return key + P_V_SPLIT + newValue;
    }

    /**
     * <pre> default ini property's comment is null.
     * <pre> there may be comments at the end of each element.
     * <pre> or null.
     * <pre> if this element is comment, return itself.
     * <pre> so, nullable, or see {@link #getCommentOptional}.
     * @see #getCommentOptional()
     * @return comment end of the element or null. if element, return itself.
     */
    @Override
    public IniComment getComment() {
        return null;
    }

    //**************** implements Map.Empty methods ****************//

    /**
     * @see #key()
     */
    @Override
    public String getKey() {
        return key();
    }

}
