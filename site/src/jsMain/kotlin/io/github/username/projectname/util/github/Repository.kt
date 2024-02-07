package io.github.username.projectname.util.github

import io.github.username.projectname.util.extensions.removeParsingQuotations

data class Repository(
    var id: String?,
    var node_id: String?,
    var name: String?,
    var full_name: String?,
    var private: Boolean?,
    var html_url: String?,
    var description: String?,
    var languages: Array<String?>,
    var fork: Boolean?,
    var stargazers_count: Int?,
    var watchers_count: Int?,
    var open_issues: Int?,
    var archived: Boolean?,
    var license: License?
) {
    companion object {
        // Removes all the quotes, then returns a copy of itself.
        fun cleanParse(repository: Repository): Repository {
            repository.id = repository.id!!.removeParsingQuotations()
            repository.node_id = repository.node_id!!.removeParsingQuotations()
            repository.name = repository.name!!.removeParsingQuotations()
            repository.full_name = repository.full_name!!.removeParsingQuotations()
            repository.html_url = repository.html_url!!.removeParsingQuotations()
            repository.description = repository.description!!.removeParsingQuotations()
            return repository
        }
    }
}