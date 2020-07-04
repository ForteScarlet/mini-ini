/*
 * Copyright (c) 2020. ForteScarlet All rights reserved.
 * Project  mini-ini
 * File     PropertyElementFormatter.java
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

package love.forte.minini.formatter;


import love.forte.minini.element.IniProperty;
import love.forte.minini.element.IniPropertyImpl;

/**
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class PropertyElementFormatter extends BaseElementFormatter<IniProperty> {

    /** key value split character, {@link IniProperty#P_V_SPLIT} */
    private char split;

    /**
     * constructor, need a comment element formatter.
     * @param commentElementFormatter {@link CommentElementFormatter}
     */
    public PropertyElementFormatter(CommentElementFormatter commentElementFormatter){
        super(commentElementFormatter);
        this.split = IniProperty.P_V_SPLIT;
    }
    /** constructor, the comment element formatter used new {@link CommentElementFormatter} */
    public PropertyElementFormatter(){
        this.split = IniProperty.P_V_SPLIT;
    }
    /**
     * constructor, need a comment element formatter.
     * @param commentElementFormatter {@link CommentElementFormatter}
     */
    public PropertyElementFormatter(char split, CommentElementFormatter commentElementFormatter){
        super(commentElementFormatter);
        this.split = split;
    }
    /** constructor, the comment element formatter used new {@link CommentElementFormatter} */
    public PropertyElementFormatter(char split){
        this.split = split;
    }


    /**
     * check this value.
     * if contains '=' and this character is not the first, return true
     * @param value value
     * @return true if can.
     */
    @Override
    public boolean check(String value) {
        return value.indexOf(split) > 0;
    }

    /**
     * <p> this method will not check value, so you should {@link #check(String)} first. </p>
     * <p> However, not checking will not necessarily report an error,
     * but may result in non-compliance. </p>
     * <p> property split by '=', like {@link IniProperty#P_V_SPLIT} </p>
     * <p> <b>note: No comment can appear on the property line</b> </p>
     * @param value a String value
     * @param line line number
     * @return {@link IniProperty}, can not be null.
     */
    @Override
    public IniProperty format(String value, int line) {
        String[] split = value.split(String.valueOf(IniProperty.P_V_SPLIT), 2);
        if(split.length == 1){
            // only one, Description no '=', Then its value is null
            split = new String[]{split[0], null};
        }
        final String propKey = split[0];
        final String propValue = split[1];

        return new IniPropertyImpl(propKey, propValue, value, line);
    }
}
