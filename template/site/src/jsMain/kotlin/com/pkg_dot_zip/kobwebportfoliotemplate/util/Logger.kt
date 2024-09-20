package com.pkg_dot_zip.kobwebportfoliotemplate.util

/**
 * A simple logging utility for console output with various log levels such as trace, info, debug, warning, and error.
 * The messages are prefixed with an emoji and the class name that logs the message.
 *
 * @property className The name of the class that instantiated this logger.
 * @constructor Creates a [Logger] instance for the specified class name.
 */
class Logger private constructor(private val className: String) {

    /**
     * Prints a message to the console.
     *
     * @param msg The message to be printed.
     */
    private fun printToConsole(msg: String) = println(msg)

    /**
     * Formats and prints a log message with an emoji prefix and class name.
     *
     * @param emoji The emoji representing the log level.
     * @param msg The log message.
     * @param className Optional parameter to override the class name in the log.
     */
    private fun printLog(emoji: String, msg: String, className: String = this.className) =
        printToConsole("$emoji $className - $msg")

    /**
     * Logs a message at the trace level (🔎).
     *
     * @param str The message to be logged.
     */
    fun trace(str: String) = printLog("\uD83D\uDD0E", str)

    /**
     * Logs a message at the info level (🖨️).
     *
     * @param str The message to be logged.
     */
    fun info(str: String) = printLog("\uD83D\uDDA8\uFE0F", str)

    /**
     * Logs a message at the debug level (🧪).
     *
     * @param str The message to be logged.
     */
    fun debug(str: String) = printLog("\uD83E\uDDEA", str)

    /**
     * Logs a message at the warning level (⚠️).
     *
     * @param str The message to be logged.
     */
    fun warning(str: String) = printLog("⚠\uFE0F", str)

    /**
     * Logs a message at the error level (⛔).
     *
     * @param str The message to be logged.
     */
    fun error(str: String) = printLog("⛔", str)

    /**
     * Companion object that provides factory methods for obtaining a [Logger] instance.
     */
    companion object {
        /**
         * Retrieves a [Logger] instance for the class that invoked this method.
         * The class name is dynamically extracted from the stack trace.
         *
         * @param func A lambda function representing the call context.
         * @return A [Logger] instance associated with the calling class.
         */
        fun get(func: () -> Unit): Logger {
            val stackTrace = js("Error().stack") as String
            val className = extractClassNameFromStackTrace(stackTrace)
            return get(className)
        }

        /**
         * Retrieves a [Logger] instance for the specified class name.
         *
         * @param name The name of the class for which to create the logger.
         * @return A [Logger] instance associated with the specified class name.
         */
        fun get(name: String): Logger = Logger(name)

        /**
         * Extracts the class name from the JavaScript stack trace.
         *
         * @param stackTrace The JavaScript stack trace as a [String].
         * @return The class name of the caller, or "Unknown" if it cannot be determined.
         */
        private fun extractClassNameFromStackTrace(stackTrace: String): String {
            val lines = stackTrace.split("\n")

            // Get the second line, which is usually the caller line.
            if (lines.size > 2) {
                val callerLine = lines[2].trim()

                // Regex to match class or function name, removing 'new' if present. This was done by AI since I struggle with Regex.
                val regex = Regex("""at\s+(?:new\s+)?(.*?)\s+\(""")
                val matchResult = regex.find(callerLine)

                return matchResult?.groups?.get(1)?.value ?: "Unknown"
            }

            return "Unknown"
        }
    }
}