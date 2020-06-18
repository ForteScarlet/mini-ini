package love.forte.minini.reader;

import love.forte.minini.Ini;
import love.forte.minini.IniFormatterFactory;
import love.forte.minini.IniReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * <p> default buffered reader for IniReader.
 * <p> {@link #read(Reader)} will use buffered reader
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class BufferedIniReader extends IniReader {

    public BufferedIniReader() {
    }

    public BufferedIniReader(IniFormatterFactory formatterFactory) {
        super(formatterFactory);
    }

    /**
     * to buffered and read
     * @param reader ini data reader
     * @return
     */
    @Override
    public Ini read(Reader reader) throws IOException {
        BufferedReader bufReader;
        if(reader instanceof BufferedReader){
            bufReader = (BufferedReader) reader;
        }else{
            bufReader = new BufferedReader(reader);
        }
        return bufferedRead(bufReader);
    }

    /**
     * read buffered reader and parse to ini.
     * @param reader BufferedReader
     * @return Ini
     * @throws IOException io exception
     */
    private Ini bufferedRead(BufferedReader reader) throws IOException {
        return defaultFormat(reader);
    }


}
