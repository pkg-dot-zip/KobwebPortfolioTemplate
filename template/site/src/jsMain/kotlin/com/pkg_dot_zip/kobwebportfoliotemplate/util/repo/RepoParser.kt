package com.pkg_dot_zip.kobwebportfoliotemplate.util.repo

import kotlinx.serialization.json.*

object RepoParser {
    fun parseRepoFromMultipleRepoJson(jsonString: String): List<Repository> = parseRepoFromMultipleRepoJson(Json.parseToJsonElement(jsonString))

    private fun parseRepoFromMultipleRepoJson(jsonElement: JsonElement): List<Repository> {
        val listToReturn: MutableList<Repository> = mutableListOf()

        // First we put all the repos in the listToReturn. We 'clean' the repositories here as well.
        for (element in jsonElement.jsonArray) {
            listToReturn.add(parseRepoFromJson(element))
        }

        return listToReturn
    }

    fun parseRepoFromJson(jsonString: String): Repository = parseRepoFromJson(Json.parseToJsonElement(jsonString))

    private fun parseRepoFromJson(jsonElement: JsonElement): Repository {
        // License processing.
        var license = Repository.License("No license found.", "", "", "", "")
        if (jsonElement.jsonObject["license"].toString() != "null") {
            license = Repository.License(
                key = jsonElement.jsonObject["license"]!!.jsonObject["key"].toString(),
                name = jsonElement.jsonObject["license"]!!.jsonObject["name"].toString(),
                spdx_id = jsonElement.jsonObject["license"]!!.jsonObject["spdx_id"].toString(),
                url = jsonElement.jsonObject["license"]!!.jsonObject["url"].toString(),
                node_id = jsonElement.jsonObject["license"]!!.jsonObject["node_id"].toString(),
            )
        }

        // Repo processing.
        var repository = Repository(
            name = jsonElement.jsonObject["name"].toString(),
            description = jsonElement.jsonObject["description"].toString(),
            id = jsonElement.jsonObject["id"].toString(),
            node_id = jsonElement.jsonObject["node_id"].toString(),
            full_name = jsonElement.jsonObject["full_name"]!!.jsonPrimitive.toString(),
            archived = jsonElement.jsonObject["archived"]!!.jsonPrimitive.boolean,
            open_issues = jsonElement.jsonObject["open_issues"]!!.jsonPrimitive.int,
            watchers_count = jsonElement.jsonObject["watchers_count"]!!.jsonPrimitive.int,
            stargazers_count = jsonElement.jsonObject["stargazers_count"]!!.jsonPrimitive.int,
            license = license,
            fork = jsonElement.jsonObject["fork"]!!.jsonPrimitive.boolean,
            private = jsonElement.jsonObject["private"]!!.jsonPrimitive.boolean,
            languages = arrayOf(jsonElement.jsonObject["language"].toString()),
            html_url = jsonElement.jsonObject["html_url"]!!.jsonPrimitive.toString(),
            topics = jsonElement.jsonObject["topics"]!!.jsonArray.map { it.jsonPrimitive.content }.toTypedArray(),
        )

        repository = Repository.cleanParse(repository) // Remove quotes from strings.
        return repository
    }
}