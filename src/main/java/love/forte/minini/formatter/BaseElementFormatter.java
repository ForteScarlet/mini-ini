/*
 * Copyright (c) 2020. ForteScarlet All rights reserved.
 * Project  mini-ini
 * File     BaseElementFormatter.java
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

import love.forte.minini.element.IniElement;

/**
 * <pre> elementFormatter, format a String value as {@link IniElement}.
 * <pre> Other than CommentElementFormatter, All element formatters should additionally require a comment element formatter
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public abstract class BaseElementFormatter<E extends IniElement> implements ElementFormatter<E> {

    /**
     * Other than CommentElementFormatter,
     * All element formatters should additionally require a comment element formatter,
     * or extends comment element formatter.
     */
    private CommentElementFormatter commentElementFormatter;

    /**
     * constructor, need a comment element formatter.
     * @param commentElementFormatter {@link CommentElementFormatter}
     */
    public BaseElementFormatter(CommentElementFormatter commentElementFormatter){
        this.commentElementFormatter = commentElementFormatter;
    }
    /** constructor, the comment element formatter used new {@link CommentElementFormatter} */
    public BaseElementFormatter(){
        this.commentElementFormatter = new CommentElementFormatter();
    }



    /** {@link #commentElementFormatter}'s getter */
    protected CommentElementFormatter getCommentElementFormatter(){
        return this.commentElementFormatter;
    }

    /** {@link #commentElementFormatter}'s setter */
    protected void setCommentElementFormatter(CommentElementFormatter commentElementFormatter){
        this.commentElementFormatter = commentElementFormatter;
    }


//    /**
//     * check this value.
//     * @param value value
//     * @return true if can.
//     */
//    public abstract boolean check(String value);
//
//    /**
//     * <pre> this method will not check value, so you should {@link #check(String)} first.
//     * <pre> However, not checking will not necessarily report an error, but may result in non-compliance.
//     * @param value a String value
//     * @param line line number
//     * @return {@link E}, can not be null.
//     */
//    @Override
//    public abstract E format(String value, int line);

}
