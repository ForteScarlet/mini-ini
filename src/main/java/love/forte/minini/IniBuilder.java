package love.forte.minini;

import com.sun.istack.internal.Nullable;
import love.forte.minini.element.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * A builder create an {@link Ini} examples.
 * Thread unsafe.
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class IniBuilder {

    /** elements */
    private List<IniElement> elements;

    /** wait for first sections */
    private LinkedList<Supplier<IniProperty>> waitForSections = new LinkedList<>();

    /** last section. */
    private IniSection lastSection;

    /** line number. start from 1. */
    private int line = 1;

    /** sectionCreator */
    private IniSectionCreator iniSectionCreator = IniSectionCreator.DEFAULT;

    /** commentCreator */
    private IniCommentCreator iniCommentCreator = IniCommentCreator.DEFAULT;

    /** sectionCreator */
    private IniPropertyCreator iniPropertyCreator = IniPropertyCreator.DEFAULT;


    public IniBuilder(){
        elements = new ArrayList<>();
    }
    public IniBuilder(Supplier<List<IniElement>> listSupplier){
        elements = listSupplier.get();
    }


    //**************** creator function ****************//

    /**
     * Set section creator function
     * @see IniSectionCreator
     * @param iniSectionCreator {@link IniSectionCreator}
     */
    public IniBuilder sectionCreator(IniSectionCreator iniSectionCreator){
        Objects.requireNonNull(iniSectionCreator);
        this.iniSectionCreator = iniSectionCreator;
        return this;
    }
    /**
     * Set comment creator function
     * @see IniCommentCreator
     * @param iniCommentCreator {@link IniCommentCreator}
     */
    public IniBuilder commentCreator(IniCommentCreator iniCommentCreator){
        Objects.requireNonNull(iniCommentCreator);
        this.iniCommentCreator = iniCommentCreator;
        return this;
    }
    /**
     * Set property creator function
     * @see IniPropertyCreator
     * @param iniPropertyCreator {@link IniPropertyCreator}
     */
    public IniBuilder sectionCreator(IniPropertyCreator iniPropertyCreator){
        Objects.requireNonNull(iniPropertyCreator);
        this.iniPropertyCreator = iniPropertyCreator;
        return this;
    }

    //**************** line ****************//

    /**
     * Skip lines. add null value to line.
     * @param length skip lines
     */
    public IniBuilder skipLine(int length){
        for (int i = 0; i < length; i++) {
            elements.add(null);
            line++;
        }
        return this;
    }

    //**************** other builder ****************//

    /**
     * Plus other builder
     * @param otherBuilder other builder
     */
    public IniBuilder plus(IniBuilder otherBuilder){
        this.elements.addAll(otherBuilder.elements);
        this.line += otherBuilder.line-1;
        return this;
    }

    /**
     * Plus iniElement list
     * @param elements IniElement list
     */
    public IniBuilder plus(List<IniElement> elements){
        this.elements.addAll(elements);
        this.line += elements.size();
        return this;
    }


    //**************** add section ****************//

    /**
     * Plus a section
     * @param value section value
     */
    public IniBuilder plusSection(String value){
        final IniSection section = iniSectionCreator.create(value, line++, null);
        elements.add(section);
        this.lastSection = section;
        checkProps();
        return this;
    }

    /**
     * Plus a section with comment
     * @param value   section value
     * @param comment comment
     */
    public IniBuilder plusSection(String value, IniComment comment){
        final IniSection section = iniSectionCreator.create(value, line++, comment);
        elements.add(section);
        this.lastSection = section;
        checkProps();
        return this;
    }

    /**
     * Plus a section with comment
     * @param value   section value
     * @param commentValue comment value
     */
    public IniBuilder plusSection(String value, String commentValue){
        final int lineNumber = line++;
        final IniComment comment = iniCommentCreator.create(commentValue, lineNumber);
        final IniSection section = iniSectionCreator.create(value, lineNumber, comment);
        elements.add(section);
        this.lastSection = section;
        checkProps();
        return this;
    }


    private void checkProps(){
        // not empty
        if(this.lastSection != null && !waitForSections.isEmpty()){
            while(!waitForSections.isEmpty()){
                final IniProperty property = waitForSections.removeLast().get();
                property.setSection(this.lastSection);
                this.lastSection.add(property);
                elements.add(property);
            }
        }
    }

    private void checkProps(Supplier<IniProperty> propertySupplier){
        // not empty
        if(this.lastSection == null){
            this.waitForSections.addFirst(propertySupplier);
        }else{
            checkProps();
            final IniProperty property = propertySupplier.get();
            property.setSection(this.lastSection);
            this.lastSection.add(property);
            elements.add(property);
        }
    }

    //**************** add property ****************//

    /**
     * Plus a property
     * @param key    key
     * @param value  value
     */
    public IniBuilder plusProperty(String key, String value){
        checkProps(() -> iniPropertyCreator.create(key, value, line++, null));
        return this;
    }

    /**
     * Plus a property
     * @param key    key
     * @param value  value
     */
    public IniBuilder plusProperty(String key, String value, IniComment comment){
        checkProps(() -> iniPropertyCreator.create(key, value, line++, comment));
        return this;
    }

    /**
     * Plus a property
     * @param key    key
     * @param value  value
     */
    public IniBuilder plusProperty(String key, String value, String commentValue){
        checkProps(() -> {
            final int lineNumber = line++;
            IniComment comment = iniCommentCreator.create(commentValue, lineNumber);
            return iniPropertyCreator.create(key, value, lineNumber, comment);
        });
        return this;
    }


    //**************** add comment ****************//

    public IniBuilder plusComment(String value){
        final IniComment comment = iniCommentCreator.create(value, line++);
        elements.add(comment);
        return this;
    }



    //**************** end & build ****************//

    public Ini build(){
        return new Ini(elements);
    }


    //**************** create function interface ****************//

    /**
     * section create function
     */
    @FunctionalInterface
    public interface IniSectionCreator {
        IniSectionCreator DEFAULT = IniSectionImpl::new;
        /**
         * create a section by value
         * @param value             value
         * @param line              line number
         * @param comment           comment, nullable
         * @return  {@link IniSection}
         */
        IniSection create(String value, int line, @Nullable IniComment comment);
    }

    /**
     * section create function
     */
    @FunctionalInterface
    public interface IniCommentCreator {
        IniCommentCreator DEFAULT = IniCommentImpl::byValue;
        /**
         * create a Comment by value
         * @param value             value
         * @param line              line number
         * @return  {@link IniComment}
         */
        IniComment create(String value, int line);
    }

    /**
     * property create function
     */
    @FunctionalInterface
    public interface IniPropertyCreator {
        /**
         * this default function will ignore comment.
         */
        IniPropertyCreator DEFAULT = (k, v, l, c) -> new IniPropertyImpl(k, v, l);

        /**
         * create a property by value
         * @param key       key
         * @param value     value
         * @param line      line
         * @param comment   comment, nullable
         * @return {@link IniProperty}
         */
        IniProperty create(String key, String value, int line, @Nullable IniComment comment);
    }

}
