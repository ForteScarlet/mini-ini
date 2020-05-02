package io.github.minini;

import io.github.minini.element.IniElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringJoiner;

/**
 *
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
    public Ini(int initialCapacity) {
        super(initialCapacity);
    }
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
            String newLineSplit = System.getProperty("line.separator", "\r\n");
            // use joiner for every line
            StringJoiner joiner = new StringJoiner(newLineSplit);
            for (IniElement iniElement : this) {
                // if null, show a empty line.
                joiner.add(iniElement == null ? "" : iniElement.toString());
            }
            return joiner.toString();
        }
    }




}
