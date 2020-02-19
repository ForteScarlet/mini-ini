package io.github.minini.element;

/**
 * Ini file's comment.
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class IniComment extends BaseElement {

    /** Ini file comment's head .   */
    public static final char HEAD = ';';


    /** constructor */
    public IniComment(CharSequence value, CharSequence originalValue, int lineNumber){
        super(value, originalValue, lineNumber);
    }

    /** constructor */
    public IniComment(CharSequence originalValue, int lineNumber){
        super(trim(originalValue.subSequence(0, originalValue.length())), originalValue, lineNumber);
    }

    /**
     * If the value changed, change the originalValue
     *
     * @param newValue when value changes, like {@link #setValue(CharSequence)} or {@link #setValue(java.util.function.Function)}
     */
    @Override
    protected CharSequence valueChanged(CharSequence newValue) {
        return "; " + trim(newValue);
    }


    /**
     * <pre> there may be comments at the end of each element.
     * <pre> or null.
     * <pre> if this element is comment, return itself.
     * <pre> so, nullable, or see {@link #getCommentOptional}.
     * @see #getCommentOptional()
     * @return comment end of the element or null. if element, return itself.
     */
    @Override
    public IniComment getComment() {
        return this;
    }

}
