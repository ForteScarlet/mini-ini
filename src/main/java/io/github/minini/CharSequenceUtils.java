package io.github.minini;

import java.util.PrimitiveIterator;

/**
 *
 * a simple utils for CharSequence
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class CharSequenceUtils {

    /**
     * a trim method like {@link String#trim()}
     * @param charSequence charSequence value
     * @return value trimmed
     */
    public static CharSequence trim(CharSequence charSequence){
        if(charSequence instanceof String){
            return ((String) charSequence).trim();
        }
        int len = charSequence.length();
        // charSequence to char array
        char[] value = new char[len];
        int index = 0;
        PrimitiveIterator.OfInt iter = charSequence.chars().iterator();
        while(iter.hasNext()){
            value[index++] = (char) iter.nextInt();
        }
        int st = 0;
        /* avoid getfield opcode */
        char[] val = value;
        while ((st < len) && (val[st] <= ' ')) {
            st++;
        }
        while ((st < len) && (val[len - 1] <= ' ')) {
            len--;
        }
        return ((st > 0) || (len < value.length)) ? charSequence.subSequence(st, len) : charSequence;
    }
}
