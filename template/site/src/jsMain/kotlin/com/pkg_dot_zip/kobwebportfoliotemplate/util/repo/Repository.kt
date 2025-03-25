package com.pkg_dot_zip.kobwebportfoliotemplate.util.repo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A data class representing a GitHub repository retrieved from the <a href="https://docs.github.com/en/rest">GitHub REST API</a>.
 */
@Serializable
data class Repository(
    var id: String? = null,
    @SerialName("node_id")
    var nodeId: String? = null,
    var name: String? = null,
    @SerialName("full_name")
    var fullName: String? = null,
    var private: Boolean? = null,
    @SerialName("html_url")
    var htmlUrl: String? = null,
    var description: String? = null,
    var fork: Boolean? = null,
    @SerialName("stargazers_count")
    var stargazersCount: Int? = null,
    @SerialName("watchers_count")
    var watchersCount: Int? = null,
    @SerialName("open_issues")
    var openIssues: Int? = null,
    var archived: Boolean? = null,
    var size: Int? = null,
    @SerialName("has_issues")
    var hasIssues: Boolean? = null,
    @SerialName("has_projects")
    var hasProjects: Boolean? = null,
    @SerialName("has_downloads")
    var hasDownloads: Boolean? = null,
    @SerialName("has_wiki")
    var hasWiki: Boolean? = null,
    @SerialName("has_pages")
    var hasPages: Boolean? = null,
    @SerialName("has_discussions")
    var hasDiscussions: Boolean? = null,
    @SerialName("forks_count")
    var forksCount: Int? = null,
    var disabled: Boolean? = null,
    @SerialName("allow_forking")
    var allowForking: Boolean? = null,
    @SerialName("is_template")
    var isTemplate: Boolean? = null,
    @SerialName("web_commit_signoff_required")
    var webCommitSignoffRequired: Boolean? = null,
    var topics: Array<String>? = arrayOf(),
    var visibility: String? = null,
    @SerialName("default_branch")
    var defaultBranch: String? = null,
    var language: String? = null,
)