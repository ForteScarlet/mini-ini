package io.github.minini.formatter;

import io.github.minini.ElementFormatter;
import io.github.minini.element.IniComment;

/**
 *
 * an element formatter for comment
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class CommentElementFormatter extends ElementFormatter<IniComment> {

    /** comment start char, {@link IniComment#HEAD} */
    private final char START;

    public CommentElementFormatter(char startChar){
        super(null);
        START = startChar;
    }
    public CommentElementFormatter(){
        super(null);
        START = IniComment.HEAD;
    }

    /**
     * format a CharSequence value as {@link IniComment}.
     * @param value a CharSequence value
     * @return {@link IniComment}
     */
    @Override
    public IniComment format(CharSequence value, int line) {
        return new IniComment(value.subSequence(0, value.length()), value, line);
    }

    /**
     * check value if can
     * @param value a charSequence value
     */
    @Override
    public boolean check(CharSequence value){
        // if start with ';', can
        return value.charAt(0) == START;
    }

}
