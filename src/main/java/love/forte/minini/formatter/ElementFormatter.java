package love.forte.minini.formatter;

import love.forte.minini.ElementFormattable;
import love.forte.minini.element.IniElement;

/**
 * <pre> elementFormatter, format a String value as {@link IniElement}.
 * <pre> Other than CommentElementFormatter, All element formatters should additionally require a comment element formatter
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public abstract class ElementFormatter<E extends IniElement> implements ElementFormattable<E> {

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
    public ElementFormatter(CommentElementFormatter commentElementFormatter){
        this.commentElementFormatter = commentElementFormatter;
    }
    /** constructor, the comment element formatter used new {@link CommentElementFormatter} */
    public ElementFormatter(){
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


    /**
     * check this value.
     * @param value value
     * @return true if can.
     */
    public abstract boolean check(String value);

    /**
     * <pre> this method will not check value, so you should {@link #check(String)} first.
     * <pre> However, not checking will not necessarily report an error, but may result in non-compliance.
     * @param value a String value
     * @param line line number
     * @return {@link E}, can not be null.
     */
    @Override
    public abstract E format(String value, int line);

}
