package com.pkg_dot_zip.kobwebportfoliotemplate.util.repo

import kotlinx.serialization.json.*

object RepoHandler {

    const val API_URL: String = "https://api.github.com/users/varabyte/repos"

    fun getRepoList(json: String, repositoryShowingMode: RepositoryShowingMode): List<Repository> {
        val list: List<Repository> = getRepoListFromJson(json)

        // Generate UI element for repos (which ones dependent on REPOSITORY_SHOWING_MODE).
        return when (repositoryShowingMode) {
            RepositoryShowingMode.ALL -> list

            // Check if there are even 3 repositories at all. If not, we create a top 0, 1 or 2.
            RepositoryShowingMode.MOST_STARRED ->
                list.sortedByDescending { repository -> repository.stargazers_count }
                    .subList(0, if (list.size < 3) list.size else 3)

            RepositoryShowingMode.NO_FORKED -> list.filter { repository -> !repository.fork!! }
            RepositoryShowingMode.FORKED_ONLY -> list.filter { repository -> repository.fork!! }
        }
    }

    private fun getRepoListFromJson(json: String): List<Repository> {
        val listToReturn: MutableList<Repository> = mutableListOf()

        // First we put all the repos in the listToReturn. We 'clean' the repositories here as well.
        for (jsonElement in Json.parseToJsonElement(json).jsonArray) {

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
            if (shouldSkipRepo(repository)) continue // Check if need to skip, if so skip.

            listToReturn.add(repository)
        }

        // Then we sort them and return them.
        return sortRepoList(listToReturn)
    }

    private fun sortRepoList(listToSort: MutableList<Repository>): MutableList<Repository> = listToSort.apply {
        sortByDescending(Repository::stargazers_count) // Sort them by stars.
    }

    private fun shouldSkipRepo(repository: Repository): Boolean {
        if (repository.fork!!) return true // Skip all forks.

        // Add more repos to skip here!

        return false
    }
}