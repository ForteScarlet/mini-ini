package love.forte.minini;

import love.forte.minini.element.IniElement;
import love.forte.minini.element.IniProperty;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * <pre> Ini data.
 * <pre> extends {@code ArrayList<IniElement> }
 * <pre> if you want to add a empty line to this ini, just add null.
 * @author ForteScralet
 * @since 1.0
 */
public class Ini extends ArrayList<IniElement> /*implements Map<String, IniProperty>*/ {

    //**************** constructors from arrayList ****************//


    public Ini() {
    }

    /**
     * constructor
     * @param initialCapacity param from {@link ArrayList#ArrayList(int)}
     */
    public Ini(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * constructor
     * @param c param from {@link ArrayList#ArrayList(Collection)}
     */
    public Ini(Collection<? extends IniElement> c) {
        super(c);
    }

    //**************** some methods ****************//

    /**
     * toString to show ini data
     * @return ini data string
     */
    @Override
    public String toString(){
        if(this.isEmpty()){
            // if data empty, return empty.
            return "";
        }else{
            // Imitation of super toString method.
            // Imitation ? maybe not ?
            String newLineSplit = getNewLineSplit();
            // use joiner for every line
            StringJoiner joiner = new StringJoiner(newLineSplit);
            for (IniElement iniElement : this) {
                // if null, show a empty line.
                joiner.add(iniElement == null ? "" : iniElement.toString());
            }
            return joiner.toString();
        }
    }

    /**
     * get new line split
     */
    private String getNewLineSplit(){
        return System.getProperty("line.separator", "\n");
    }

    /**
     * <p> get properties.</p>
     * <p>for example: </p>
     * <p>
     *     <code>
     *          [se1] # section named 'se1'
     *          # key 1
     *          key1=value1
     *          # key 2
     *          key1=value2
     *          [se2]
     *     </code>
     * </p>
     * <p>will be</p>
     * <p>
     *     <code>
     *          se1${delimiter}value1=value1
     *          se1${delimiter}value2=value2
     *     </code>
     * </p>
     * <p>Suppose delimiter is'.'</p>
     * <p>
     *     <code>
     *          se1.value1=value1
     *          se1.value2=value2
     *     </code>
     * </p>
     * @param delimiter Connect the property value to the section value. if null, ignore section.
     * @return properties
     */
    public Properties toProperties(String delimiter){
        final Properties prop = new Properties();
        final Iterator<IniElement> iter = iterator();
        IniElement next;
        while (iter.hasNext()){
            next = iter.next();
            if(next.isProperty()){
                String pk;
                IniProperty inip = (IniProperty)next;
                if(delimiter != null){
                    pk = inip.getSection().value() + delimiter + inip.key();
                }else{
                    pk = inip.key();
                }
                prop.setProperty(pk ,next.value());
            }
        }
        return prop;
    }

    /**
     * to properties. delimiter is '.'
     * @see #toProperties(String)
     * @return properties
     */
    public Properties toProperties(){
        return toProperties(".");
    }


    //**************** method for write ****************//


    /**
     * write the {@link #toString()} value to output stream.
     * @param out output stream.
     * @param charset param for {@link String#getBytes(Charset)}
     * @param withComment write with comment
     * @throws IOException io exception from {@link OutputStream#write(byte[])}
     */
    public void write(OutputStream out, Charset charset, boolean withComment) throws IOException {
        String str;
        for (IniElement element : this) {
            if(!withComment && element.isComment()){
                continue;
            }
            str = element == null ? getNewLineSplit() :
                    withComment ? element.toString() + getNewLineSplit() : element.toNoCommentString() + getNewLineSplit();
            out.write(str.getBytes(charset));
        }
        out.flush();
    }

    /**
     * write the {@link #toString()} value to output stream.
     * charset is utf-8
     * @see #write(OutputStream, Charset, boolean)
     * @param out output stream.
     * @param withComment write with comment
     * @throws IOException io exception from {@link OutputStream#write(byte[])}
     */
    public void write(OutputStream out, boolean withComment) throws IOException {
        write(out, StandardCharsets.UTF_8, withComment);
    }


    /**
     * write the {@link #toString()} value to Writer.
     * @param writer Writer
     *               @throws IOException io exception from {@link Writer#write(String)}
     * @param withComment write with comment
     */
    public void write(Writer writer, boolean withComment) throws IOException {
        String str;
        for (IniElement element : this) {
            if(!withComment && element.isComment()){
                continue;
            }
            str = element == null ? getNewLineSplit() :
                    withComment ? element.toString() + getNewLineSplit() : element.toNoCommentString() + getNewLineSplit();
            writer.write(str);
        }
        writer.flush();
    }

    /**
     * write the {@link #toString()} value to PrintStream.
     * @param print PrintStream
     * @param withComment write with comment
     */
    public void write(PrintStream print, boolean withComment) {
        String str;
        for (IniElement element : this) {
            if(!withComment && element.isComment()){
                continue;
            }
            str = element == null ? "" : withComment ? element.toString() : element.toNoCommentString();
            print.println(str);
        }
        print.flush();
    }


    /**
     * write the {@link #toString()} value to File.
     * @param file    file
     * @param charset charset
     * @param withComment write with comment
     * @throws IOException io exception
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void write(File file, Charset charset, boolean withComment) throws IOException {
        if(!file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            write(out, charset, withComment);
        }
    }

    /**
     * write the {@link #toString()} value to File.
     * charset is utf-8
     * @param file    file
     * @param withComment write with comment
     * @throws IOException io exception
     */
    public void write(File file, boolean withComment) throws IOException {
        write(file, StandardCharsets.UTF_8, withComment);
    }

    /**
     * write the {@link #toString()} value to Path(file).
     * @param path    path
     * @param charset charset
     * @param withComment write with comment
     * @throws IOException io exception
     */
    public void write(Path path, Charset charset, boolean withComment) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        try (BufferedOutputStream out = new BufferedOutputStream(Files.newOutputStream(path))) {
            write(out, charset, withComment);
        }
    }


    /**
     * write the {@link #toString()} value to Path(file).
     * charset is utf-8
     * @param path    path
     * @param withComment write with comment
     * @throws IOException io exception
     */
    public void write(Path path, boolean withComment) throws IOException {
        write(path, StandardCharsets.UTF_8, withComment);
    }

}
