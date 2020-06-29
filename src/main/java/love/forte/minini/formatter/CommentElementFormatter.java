/*
 * Copyright (c) 2020. ForteScarlet All rights reserved.
 * Project  mini-ini
 * File     CommentElementFormatter.java
 *
 * You can contact the author through the following channels:
 * github https://github.com/ForteScarlet
 * gitee  https://gitee.com/ForteScarlet
 * email  ForteScarlet@163.com
 *
 */

package love.forte.minini.formatter;

import love.forte.minini.element.IniComment;
import love.forte.minini.element.IniCommentImpl;

/**
 *
 * an element formatter for comment
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class CommentElementFormatter extends BaseElementFormatter<IniComment> {

    /** comment start char, {@link IniComment#HEAD} */
    private char start;

    public CommentElementFormatter(char startChar){
        super(null);
        start = startChar;
    }
    public CommentElementFormatter(){
        super(null);
        start = IniComment.HEAD;
    }

    /**
     * format a String value as {@link IniComment}.
     * @param value a String value
     * @return {@link IniComment}
     */
    @Override
    public IniComment format(String value, int line) {
        return new IniCommentImpl(value.substring(1), value, line);
    }

    /**
     * check value if can
     * @param value a String value
     */
    @Override
    public boolean check(String value){
        // if start with '#'
        return value.charAt(0) == start;
    }

}
