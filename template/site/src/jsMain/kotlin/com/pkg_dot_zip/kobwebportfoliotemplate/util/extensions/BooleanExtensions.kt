package com.pkg_dot_zip.kobwebportfoliotemplate.util.extensions

/**
 * Converts a [Boolean] value to an [Int].
 * @return `1` if the Boolean value is `true`, `0` if it is `false`.
 */
fun Boolean.toInt(): Int = if (this) 1 else 0