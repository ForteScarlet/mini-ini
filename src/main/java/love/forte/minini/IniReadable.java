package love.forte.minini;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Path;

/**
 * reader for read ini input
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public interface IniReadable {


    /**
     * read ini data from Reader
     * @param reader ini data reader
     * @return {@link Ini} bean
     * @throws IOException io exception
     * @throws IniFormatException format exception
     */
    Ini read(Reader reader) throws IOException;

    /**
     * read ini data from an inputStream
     * @see #read(Reader)
     * @param in an ini data inputStream
     * @return {@link Ini} bean
     * @throws IOException io exception
     * @throws IniFormatException format exception
     */
    Ini read(InputStream in) throws IOException ;

    /**
     *
     * read ini file to bean
     *
     * @see #read(Reader)
     * @param file ini file
     * @return {@link Ini} bean
     * @throws IOException io exception
     * @throws IniFormatException format exception
     */
    Ini read(File file) throws IOException ;

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
    Ini read(Path path) throws IOException;
}
