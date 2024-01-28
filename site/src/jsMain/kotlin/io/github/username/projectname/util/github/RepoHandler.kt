package io.github.username.projectname.util.github

import io.github.username.projectname.Config
import kotlinx.serialization.json.*

object RepoHandler {

    const val API_URL: String = "https://api.github.com/users/${Config.Socials.GITHUB_USERNAME}/repos"

    enum class RepositoryShowingMode {
        ALL,
        NO_FORKED,
        FORKED_ONLY,
        FEATURED,
        MOST_STARRED
    }

    fun getRepoList(json: String, repositoryShowingMode: RepositoryShowingMode): List<Repository> {
        val list: List<Repository> = getRepoListFromJson(json)

        // Generate UI element for repos (which ones dependent on REPOSITORY_SHOWING_MODE).
        return when (repositoryShowingMode) {
            RepositoryShowingMode.ALL -> list
            RepositoryShowingMode.FEATURED ->
                list.filter { repository -> Config.Repositories.REPOSITORY_FEATURES_LIST.contains(repository.name) }

            // Check if there are even 3 repositories at all. If not, we create a top 0, 1 or 2.
            RepositoryShowingMode.MOST_STARRED ->
                list.sortedByDescending { repository -> repository.stargazers_count }
                    .subList(0, if (list.size < 3) list.size else 3)

            RepositoryShowingMode.NO_FORKED -> list.filter { repository -> !repository.fork!! }
            RepositoryShowingMode.FORKED_ONLY -> list.filter { repository -> repository.fork!! }
        }
    }

    private fun getRepoListFromJson(json: String): List<Repository> {
        val listToReturn: MutableList<Repository> = ArrayList()

        // First we put all the repos in the listToReturn. We 'clean' the repositories here as well.
        for (jsonElement in Json.parseToJsonElement(json).jsonArray) {

            // License processing.
            var license = License("No license found.", "", "", "", "")
            if (jsonElement.jsonObject["license"].toString() != "null") {
                license = License(
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
                html_url = jsonElement.jsonObject["html_url"]!!.jsonPrimitive.toString()
            )

            repository = Repository.cleanParse(repository) // Remove quotes from strings.
            if (shouldSkipRepo(repository)) continue // Check if need to skip, if so skip.

            listToReturn.add(repository)
        }

        // Then we sort them and return them.
        return sortRepoList(listToReturn)
    }

    private fun sortRepoList(listToSort: MutableList<Repository>): MutableList<Repository> {
        return listToSort.also {
            it.sortByDescending(Repository::stargazers_count) // Sort them by stars.
        }
    }


    // NOTE: This method can be rewritten -> (see return statements).
    // This is entirely up to the developer of the website.
    private fun shouldSkipRepo(repository: Repository): Boolean {
        for (reason in Config.Repositories.getReasonsToKeepRepo(repository)) {
            if (!reason) continue

            println("${repository.name} was in exceptions. Adding anyway.")
            return false
        }


        for (reason in Config.Repositories.getReasonsToSkipRepo(repository)) {
            if (!reason) continue

            println("${repository.name} was in reasons to skip. Ignoring ${repository.name}.")
            return true
        }

        return false // No reasons found -> We can make a nice repo box.
    }
}