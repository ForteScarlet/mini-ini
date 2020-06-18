package love.forte.minini.element;


/**
 * an abstract class for {@link IniElement}.
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public abstract class BaseElement implements IniElement {

    /**
     * value of comment text.
     */
    private String value;
    /**
     * the line number.
     */
    private int lineNumber;
    /**
     * the originalValue
     */
    private String originalValue;
    /**
     * comment, nullable
     */
    private IniComment comment;

    BaseElement(String value, String originalValue, int lineNumber) {
        this.value = value;
        this.lineNumber = lineNumber;
        this.originalValue = originalValue;
        this.comment = null;
    }

    /**
     * maybe have comment
     */
    BaseElement(String value, String originalValue, int lineNumber, IniComment comment) {
        this.value = value;
        this.lineNumber = lineNumber;
        this.originalValue = originalValue;
        this.comment = comment;
    }

    /**
     * trim a value
     *
     * @param value char sequence value
     * @return trimmed value
     */
    protected static String trim(String value) {
        return value.trim();
    }

    /**
     * If the {@code value} changed, change the originalValue
     *
     * @param newValue when {@code value} changes, like {@link #setValue(String)} or {@link #setValue(java.util.function.Function)}
     * @return new originalValue
     */
    protected abstract String valueChanged(String newValue);

    /**
     * <p>this element's value.
     * maybe a {@code toString} value like {@code comment},
     * a property's value like {@code property} or a title value like {@code section} .
     *
     * @return some value
     */
    @Override
    public String value() {
        return this.value;
    }

    /**
     * change value.
     *
     * @param newValue a new value
     */
    protected void changeValue(String newValue) {
        // default: just set value.
        value = newValue;
    }

    /**
     * change this element's value.
     * if you want to DIY how to set value, Recommended to cover {@link #changeValue(String)} instead of {@link #setValue(String)}
     *
     * @param newValue a new value
     * @return old value
     * @see #value()
     */
    @Override
    public String setValue(String newValue) {
        String old = value;
        // change value, and update OriginalValue
        changeValue(newValue);
        // trigger value-changed event
        setOriginalValue(valueChanged(newValue));
        return old;
    }


    /**
     * Default is {@code originalValue.toString()}
     *
     * @return string value
     */
    @Override
    public String toString() {
        return toCompleteString();
    }

    /**
     * get the original string.
     * @return original string value.
     */
    @Override
    public String getOriginalValue() {
        return originalValue;
    }


    protected void setOriginalValue(String newOriginalValue) {
        this.originalValue = newOriginalValue;
    }

    @Override
    public IniComment getComment() {
        return comment;
    }

    /**
     * clear comment (if exists).
     */
    @Override
    public void clearComment() {
        this.comment = null;
    }

    /**
     * like {@link #toString()}, without comment value(if exists).
     *
     * @return to string value without comment value.
     */
    @Override
    public String toNoCommentString() {
        return originalValue;
    }

    /**
     * <pre> Get complete information.
     * <pre> Take sec as an example：{@code section.toString() + all properties.toString() + comment.toString()}
     * <pre> In general, it is about the same as {@link #toString()}.
     * @return
     */
    @Override
    public String toCompleteString() {
        return comment == null ? originalValue : originalValue + " " + comment;
    }

    //**************** implements from String ****************//


    /**
     * the line number where you are.
     *
     * @return line number.
     */
    @Override
    public int line() {
        return lineNumber;
    }

    /**
     * Returns the length of this character sequence.  The length is the number
     * of 16-bit <code>char</code>s in the sequence.
     *
     * @return the number of <code>char</code>s in this sequence
     */
    @Override
    public int length() {
        return value().length();
    }

    /**
     * Returns the <code>char</code> value at the specified index.  An index ranges from zero
     * to <tt>length() - 1</tt>.  The first <code>char</code> value of the sequence is at
     * index zero, the next at index one, and so on, as for array
     * indexing.
     *
     * <p>If the <code>char</code> value specified by the index is a
     * <a href="{@docRoot}/java/lang/Character.html#unicode">surrogate</a>, the surrogate
     * value is returned.
     *
     * @param index the index of the <code>char</code> value to be returned
     * @return the specified <code>char</code> value
     * @throws IndexOutOfBoundsException if the <tt>index</tt> argument is negative or not less than
     *                                   <tt>length()</tt>
     */
    @Override
    public char charAt(int index) {
        return value().charAt(index);
    }

    /**
     * Returns a <code>CharSequence</code> that is a subsequence of this sequence.
     * The subsequence starts with the <code>char</code> value at the specified index and
     * ends with the <code>char</code> value at index <tt>end - 1</tt>.  The length
     * (in <code>char</code>s) of the
     * returned sequence is <tt>end - start</tt>, so if <tt>start == end</tt>
     * then an empty sequence is returned.
     *
     * @param start the start index, inclusive
     * @param end   the end index, exclusive
     * @return the specified subsequence
     * @throws IndexOutOfBoundsException if <tt>start</tt> or <tt>end</tt> are negative,
     *                                   if <tt>end</tt> is greater than <tt>length()</tt>,
     *                                   or if <tt>start</tt> is greater than <tt>end</tt>
     */
    @Override
    public CharSequence subSequence(int start, int end) {
        return value.subSequence(start, end);
    }
}
