package io.github.username.projectname.util.github

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
        // This might be the worst shit I ever wrote. Please forgive me.
        // Removes all the quotes, then returns a copy of itself like it's a builder pattern. Although it is not.
        // I originally wanted it to be, but I then forgot why. I'm actually losing it 🗣️🗣️
        fun cleanParse(repository: Repository): Repository {
            if (repository.id != "null") repository.id = repository.id!!.removePrefix("\"").removeSuffix("\"")
            if (repository.node_id != "null") repository.node_id =
                repository.node_id!!.removePrefix("\"").removeSuffix("\"")
            if (repository.name != "null") repository.name = repository.name!!.removePrefix("\"").removeSuffix("\"")
            if (repository.full_name != "null") repository.full_name =
                repository.full_name!!.removePrefix("\"").removeSuffix("\"")
            if (repository.html_url != "null") repository.html_url =
                repository.html_url!!.removePrefix("\"").removeSuffix("\"")
            if (repository.description != "null") repository.description =
                repository.description!!.removePrefix("\"").removeSuffix("\"")
            return repository
        }
    }
}