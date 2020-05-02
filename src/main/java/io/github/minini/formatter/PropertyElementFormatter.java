package io.github.minini.formatter;


import io.github.minini.element.IniProperty;

/**
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class PropertyElementFormatter extends ElementFormatter<IniProperty> {

    /** key value split character, {@link IniProperty#P_V_SPLIT} */
    private char split;

    /**
     * constructor, need a comment element formatter.
     * @param commentElementFormatter {@link CommentElementFormatter}
     */
    public PropertyElementFormatter(CommentElementFormatter commentElementFormatter){
        super(commentElementFormatter);
        this.split = IniProperty.P_V_SPLIT;
    }
    /** constructor, the comment element formatter used new {@link CommentElementFormatter} */
    public PropertyElementFormatter(){
        this.split = IniProperty.P_V_SPLIT;
    }
    /**
     * constructor, need a comment element formatter.
     * @param commentElementFormatter {@link CommentElementFormatter}
     */
    public PropertyElementFormatter(char split, CommentElementFormatter commentElementFormatter){
        super(commentElementFormatter);
        this.split = split;
    }
    /** constructor, the comment element formatter used new {@link CommentElementFormatter} */
    public PropertyElementFormatter(char split){
        this.split = split;
    }


    /**
     * check this value.
     * if contains '=' and this character is not the first, return true
     * @param value value
     * @return true if can.
     */
    @Override
    public boolean check(String value) {
        return value.indexOf(split) > 0;
    }

    /**
     * <pre> this method will not check value, so you should {@link #check(String)} first.
     * <pre> However, not checking will not necessarily report an error, but may result in non-compliance.
     * @param value a String value
     * @param line line number
     * @return {@link IniProperty}, can not be null.
     */
    @Override
    public IniProperty format(String value, int line) {
        // TODO
        return null;
    }
}
