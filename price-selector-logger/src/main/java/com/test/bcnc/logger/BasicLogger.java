package com.test.bcnc.logger;

import org.slf4j.Logger;

public abstract class BasicLogger {

    private static final String NEW_LINE_REGEX = "[\n\r\t]";

    protected BasicLogger() {
        super();
    }

    /**
     * Shows not critical errors. I.e. Email address not found, permissions not
     * allowed for this user, ...
     *
     * @param messageTemplate string with static text as template.
     * @param arguments       parameters to fill up the template
     */
    public static void warning(Logger logger, String messageTemplate, Object... arguments) {
        logger.warn(messageTemplate, arguments);
    }

    /**
     * Shows not critical errors. I.e. Email address not found, permissions not
     * allowed for this user, ...
     *
     * @param logger          the Logger.
     * @param className       the class to log.
     * @param messageTemplate string with static text as template.
     * @param arguments       parameters to fill up the template
     */
    public static void warning(Logger logger, String className, String messageTemplate, Object... arguments) {
        if (logger.isWarnEnabled() && messageTemplate != null) {
            for (int i = 0; i < arguments.length; i++) {
                if (arguments[i] != null) {
                    arguments[i] = arguments[i].toString().replaceAll(NEW_LINE_REGEX, "_");
                }
            }
            final String templateWithClass = (className + ": " + messageTemplate).replaceAll(NEW_LINE_REGEX, "_");
            logger.warn(templateWithClass, arguments);
        }
    }

    /**
     * Events that have business meaning (i.e. creating category, deleting form,
     * ...). To follow user actions.
     *
     * @param messageTemplate string with static text as template.
     * @param arguments       parameters to fill up the template
     */
    public static void info(Logger logger, String messageTemplate, Object... arguments) {
        if (logger.isInfoEnabled() && messageTemplate != null) {
            for (int i = 0; i < arguments.length; i++) {
                if (arguments[i] != null) {
                    arguments[i] = arguments[i].toString().replaceAll(NEW_LINE_REGEX, "_");
                }
            }
            messageTemplate = messageTemplate.replaceAll(NEW_LINE_REGEX, "_");
            logger.info(messageTemplate, arguments);
        }
    }

    /**
     * Events that have business meaning (i.e. creating category, deleting form,
     * ...). To follow user actions.
     * <p>
     *
     * @param logger          the Logger.
     * @param className       the class to log.
     * @param messageTemplate string with static text as template.
     * @param arguments       parameters to fill up the template
     */
    public static void info(Logger logger, String className, String messageTemplate, Object... arguments) {
        if (logger.isInfoEnabled() && messageTemplate != null) {
            for (int i = 0; i < arguments.length; i++) {
                if (arguments[i] != null) {
                    arguments[i] = arguments[i].toString().replaceAll(NEW_LINE_REGEX, "_");
                }
            }
            final String templateWithClass = (className + ": " + messageTemplate).replaceAll(NEW_LINE_REGEX, "_");
            logger.info(templateWithClass, arguments);
        }
    }

    /**
     * For following the trace of the execution. I.e. Knowing if the application
     * access to a method, opening database connection, etc.
     *
     * @param messageTemplate string with static text as template.
     * @param arguments       parameters to fill up the template
     */
    public static void debug(Logger logger, String messageTemplate, Object... arguments) {
        if (logger.isDebugEnabled() && messageTemplate != null) {
            for (int i = 0; i < arguments.length; i++) {
                if (arguments[i] != null) {
                    arguments[i] = arguments[i].toString().replaceAll(NEW_LINE_REGEX, "_");
                }
            }
            messageTemplate = messageTemplate.replaceAll(NEW_LINE_REGEX, "_");
            logger.debug(messageTemplate, arguments);
        }
    }

    /**
     * For following the trace of the execution. I.e. Knowing if the application
     * access to a method, opening database connection, etc.
     *
     * @param logger          the Logger.
     * @param className       the class to log.
     * @param messageTemplate string with static text as template.
     * @param arguments       parameters to fill up the template
     */
    public static void debug(Logger logger, String className, String messageTemplate, Object... arguments) {
        if (logger.isDebugEnabled() && messageTemplate != null) {
            // Replace pattern-breaking characters
            for (int i = 0; i < arguments.length; i++) {
                if (arguments[i] != null) {
                    arguments[i] = arguments[i].toString().replaceAll(NEW_LINE_REGEX, "_");
                }
            }
            messageTemplate = messageTemplate.replaceAll(NEW_LINE_REGEX, "_");
            logger.debug(String.format("%s: %s", className, messageTemplate), arguments); //NOSONAR
        }
    }

    /**
     * To log any not expected error that can cause application malfunctions.
     * I.e. couldn't open a database connection, etc.
     *
     * @param messageTemplate string with static text as template.
     * @param arguments       parameters to fill up the template
     */
    protected static void severe(Logger logger, String messageTemplate, Object... arguments) {
        if (logger.isErrorEnabled() && messageTemplate != null) {
            for (int i = 0; i < arguments.length; i++) {
                if (arguments[i] != null) {
                    arguments[i] = arguments[i].toString().replaceAll(NEW_LINE_REGEX, "_");
                }
            }
            messageTemplate = messageTemplate.replaceAll(NEW_LINE_REGEX, "_");
            logger.error(messageTemplate, arguments);
        }
    }

    /**
     * To log any not expected error that can cause application malfunctions.
     *
     * @param logger          the Logger.
     * @param className       the class to log.
     * @param messageTemplate string with static text as template.
     * @param arguments       parameters to fill up the template
     */
    public static void severe(Logger logger, String className, String messageTemplate, Object... arguments) {
        severe(logger, className + ": " + messageTemplate, arguments);
    }

    public static void errorMessageNotification(Logger logger, String className, Throwable throwable) {
        if (logger.isErrorEnabled()) {
            logger.error("Exception on class {}:\n", className, throwable);
        }
    }
}
