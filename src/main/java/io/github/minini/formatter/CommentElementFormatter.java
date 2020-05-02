package io.github.minini.formatter;

import io.github.minini.element.IniComment;

/**
 *
 * an element formatter for comment
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class CommentElementFormatter extends ElementFormatter<IniComment> {

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
        return new IniComment(value.substring(1), value, line);
    }

    /**
     * check value if can
     * @param value a String value
     */
    @Override
    public boolean check(String value){
        // if start with ';', can
        return value.charAt(0) == start;
    }

}
