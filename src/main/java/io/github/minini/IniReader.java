package io.github.minini;

import io.github.minini.element.IniComment;
import io.github.minini.element.IniElement;
import io.github.minini.element.IniProperty;
import io.github.minini.element.IniSection;
import io.github.minini.formatter.CommentElementFormatter;
import io.github.minini.formatter.DefaultIniFormatter;
import io.github.minini.formatter.ElementFormatter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public IniReader(){
        SECTIONS_HEAD = IniSection.HEAD;
        SECTIONS_END = IniSection.END  ;
        PARAMETER_SPLIT = IniProperty.P_V_SPLIT;
        COMMENT_HEAD = IniComment.HEAD;
        formatterFactory = DefaultIniFormatter::new;
    }

    /**
     * get a default formatter by factory
     * @return {@link IniFormatter}
     */
    protected IniFormatter getFormatter(){

        return formatterFactory.apply(
                new CommentElementFormatter(),
                null,
                null
        );
    }

    /** get a formatter by factory
     * @return {@link IniFormatter}
     */
    protected IniFormatter getFormatter(ElementFormatter<IniComment> commentElementFormatter,
                                        ElementFormatter<IniSection> sectionElementFormatter,
                                        ElementFormatter<IniProperty> propertyElementFormatter){
        return formatterFactory.apply(commentElementFormatter, sectionElementFormatter, propertyElementFormatter);
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
        String newLineSplit = System.getProperty("line.separator", "\r\n");
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


}
