package love.forte.minini.annotation;

import java.lang.annotation.*;

/**
 * An identifier that can be null.
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface Nullable { }
