package com.pkg_dot_zip.kobwebportfoliotemplate.components.sections

import androidx.compose.runtime.Composable
import com.pkg_dot_zip.kobwebportfoliotemplate.components.widgets.RepoWidget
import com.pkg_dot_zip.kobwebportfoliotemplate.util.Logger
import com.pkg_dot_zip.kobwebportfoliotemplate.util.repo.RepoHandler
import com.pkg_dot_zip.kobwebportfoliotemplate.util.repo.RepositoryShowingMode
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns

/**
 * All the cards on the repositories page.
 */
@Composable
fun CustomRepoCardSection() {
    val logger = Logger.get("CustomRepoCardSection")

    SimpleGrid(numColumns(base = 1, sm = 1, md = 2, lg = 2)) {
        for (repository in RepoHandler.getAllRepos(RepositoryShowingMode.ALL)) {
            logger.info("Creating UI Element for repo: ${repository.name}")
            RepoWidget(repository)
        }
    }
}
