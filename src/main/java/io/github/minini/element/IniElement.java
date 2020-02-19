package io.github.minini.element;

import java.util.Optional;
import java.util.function.Function;

/**
 * <pre> IniElement, like {@code sections, properties, comments}.
 * <pre> they all can be like {@link CharSequence} .
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public interface IniElement extends CharSequence {

    /**
     * <p>this element's value.
     * maybe a {@code toString} value like {@code comment},
     * a property's value like {@code property} or a title value like {@code section} .
     * @return some value
     */
    CharSequence value();

    /**
     * {@code value().toString() }
     * @return {@link #value()}'s String value.
     */
    default String valueString(){
        return value().toString();
    }

    /**
     * change this element's value.
     * @see #value()
     * @param newValue a new value
     * @return old value
     */
    CharSequence setValue(CharSequence newValue);

    /**
     * <pre> there may be comments at the end of each element.
     * <pre> or null.
     * <pre> if this element is comment, return itself.
     * <pre> so, nullable, or see {@link #getCommentOptional}.
     * @see #getCommentOptional()
     * @return comment end of the element or null. if element, return itself.
     */
    IniComment getComment();

    /**
     * the line number where you are.
     * @return line number.
     */
    int line();

    /**
     * <pre> there may be comments at the end of each element.
     * <pre> if this element is comment, return itself.
     * @see #getComment()
     * @return comment end of the element. if element, return itself.
     */
    default Optional<IniComment> getCommentOptional(){
        return Optional.ofNullable(getComment());
    }

    /**
     * Edit the value of this element on the basis of original value .
     * @param valueEditor function to edit old value, {@code oldValue -> {
     *     // edit ...
     *     return newValue;
     * }}
     * @return old value
     */
    default CharSequence setValue(Function<CharSequence, CharSequence> valueEditor){
        return setValue(valueEditor.apply(value()));
    }

    /**
     * Am I a comment?
     * @return is it a comment?
     */
    default boolean isComment(){
        return this instanceof IniComment;
    }
    /**
     * Am I a property?
     * @return is it a property?
     */
    default boolean isProperty(){
        return this instanceof IniProperty;
    }
    /**
     * Am I a section?
     * @return is it a section?
     */
    default boolean isSection(){
        return this instanceof IniSection;
    }

    /**
     * empty line ?
     * @return empty line ?
     */
    default boolean isEmptyLine() {
      return this instanceof IniEmptyLine;
    };

}
