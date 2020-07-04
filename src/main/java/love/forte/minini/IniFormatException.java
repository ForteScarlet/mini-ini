/*
 * Copyright (c) 2020. ForteScarlet All rights reserved.
 * Project  mini-ini
 * File     IniFormatException.java
 *
 * This project is protected by the open source agreement Apache Licence 2.0.
 * You can find this agreement from https://github.com/ForteScarlet/mini-ini.
 *
 * You can contact the author through the following channels:
 * github https://github.com/ForteScarlet
 * gitee  https://gitee.com/ForteScarlet
 * email  ForteScarlet@163.com
 * QQ     1149159218
 *
 */

package love.forte.minini;

/**
 *
 * a format exception
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class IniFormatException extends RuntimeException {

    public IniFormatException() {
    }

    public IniFormatException(String message) {
        super(message);
    }

    public IniFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public IniFormatException(Throwable cause) {
        super(cause);
    }

    public IniFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
