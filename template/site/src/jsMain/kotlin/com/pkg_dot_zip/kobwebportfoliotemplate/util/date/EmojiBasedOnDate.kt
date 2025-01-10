package com.pkg_dot_zip.kobwebportfoliotemplate.util.date

import com.pkg_dot_zip.kobwebportfoliotemplate.Res
import com.pkg_dot_zip.kobwebportfoliotemplate.util.Logger

object EmojiBasedOnDate {
    private val logger = Logger.get { }

    /***
     * These are all the emojis displayed on the homepage if no specified emoji for this date was found (see [SpecialDateHandler]).
     */
    val emojis: Collection<String> = setOf(
        Res.AnimatedEmojis.ASTONISHED_FACE,
        Res.AnimatedEmojis.NINJA,
        Res.AnimatedEmojis.HAMSA,
        Res.AnimatedEmojis.FACTORY_WORKER,
        Res.AnimatedEmojis.FIREFIGHTER,
        Res.AnimatedEmojis.MAN_BIKING,
        Res.AnimatedEmojis.MAN_DANCING,
        Res.AnimatedEmojis.MAN_SURFING,
        Res.AnimatedEmojis.MAN_CLIMBING,
        Res.AnimatedEmojis.MAN_JUGGLING,
        Res.AnimatedEmojis.MAN_BOUNCING_BALL,
        Res.AnimatedEmojis.MAN_CARTWHEELING,
        Res.AnimatedEmojis.MAN_IN_LOTUS_POSITION,
        Res.AnimatedEmojis.MAN_SWIMMING,
        Res.AnimatedEmojis.MAN_TECHNOLOGIST,
        Res.AnimatedEmojis.MAN_TIPPING_HAND,
        Res.AnimatedEmojis.SPEAKING_HEAD,
    )

    /**
     * Returns an emoji to use on the front page of the site.
     * If [currentDate] is considered a [SpecialDateHandler.SpecialDate] the emoji that fits that event is returned.
     * If not, a random emoji is chosen instead from [emojis].
     *
     * @param currentDate The current date.
     * @return The path to the resource image of the emoji as a [String].
     */
    fun getEmojiRandomly(currentDate: WebDate = WebDate.now()): String {
        logger.info("Retrieving Emoji at $currentDate")

        val specialDateEmoji = SpecialDateHandler.getValueOnSpecialDate(currentDate) { it.frontPageEmoji }
        if (specialDateEmoji != null) return specialDateEmoji

        val availableEmojis = emojis
        val randomIndex = availableEmojis.indices.random()
        logger.trace("Random index: $randomIndex")
        return availableEmojis.elementAt(randomIndex)
    }
}