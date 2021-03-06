/*
 * Copyright (c) 2020. ForteScarlet All rights reserved.
 * Project  mini-ini
 * File     IniComment.java
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

/**
 * Ini file's comment.
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public interface IniComment extends IniElement {

    /** Ini file comment's head . maybe ';' or '#', default is '#'   */
    char HEAD = '#';
    char[] HEADS = {';', '#'};

    /**
     * <pre> there may be comments at the end of each element.
     * <pre> or null.
     * <pre> if this element is comment, return itself.
     * <pre> so, nullable, or see {@link #getCommentOptional}.
     * @see #getCommentOptional()
     * @return comment end of the element or null. if element, return itself.
     */
    @Override
    default IniComment getComment() {
        return null;
    }

    /**
     * clear comment (if exists).
     */
    @Override
    default void clearComment() { }

    /**
     * like {@link #toString()}, without comment value(if exists).
     * comment to no comment string? no, return original value.
     * @return to string value without comment value.
     */
    @Override
    default String toNoCommentString() {
        return getOriginalValue();
    }

}
