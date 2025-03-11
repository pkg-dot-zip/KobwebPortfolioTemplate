package com.pkg_dot_zip.kobwebportfoliotemplate.util.extensions

/**
 * Replace uppercase letters with a hyphen and their lowercase counterpart.
 * The regex "(?<!^)([A-Z])" matches any uppercase letter that isn't at the start of the string.
 */
fun String.toKebabCase(): String {
    return this.replace(Regex("(?<!^)([A-Z])"), "-$1").lowercase()
}