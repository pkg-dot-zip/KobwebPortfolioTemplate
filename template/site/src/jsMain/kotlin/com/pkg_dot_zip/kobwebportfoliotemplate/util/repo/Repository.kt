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
            if (repository.id != "null") repository.id = repository.id!!.removeSurrounding("\"")
            if (repository.node_id != "null") repository.node_id =
                repository.node_id!!.removeSurrounding("\"")
            if (repository.name != "null") repository.name = repository.name!!.removeSurrounding("\"")
            if (repository.full_name != "null") repository.full_name =
                repository.full_name!!.removeSurrounding("\"")
            if (repository.html_url != "null") repository.html_url =
                repository.html_url!!.removeSurrounding("\"")
            if (repository.description != "null") repository.description =
                repository.description!!.removeSurrounding("\"")
            return repository
        }
    }

    data class License(
        val key: String,
        val name: String,
        val spdx_id: String,
        val url: String,
        val node_id: String,
    )
}