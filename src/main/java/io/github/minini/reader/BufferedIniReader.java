package io.github.minini.reader;

import io.github.minini.Ini;
import io.github.minini.IniReader;

import java.io.*;

/**
 *
 * <p> default buffered reader for IniReader.
 * <p> {@link #read(Reader)} will use buffered reader
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class BufferedIniReader extends IniReader {

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


    private Ini bufferedRead(BufferedReader reader) throws IOException {


        return defaultFormat(reader);

    }


}
