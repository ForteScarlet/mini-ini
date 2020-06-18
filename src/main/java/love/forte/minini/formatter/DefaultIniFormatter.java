package love.forte.minini.formatter;

import love.forte.minini.IniFormatException;
import love.forte.minini.IniFormatter;
import love.forte.minini.element.IniComment;
import love.forte.minini.element.IniElement;
import love.forte.minini.element.IniProperty;
import love.forte.minini.element.IniSection;

import java.util.Objects;

/**
 * <pre> a default ini line formatter.
 * <pre> Requires one of three types of formatters.
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class DefaultIniFormatter implements IniFormatter {

//    /**
//     * ini sections head, default : {@link IniSection#HEAD}
//     */
//    protected final char SECTIONS_HEAD;
//    /**
//     * ini sections end,  default : {@link IniSection#END}
//     */
//    protected final char SECTIONS_END;
//    /**
//     * ini parameter's split, default : {@link IniProperty#P_V_SPLIT}
//     */
//    protected final char PARAMETER_SPLIT;
//    /**
//     * ini comment head, default : {@link IniComment#HEAD}
//     */
//    protected final char COMMENT_HEAD;

    protected final ElementFormatter<IniComment> commentElementFormatter;
    protected final ElementFormatter<IniSection> sectionElementFormatter;
    protected final ElementFormatter<IniProperty> propertyElementFormatter;

    /**
     * last section
     */
    protected IniSection lastSection;

    /**
     * line number of read
     */
    private int lineNumber = 0;
    /**
     * line number of effective. empty line will not added.
     */
    private int effectiveLineNumber = 0;


//    public DefaultIniFormatter(char sectionsHead, char sectionsEnd, char parameterSplit, char commentHead) {
//        this.SECTIONS_HEAD = sectionsHead;
//        this.SECTIONS_END = sectionsEnd;
//        this.PARAMETER_SPLIT = parameterSplit;
//        this.COMMENT_HEAD = commentHead;
//
//        this.commentElementFormatter = null;
//        this.sectionElementFormatter = null;
//        this.propertyElementFormatter = null;
//    }

    public DefaultIniFormatter(ElementFormatter<IniComment> commentElementFormatter,
                               ElementFormatter<IniSection> sectionElementFormatter,
                               ElementFormatter<IniProperty> propertyElementFormatter
    ) {
        this.commentElementFormatter = commentElementFormatter;
        this.sectionElementFormatter = sectionElementFormatter;
        this.propertyElementFormatter = propertyElementFormatter;
    }

    /**
     * <pre> format line as element.
     * <pre> if empty line, return null.
     * @param raw line data
     * @return {@link IniElement}
     */
    @Override
    public IniElement formatLine(String raw) {
        Objects.requireNonNull(raw);
        // line number + 1
        lineNumber++;
        String line = raw.trim();
        // if empty line, return null
        if (line.length() == 0) {
            return null;
        }

        // format line.
        IniElement element;
        // pre effective line number, preEff = eff + 1
        int preEffectiveLineNumber = effectiveLineNumber + 1;

        // comment?
        if (commentElementFormatter.check(line)) {
            element = commentElementFormatter.format(line, preEffectiveLineNumber);
        } else
            // section?
            if (sectionElementFormatter.check(line)) {
                IniSection section = sectionElementFormatter.format(line, preEffectiveLineNumber);
                // save last section
                lastSection = section;
                element = section;
        } else
            // property ?
            if (propertyElementFormatter.check(line)) {
                IniProperty property = propertyElementFormatter.format(line, preEffectiveLineNumber);
                // set section if exists
                // In general it should be there, unless it's incorrectly formatted. If not, an exception is thrown.
                if (lastSection == null) {
                    throw new IniFormatException("Cannot found section for property line " + lineNumber + " : " + line);
                }
                // set section for property
                property.setSection(lastSection);
                lastSection.add(property);
                element = property;
        } else {
            // None of them
            throw new IniFormatException("No matching element type found for line " + lineNumber + " : " + line);
        }

        // if no throw, update effective line number.
        effectiveLineNumber = preEffectiveLineNumber;
        return element;
    }

    /**
     * Back to the initial state
     */
    @Override
    public synchronized void init() {
        lineNumber = 0;
        effectiveLineNumber = 0;
        lastSection = null;
    }
}
