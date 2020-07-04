/*
 * Copyright (c) 2020. ForteScarlet All rights reserved.
 * Project  mini-ini
 * File     SectionElementFormatter.java
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

import love.forte.minini.IniFormatException;
import love.forte.minini.element.IniComment;
import love.forte.minini.element.IniSection;
import love.forte.minini.element.IniSectionImpl;

/**
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class SectionElementFormatter extends BaseElementFormatter<IniSection> {

    /** section starting char, {@link IniSection#HEAD} */
    private char head;
    /** section ending char, {@link IniSection#END} */
    private char end;

    public SectionElementFormatter(CommentElementFormatter commentElementFormatter){
        super(commentElementFormatter);
        head = IniSection.HEAD;
        end = IniSection.END;
    }
    public SectionElementFormatter(){
        head = IniSection.HEAD;
        end = IniSection.END;
    }
    public SectionElementFormatter(char head, char end, CommentElementFormatter commentElementFormatter){
        super(commentElementFormatter);
        this.head = head;
        this.end = end;
    }
    public SectionElementFormatter(char head, char end){
        this.head = head;
        this.end = end;
    }

    /**
     * <pre> check this value.
     * <pre> if this value's first char == {@code HEAD} value, pass.
     * @param value value
     * @return true if can.
     */
    @Override
    public boolean check(String value) {
        // if start with HEAD, pass
        return value.charAt(0) == head;
    }

    /**
     * <pre> this method will not check value, so you should {@link #check(String)} first.
     * <pre> However, not checking will not necessarily report an error, but may result in non-compliance.
     * @param value a String value
     * @param line line number
     * @return {@link IniSection}, can not be null.
     */
    @Override
    public IniSection format(String value, int line) {
        // foreach this value, and find the head and end.
        // remember the index for comment (if exists).
        int indexOfEnd = value.indexOf(end);
        if(indexOfEnd <= 0){
            // can not found end.
            throw new IniFormatException("can not found the end character '"+ end +"' for section line "+ line +" : " + value);
        }
//        int indexOutOfHead = value.indexOf(head, 1);
//        if(indexOutOfHead < indexOfEnd){
            // more head.
            // maybe.. do nothing?
//        }

        // section value
        String sectionValue = value.substring(0, indexOfEnd+1).trim();
        String endOfValue = value.substring(indexOfEnd+1).trim();
        IniComment comment = null;
        if(endOfValue.length() > 0){
            // check, if not, throw an exception.
            CommentElementFormatter commentElementFormatter = getCommentElementFormatter();
            if(commentElementFormatter.check(endOfValue)){
                comment = commentElementFormatter.format(endOfValue, line);
            }else{
                throw new IniFormatException("can not format the value end of section value ("+ line +":"+ (indexOfEnd+1) +") :" + endOfValue);
            }
        }

        return new IniSectionImpl(sectionValue.substring(1, indexOfEnd), sectionValue, line, comment);
    }
}
