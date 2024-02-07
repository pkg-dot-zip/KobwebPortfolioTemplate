package io.github.username.projectname.util.extensions

fun String.removeParsingQuotations(): String = this.removePrefix("\"").removeSuffix("\"")