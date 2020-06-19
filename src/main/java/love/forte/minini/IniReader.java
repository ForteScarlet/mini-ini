package love.forte.minini;

import love.forte.minini.element.IniComment;
import love.forte.minini.element.IniElement;
import love.forte.minini.element.IniProperty;
import love.forte.minini.element.IniSection;
import love.forte.minini.formatter.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 *
 * <p> an abstract class for read ini.
 * <p> remember to close io stream !
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public abstract class IniReader implements IniReadable {

    /** ini sections head, default : {@link IniSection#HEAD} */
    protected final char SECTIONS_HEAD;
    /** ini sections end,  default : {@link IniSection#END} */
    protected final char SECTIONS_END;
    /** ini parameter's split, default : {@link IniProperty#P_V_SPLIT} */
    protected final char PARAMETER_SPLIT;
    /** ini comment's head, default : {@link IniComment#HEAD} */
    protected final char COMMENT_HEAD;
    /** ini line data formatter factory */
    protected final IniFormatterFactory formatterFactory;


    private Supplier<ElementFormatter<IniComment>>  commentElementFormatterSupplier  = CommentElementFormatter::new;
    private Supplier<ElementFormatter<IniSection>>  sectionElementFormatterSupplier  = SectionElementFormatter::new;
    private Supplier<ElementFormatter<IniProperty>> propertyElementFormatterSupplier = PropertyElementFormatter::new;

    public IniReader(){
        SECTIONS_HEAD = IniSection.HEAD;
        SECTIONS_END = IniSection.END  ;
        PARAMETER_SPLIT = IniProperty.P_V_SPLIT;
        COMMENT_HEAD = IniComment.HEAD;
        this.formatterFactory = DefaultIniFormatter::new;
    }

    public IniReader(IniFormatterFactory formatterFactory){
        SECTIONS_HEAD = IniSection.HEAD;
        SECTIONS_END = IniSection.END  ;
        PARAMETER_SPLIT = IniProperty.P_V_SPLIT;
        COMMENT_HEAD = IniComment.HEAD;
        this.formatterFactory = formatterFactory;
    }

    /**
     * get a default formatter by factory
     * @return {@link IniFormatter}
     */
    protected IniFormatter getFormatter(){

        return formatterFactory.apply(
                commentElementFormatterSupplier.get(),
                sectionElementFormatterSupplier.get(),
                propertyElementFormatterSupplier.get()
        );
    }

    /**
     * read ini data from an inputStream
     * @see #read(Reader)
     * @param in an ini data inputStream
     * @return ini bean
     * @throws IOException io exception
     * @throws IniFormatException format exception
     */
    @Override
    public Ini read(InputStream in) throws IOException {
        return read(new InputStreamReader(in));
    }

    /**
     *
     * read ini file to bean
     *
     * @see #read(Reader)
     * @param file ini file
     * @return ini bean
     * @throws IOException io exception
     * @throws IniFormatException format exception
     */
    @Override
    public Ini read(File file) throws IOException {
        try ( Reader reader = new FileReader(file)) {
            return read(reader);
        }
    }

    /**
     *
     * read ini file to bean
     *
     * @see #read(Reader)
     * @param path ini path(file)
     * @return ini bean
     * @throws IOException io exception
     * @throws IniFormatException format exception
     */
    @Override
    public Ini read(Path path) throws IOException {
        try (Reader reader = Files.newBufferedReader(path)) {
            return read(reader);
        }
    }


    /**
     * format reader to ini bean
     * @see #defaultFormat(Reader, int)
     * @param reader reader
     * @return {@link Ini} bean
     */
    protected Ini defaultFormat(Reader reader) throws IOException {
        return defaultFormat(reader, 16);
    }

    /**
     * format reader to ini bean
     * @param reader reader
     * @param builderCapacity {@link StringBuilder} init param
     * @return {@link Ini} bean
     */
    protected Ini defaultFormat(Reader reader, int builderCapacity) throws IOException {
        IniFormatter formatter = getFormatter();
        List<IniElement> iniElements = new ArrayList<>();
        // new line split
        String newLineSplit = System.getProperty("line.separator", "\n");
        StringBuilder line = new StringBuilder(builderCapacity);

        int ch;
        while((ch = reader.read()) != -1) {
            line.append((char) ch);
            String nowStr = line.toString();
            // if new line
            if(nowStr.endsWith(newLineSplit)){
                // format and add
                IniElement element = formatter.formatLine(nowStr);
                if(element != null) {
                    iniElements.add(element);
                }
                // init stringBuilder
                line.delete(0, line.length());
            }
        }
        // the end of files, format again
        if(line.length() > 0){
            // format and add
            iniElements.add(formatter.formatLine(line.toString()));
        }

        return new Ini(iniElements);
    }

    //**************** getter & setter for formatter supplier ****************//


    public Supplier<ElementFormatter<IniComment>> getCommentElementFormatterSupplier() {
        return commentElementFormatterSupplier;
    }

    public void setCommentElementFormatterSupplier(Supplier<ElementFormatter<IniComment>> commentElementFormatterSupplier) {
        this.commentElementFormatterSupplier = commentElementFormatterSupplier;
    }

    public Supplier<ElementFormatter<IniSection>> getSectionElementFormatterSupplier() {
        return sectionElementFormatterSupplier;
    }

    public void setSectionElementFormatterSupplier(Supplier<ElementFormatter<IniSection>> sectionElementFormatterSupplier) {
        this.sectionElementFormatterSupplier = sectionElementFormatterSupplier;
    }

    public Supplier<ElementFormatter<IniProperty>> getPropertyElementFormatterSupplier() {
        return propertyElementFormatterSupplier;
    }

    public void setPropertyElementFormatterSupplier(Supplier<ElementFormatter<IniProperty>> propertyElementFormatterSupplier) {
        this.propertyElementFormatterSupplier = propertyElementFormatterSupplier;
    }


}
