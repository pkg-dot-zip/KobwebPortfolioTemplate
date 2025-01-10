package com.pkg_dot_zip.kobwebportfoliotemplate.util.repo

import androidx.compose.runtime.*
import kotlinx.browser.window
import kotlinx.coroutines.await
import org.w3c.fetch.Request

object RepoHandler {

    // USER TODO: Put your own username(s) here!
    private val users: Array<String> = arrayOf(
        "varabyte",
//        "JetBrains"
    )

    // USER TODO: Put your own repos here. Format is 'Owner/Repo'
    private val specifiedRepos: Array<String> = arrayOf(
//        "square/okhttp"
    )

    @Composable
    fun getAllRepos(repositoryShowingMode: RepositoryShowingMode): List<Repository> {
        var list: MutableList<Repository> = arrayListOf()

        list.addAll(getUserRepos())
        list.addAll(getSpecifiedRepos())

        list.removeAll { repo -> shouldSkipRepo(repo) }

        // Then we sort them.
        list = sortRepoList(list)

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

    /**
     * Returns all repositories listed in [specifiedRepos].
     */
    @Composable
    private fun getSpecifiedRepos() : List<Repository> {
        val repos: ArrayList<Repository> = arrayListOf()

        for (repoToAdd in specifiedRepos) {
            var jsonResponse by remember { mutableStateOf<String?>(null) }

            LaunchedEffect(Unit) {
                jsonResponse = window.fetch(Request("https://api.github.com/repos/${repoToAdd}")).await().text().await()
            }

            if (jsonResponse != null) repos.add(RepoParser.parseRepoFromJson(jsonResponse!!))
        }

        return repos
    }

    /**
     * Returns all repositories from users listed in [users].
     */
    @Composable
    private fun getUserRepos() : List<Repository> {
        val repos: ArrayList<Repository> = arrayListOf()

        for (user in users) {
            var jsonResponse by remember { mutableStateOf<String?>(null) }

            LaunchedEffect(Unit) {
                // NOTE: If a user has more than 100 repos some will be missing here. You can implement it yourself if needed.
                jsonResponse = window.fetch(Request("https://api.github.com/users/${user}/repos?per_page=100")).await().text().await()
            }

            if (jsonResponse != null) repos.addAll(RepoParser.parseRepoFromMultipleRepoJson(jsonResponse!!))
        }

        return repos
    }

    /**
     * Sorts a list of repositories by amount of stars.
     * @param listToSort The list to sort.
     */
    private fun sortRepoList(listToSort: MutableList<Repository>): MutableList<Repository> = listToSort.apply {
        sortByDescending(Repository::stargazers_count)
    }

    private fun shouldSkipRepo(repository: Repository): Boolean {
        if (repository.fork!!) return true // Skip all forks.

        // USER TODO: Add more repos to skip here if needed!

        return false
    }
}