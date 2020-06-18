package love.forte.minini.element;

/**
 * Ini file's comment.
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class IniComment extends BaseElement {

    /** Ini file comment's head . maybe ';' or '#', default is '#'   */
    public static final char HEAD = '#';
    public static final char[] HEADS = {';', '#'};


    /** constructor */
    public IniComment(String value, String originalValue, int lineNumber){
        super(value, originalValue, lineNumber);
    }

    /** constructor */
    public IniComment(String originalValue, int lineNumber){
        super(originalValue.substring(1), originalValue, lineNumber);
    }

    /**
     * If the value changed, change the originalValue
     *
     * @param newValue when value changes, like {@link #setValue(String)} or {@link #setValue(java.util.function.Function)}
     */
    @Override
    protected String valueChanged(String newValue) {
        return "# " + trim(newValue);
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
        return null;
    }

    /**
     * clear comment (if exists).
     */
    @Override
    public void clearComment() { }

    /**
     * like {@link #toString()}, without comment value(if exists).
     *
     * @return to string value without comment value.
     */
    @Override
    public String toNoCommentString() {
        return getOriginalValue();
    }

}
