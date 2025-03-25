package com.pkg_dot_zip.kobwebportfoliotemplate.util.repo

import com.pkg_dot_zip.kobwebportfoliotemplate.util.Logger
import kotlinx.serialization.json.*

object RepoParser {

    private val logger = Logger.get {  }

    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    fun parseRepoFromMultipleRepoJson(jsonString: String): List<Repository> = parseRepoFromMultipleRepoJson(Json.parseToJsonElement(jsonString))

    private fun parseRepoFromMultipleRepoJson(jsonElement: JsonElement): List<Repository> {
        val listToReturn: MutableList<Repository> = mutableListOf()

        // First we put all the repos in the listToReturn. We 'clean' the repositories here as well.
        for (element in jsonElement.jsonArray) {
            listToReturn.add(parseRepoFromJsonElement(element))
        }

        return listToReturn
    }

    fun parseRepoFromJsonElement(jsonElement: JsonElement): Repository {
        val repo = json.decodeFromJsonElement<Repository>(jsonElement)
        return repo
    }

    fun parseRepoFromJsonString(jsonString: String): Repository {
        val repo = json.decodeFromString<Repository>(jsonString)
        return repo
    }
}