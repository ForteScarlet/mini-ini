package io.github.minini.formatter;

import io.github.minini.*;
import io.github.minini.element.IniComment;
import io.github.minini.element.IniElement;
import io.github.minini.element.IniProperty;
import io.github.minini.element.IniSection;

import java.util.Objects;

/**
 *
 * a default ini line formatter
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class DefaultIniFormatter implements IniFormatter {

    /** ini sections head, default : {@link IniSection#HEAD} */
    protected final char SECTIONS_HEAD;
    /** ini sections end,  default : {@link IniSection#END} */
    protected final char SECTIONS_END;
    /** ini parameter's split, default : {@link IniProperty#P_V_SPLIT} */
    protected final char PARAMETER_SPLIT;
    /** ini comment head, default : {@link IniComment#HEAD} */
    protected final char COMMENT_HEAD;
    /** last section */
    protected IniSection lastSection;

    private int lineNumber = 0;

    public DefaultIniFormatter(char sectionsHead, char sectionsEnd, char parameterSplit, char commentHead){
        this.SECTIONS_HEAD = sectionsHead;
        this.SECTIONS_END = sectionsEnd;
        this.PARAMETER_SPLIT = parameterSplit;
        this.COMMENT_HEAD = commentHead;
    }

    /**
     * <pre> format line as element.
     * <pre> if empty line, return null.
     * @param raw line data
     * @return {@link IniElement}
     */
    @Override
    public synchronized IniElement formatLine(String raw) {
        Objects.requireNonNull(raw);
        // line number + 1
        lineNumber++;
        String line = raw.trim();
        // if empty line, return null
        if(line.length() == 0){
            return null;
        }
        checkLine(line, raw, lineNumber);

        // format line.
        // comment?
        if(line.charAt(0) == COMMENT_HEAD){

        }else
            // section?
            if(line.charAt(0) == SECTIONS_HEAD){

        }else
            // parameters ?
            if(line.charAt(0) == SECTIONS_HEAD){

            }else{
                // None of them
                throw new IniFormatException("No matching element type found for: '"+ line +'\'');
            }

        return null;
    }

    /**
     * <pre> check line.
     * <pre> If it does not meet the specifications, throw IniFormatException.
     * @param line line data, should: {@code line = raw.trim()}
     * @param raw {@link #formatLine(String)}'s param: {@code raw}
     * @throws io.github.minini.IniFormatException
     * <pre> If it does not meet the specifications:
     * line does not start with {@code '['}, any word({@code 'a-z,A-Z,0-9'}), Unless it only comment value.
     */
    public void checkLine(String line, String raw, int lineNumber){
        // line will not be null
        if(line.charAt(0) == COMMENT_HEAD){
            // only comment
            return;
        }

        // head is '['
        char head = raw.charAt(0);
        if(head == SECTIONS_HEAD){
            return;
        }

        // head is a-z A-Z 0-9
        char l = 'a';
        char r = 'z';
        if(l <= head && r >= head){
            return;
        }

        l = 'A';
        r = 'Z';
        if(l <= head && r >= head){
            return;
        }

        l = '0';
        r = '9';
        if(l <= head && r >= head){
            return;
        }

        raw = raw.replace(NEW_LINE_SEPARATOR, "");
        // no return, throw exception
        throw new IniFormatException("Illegal line: " + lineNumber + ", this line must only comment(start by '"+ COMMENT_HEAD +"') or start with: '[',a-zA-Z0-9, but: '" + raw + '\'');
    }

    /**
     * Back to the initial state
     */
    @Override
    public synchronized void init() {
        lineNumber = 0;
        lastSection = null;
    }
}
