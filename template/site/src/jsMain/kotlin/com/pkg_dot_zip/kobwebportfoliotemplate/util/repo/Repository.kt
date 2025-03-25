package com.pkg_dot_zip.kobwebportfoliotemplate.util.repo

/**
 * A data class representing a GitHub repository retrieved from the <a href="https://docs.github.com/en/rest">GitHub REST API</a>.
 */
data class Repository(
    var id: String?,
    var node_id: String?,
    var name: String?,
    var full_name: String?,
    var private: Boolean?,
    var html_url: String?,
    var description: String?,
    var languages: Array<String?>, // TODO: Actually retrieve from url.
    var fork: Boolean?,
    var stargazers_count: Int?,
    var watchers_count: Int?,
    var open_issues: Int?,
    var archived: Boolean?,
    var license: License?,
    var topics: Array<String>?,
) {
    companion object {
        fun cleanParse(repository: Repository): Repository {
            repository.id = repository.id.clean()
            repository.node_id = repository.node_id.clean()
            repository.name = repository.name.clean()
            repository.full_name = repository.full_name.clean()
            repository.html_url = repository.html_url.clean()
            repository.description = repository.description.clean()
            return repository
        }

        private fun String?.clean(): String? =
            if (this != "null") this?.removeSurrounding("\"") else this
    }

    data class License(
        val key: String,
        val name: String,
        val spdx_id: String,
        val url: String,
        val node_id: String,
    )
}