package com.star_zero.gradle.nav_proguard_generator

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.FeatureExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.api.BaseVariant
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.FileCollection
import java.io.File

class NavProguardGeneratorPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.afterEvaluate {
            val extension = project.extensions.findByType(BaseExtension::class.java) ?: return@afterEvaluate
            forEachVariant(extension) { variant ->
                createTask(project, variant)
            }
            addProguardFile(project, extension)
        }
    }

    private fun forEachVariant(extension: BaseExtension, action: (BaseVariant) -> Unit) {
        when (extension) {
            is AppExtension -> extension.applicationVariants.all(action)
            is LibraryExtension -> {
                extension.libraryVariants.all(action)
                if (extension is FeatureExtension) {
                    extension.featureVariants.all(action)
                }
            }
        }
    }

    private fun addProguardFile(project: Project, extension: BaseExtension) {
        val proguardFile = File(project.projectDir, GenerateNavProguardTask.PROGUARD_FILE_NAME)
        when (extension) {
            is AppExtension, is FeatureExtension -> {
                extension.defaultConfig.proguardFiles.add(proguardFile)
            }
            is LibraryExtension -> {
                extension.defaultConfig.consumerProguardFiles.add(proguardFile)
            }
        }
    }

    private fun createTask(project: Project, variant: BaseVariant) {
        val task = project.tasks.create(
            "generateNavProguard${variant.name.capitalize()}",
            GenerateNavProguardTask::class.java
        )
        task.navigationFiles = navigationFiles(project, variant)

        // Run before `pre${Variant}Build` task
        val preBuildName = "pre${variant.name.capitalize()}Build"
        val preBuild = project.tasks.findByName(preBuildName) ?: throw GradleException("`${preBuildName}` task is not found.")
        preBuild.dependsOn(task)
    }

    private fun navigationFiles(project: Project, variant: BaseVariant): FileCollection {
        val files = variant.sourceSets
            .flatMap {
                it.resDirectories
            }.mapNotNull {
                File(it, "navigation").let { navDir ->
                    if (navDir.exists() && navDir.isDirectory) {
                        navDir
                    } else {
                        null
                    }
                }
            }.flatMap {
                it.listFiles().asIterable()
            }
        return project.layout.files(files)
    }
}
