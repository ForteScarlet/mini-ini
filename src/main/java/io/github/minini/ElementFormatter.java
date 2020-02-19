package io.github.minini;

import io.github.minini.element.IniComment;
import io.github.minini.element.IniElement;
import io.github.minini.formatter.CommentElementFormatter;

/**
 * <pre> elementFormatter, format a CharSequence value as {@link IniElement}.
 * <pre> Other than CommentElementFormatter, All element formatters should additionally require a comment element formatter, or extends comment element formatter.
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public abstract class ElementFormatter<E extends IniElement> implements ElementFormattable<E> {

    /**
     * Other than CommentElementFormatter, All element formatters should additionally require a comment element formatter, or extends comment element formatter.
     */
    private CommentElementFormatter commentElementFormatter;

    /**
     * constructor, need a comment element formatter.
     * @param commentElementFormatter {@link CommentElementFormatter}
     */
    public ElementFormatter(CommentElementFormatter commentElementFormatter){
        this.commentElementFormatter = commentElementFormatter;
    }

    /** {@link #commentElementFormatter}'s getter */
    protected CommentElementFormatter getCommentElementFormatter(){
        return this.commentElementFormatter;
    }
    /** {@link #commentElementFormatter}'s setter */
    protected void setCommentElementFormatter(CommentElementFormatter commentElementFormatter){
        this.commentElementFormatter = commentElementFormatter;
    }


    /**
     * check this value.
     * @param value value
     * @return true if can.
     */
    public abstract boolean check(CharSequence value);

    /**
     * <pre> this method will not check value, so you should {@link #check(CharSequence)} first.
     * <pre> However, not checking will not necessarily report an error, but may result in non-compliance.
     * @param value a CharSequence value
     * @param line line number
     * @return {@link E}
     */
    @Override
    public abstract E format(CharSequence value, int line);

}
