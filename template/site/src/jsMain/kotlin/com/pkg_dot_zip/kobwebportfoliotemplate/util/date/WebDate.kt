package com.pkg_dot_zip.kobwebportfoliotemplate.util.date

import com.pkg_dot_zip.kobwebportfoliotemplate.util.Logger
import kotlin.js.Date

/**
 * A data class representing a date and time.
 *
 * @property years The year of the date.
 * @property months The month of the date (1-12).
 * @property days The day of the date (1-31).
 * @property hours The hour of the time (0-23).
 * @property minutes The minute of the time (0-59).
 * @property seconds The second of the time (0-59).
 */
data class WebDate(
    var years: Int = 0,
    var months: Int = 0,
    var days: Int = 0,
    var hours: Int = 0,
    var minutes: Int = 0,
    var seconds: Int = 0,
) {
    fun onSameDateAndYearAndHour(other: WebDate): Boolean = this.hours == other.hours && onSameDateAndYear(other)
    fun onSameDateAndYear(other: WebDate): Boolean = this.years == other.years && onSameDate(other)
    fun onSameDate(other: WebDate): Boolean = this.months == other.months && this.days == other.days

    /**
     * Converts this [WebDate] instance to a standard [Date] object.
     *
     * @return A [Date] object representing the same date and time as this [WebDate].
     */
    fun toDate(): Date = Date(this.years, this.months, this.days, this.hours, this.minutes, this.seconds)

    companion object {
        private val logger = Logger.get { }

        /**
         * Returns the current date and time as an instance of [WebDate].
         *
         * @return A [WebDate] instance representing the current date and time with a hardcoded timezone fix.
         */
        fun now(): WebDate = parseDateTime(Date()).apply {
            logger.warning("Manually applying hardcoded timezone fix.")
            this.hours += 2 // HARDCODED TIMEZONE FIX.
        }

        private fun parseDateTime(date: Date): WebDate = parseString(date.toISOString())

        private fun parseString(dateTimeString: String): WebDate = WebDate(
            years = parseDate(dateTimeString).first,
            months = parseDate(dateTimeString).second,
            days = parseDate(dateTimeString).third,
            hours = parseTime(dateTimeString).first,
            minutes = parseTime(dateTimeString).second,
            seconds = parseTime(dateTimeString).third,
        )

        private fun parseDate(dateTimeIsoString: String): Triple<Int, Int, Int> {
            val datePart = dateTimeIsoString.split("T")[0]
            val (year, month, day) = datePart.split("-").map { it.toInt() }
            return Triple(year, month, day)
        }

        private fun parseTime(dateTimeIsoString: String): Triple<Int, Int, Int> {
            val timePart = dateTimeIsoString.split("T")[1].split("Z")[0]
            val (hour, minute, sec) = timePart.split(":")
            val seconds = sec.split(".")[0].toInt()
            return Triple(hour.toInt(), minute.toInt(), seconds)
        }
    }
}