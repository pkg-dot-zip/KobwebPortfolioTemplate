package com.pkg_dot_zip.kobwebportfoliotemplate.util.blogPost

import com.pkg_dot_zip.kobwebportfoliotemplate.util.Logger
import com.pkg_dot_zip.kobwebportfoliotemplate.util.extensions.toKebabCase
import com.pkg_dot_zip.kobwebportfoliotemplate.util.repo.Repository
import com.varabyte.kobweb.core.PageContext

object ProjectPageHandler {
    private val logger = Logger.get { }

    /**
     * Checks whether a project page actually exists.
     */
    fun repoHasProjectPage(ctx: PageContext, repo: Repository): Boolean {
        if (repo.name.isNullOrEmpty()) return false

        logger.trace("Checking if project page exists for: ${repo.name}")
        if (getProjectPages(ctx).contains(projectPagePathString(repo))) {
            logger.trace("Project page for ${repo.name} was found! ✅")
            return true
        } else {
            logger.trace("Project page for ${repo.name} was not found! ❌")
            return false
        }
    }

    /**
     * Returns the would-be route to a project's page. Does not check if it exists.
     */
    fun projectPagePathString(repo: Repository): String = "/projects/" + repo.name?.toKebabCase()

    fun getProjectPages(ctx: PageContext): Sequence<String> {
        return ctx.router.routes.map { it.path }.filter { it.startsWith("/projects/") }
    }
}