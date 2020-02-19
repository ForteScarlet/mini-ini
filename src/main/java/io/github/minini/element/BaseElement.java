package io.github.minini.element;


import io.github.minini.CharSequenceUtils;

/**
 * an abstract class for {@link IniElement}.
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public abstract class BaseElement implements IniElement {

    /** value of comment text. */
    private CharSequence value;
    /** the line number. */
    private int lineNumber;
    /** the originalValue  */
    private CharSequence originalValue;

    BaseElement(CharSequence value, CharSequence originalValue, int lineNumber){
        this.value = value;
        this.lineNumber = lineNumber;
        this.originalValue = originalValue;
    }

    /**
     * trim a value
     * @param value char sequence value
     * @return trimmed value
     */
    protected static CharSequence trim(CharSequence value){
        return CharSequenceUtils.trim(value);
    }

    /**
     * If the {@link #value} changed, change the {@link #originalValue}
     * @param newValue when {@link #value} changes, like {@link #setValue(CharSequence)} or {@link #setValue(java.util.function.Function)}
     * @return new originalValue
     */
    protected abstract CharSequence valueChanged(CharSequence newValue);

    /**
     * <p>this element's value.
     * maybe a {@code toString} value like {@code comment},
     * a property's value like {@code property} or a title value like {@code section} .
     *
     * @return some value
     */
    @Override
    public CharSequence value() {
        return this.value;
    }

    /**
     * change this element's value.
     *
     * @param newValue a new value
     * @return old value
     * @see #value()
     */
    @Override
    public CharSequence setValue(CharSequence newValue) {
        CharSequence old = value;
        // change value, and update OriginalValue
        originalValue = valueChanged(value = newValue);
        return old;
    }


    /**
     * Default is {@code originalValue.toString()}
     * @return string value
     */
    @Override
    public String toString(){
        return originalValue.toString();
    }



    //**************** implements from CharSequence ****************//


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
        return value().subSequence(start, end);
    }
}
