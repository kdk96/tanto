package publish

import com.jfrog.bintray.gradle.BintrayExtension
import com.jfrog.bintray.gradle.BintrayPlugin
import com.jfrog.bintray.gradle.tasks.BintrayUploadTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.internal.artifact.FileBasedMavenArtifact
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import publish.data.AuthBintrayData
import publish.data.PublishingData
import java.util.Date

class PublishPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.apply<MavenPublishPlugin>()
        project.apply<BintrayPlugin>()

        val publishingData = PublishingData.from(project)
        val authData = AuthBintrayData.from(project)

        if (authData.isEmpty) {
            project.logger.warn("Auth bintray data is empty")
        }
        setupBintrayPublishingInformation(project, publishingData, authData)
    }

    private fun setupBintrayPublishingInformation(
        target: Project,
        publishingData: PublishingData,
        authData: AuthBintrayData
    ) {
        target.extensions.getByType(BintrayExtension::class.java).apply {
            user = authData.user
            key = authData.key
            publish = publishingData.publishAfterUpload
            setPublications(Publications.tanto, Publications.tantoAndroid)
            pkg.apply {
                repo = publishingData.repoName
                name = target.name
                vcsUrl = publishingData.vcsUrl
                githubRepo = publishingData.githubRepo
                userOrg = publishingData.organization
                issueTrackerUrl = publishingData.issueTrackerUrl
                version.apply {
                    released = Date().toString()
                    name = target.version as String
                }
                setLicenses(publishingData.licenseName)
            }
        }
    }
}
