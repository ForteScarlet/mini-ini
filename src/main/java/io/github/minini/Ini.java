package io.github.minini;

import io.github.minini.element.IniElement;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * <pre> Ini data.
 * <pre> extends {@code ArrayList<IniElement> }
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





}
