package love.forte.minini.element;

import java.util.Optional;
import java.util.function.Function;

/**
 * <pre> IniElement, like {@code sections, properties, comments}.
 * <pre> they all can be like {@link String} .
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
    String value();

    /**
     * change this element's value.
     * @see #value()
     * @param newValue a new value
     * @return old value
     */
    String setValue(String newValue);

    /**
     * <pre> there may be comments at the end of each element.
     * <pre> or null.
     * <pre> if this element is comment, return null.
     * <pre> so, nullable, or see {@link #getCommentOptional}.
     * @see #getCommentOptional()
     * @return comment end of the element or null. if element, return null.
     */
    IniComment getComment();

    /**
     * clear comment (if exists).
     */
    void clearComment();

    /**
     * like {@link #toString()}, without comment value(if exists).
     * @return to string value without comment value.
     */
    String toNoCommentString();

    /**
     * <pre> Get complete information.
     * <pre> Take sec as an example：{@code section.toString() + all properties.toString() + comment.toString()}
     * <pre> In general, it is about the same as {@link #toString()}.
     * @return
     */
    String toCompleteString();

    /**
     * need to override toString method, to show complete information.
     * @return to string value.
     */
    @Override
    public String toString();


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
    default String setValue(Function<String, String> valueEditor){
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

}
