package utils;

import com.aventstack.extentreports.markuputils.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtils {

    private static final Logger logger = LogManager.getLogger(LoggerUtils.class);

    public enum LogsType {
        INFO, FAIL, ERROR, SKIP
    }

    /**
     * Logs messages with optional code blocks to both ExtentReports and Log4j2.
     *
     * @param logType      The type of log (INFO, FAIL, ERROR, SKIP).
     * @param message      The main message to log.
     * @param customLogMsg Optional code block to include in the log.
     */
    public static void reportLog(LogsType logType, String message, String... customLogMsg) {
        Markup messageMarkup = MarkupHelper.createLabel(message, getColorForLogType(logType));
        String customMsg = customLogMsg.length > 0 ? customLogMsg[0] : null;
        Markup customMarkup = customMsg != null ? MarkupHelper.createCodeBlock(customMsg) : null;

        switch (logType) {
            case FAIL:
                ExtentTestManager.getTest().fail(messageMarkup);
                if (customMarkup != null) ExtentTestManager.getTest().fail(customMarkup);
                logger.error(message);
                if (customMsg != null) logger.error(customMsg);
                break;
            case INFO:
                ExtentTestManager.getTest().info(messageMarkup);
                if (customMarkup != null) ExtentTestManager.getTest().info(customMarkup);
                logger.info(message);
                if (customMsg != null) logger.info(customMsg);
                break;
            case ERROR:
                ExtentTestManager.getTest().warning(messageMarkup);
                if (customMarkup != null) ExtentTestManager.getTest().warning(customMarkup);
                logger.error(message);
                if (customMsg != null) logger.error(customMsg);
                break;
            case SKIP:
                ExtentTestManager.getTest().skip(messageMarkup);
                if (customMarkup != null) ExtentTestManager.getTest().skip(customMarkup);
                logger.debug(message);
                if (customMsg != null) logger.debug(customMsg);
                break;
        }
    }

    /**
     * Logs a message at INFO level.
     *
     * @param message The message to log.
     */
    public static void reportLog(String message) {
        logger.info(message);
    }

    /**
     * Determines the ExtentColor based on the log type.
     *
     * @param logType The type of log.
     * @return The corresponding ExtentColor.
     */
    private static ExtentColor getColorForLogType(LogsType logType) {
        switch (logType) {
            case FAIL:
                return ExtentColor.RED;
            case INFO:
                return ExtentColor.GREEN;
            case ERROR:
                return ExtentColor.LIME;
            case SKIP:
                return ExtentColor.YELLOW;
            default:
                return ExtentColor.BLACK;
        }
    }
}