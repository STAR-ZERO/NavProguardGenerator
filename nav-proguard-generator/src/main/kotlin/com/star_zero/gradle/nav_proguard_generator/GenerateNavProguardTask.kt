package com.star_zero.gradle.nav_proguard_generator

import com.android.utils.forEach
import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.TaskAction
import org.gradle.work.Incremental
import org.w3c.dom.NodeList
import java.io.File
import java.io.FileInputStream
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

open class GenerateNavProguardTask : DefaultTask() {

    @get:Incremental
    @get:InputFiles
    lateinit var navigationFiles: FileCollection

    @TaskAction
    fun generate() {
        val proguardFile = File(project.projectDir, PROGUARD_FILE_NAME)
        if (proguardFile.exists()) {
            proguardFile.delete()
        }

        val keepClasses = mutableListOf<String>()
        navigationFiles.forEach { navFile ->
            val input = FileInputStream(navFile)
            val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(input)

            // Get class name from `app:argType` attribute
            val xpath = XPathFactory.newInstance().newXPath()
            val nodes = xpath.evaluate("//argument", doc, XPathConstants.NODESET)
            (nodes as? NodeList)?.forEach { node ->
                val className = node.attributes.getNamedItem("app:argType").nodeValue
                if (className.contains(".")) {
                    keepClasses.add("-keep class ${className.replace("[]", "")}")
                }
            }
        }

        if (keepClasses.isNotEmpty()) {
            proguardFile.writeText(keepClasses.distinct().joinToString(separator = "\n"))
        }
    }

    companion object {
        const val PROGUARD_FILE_NAME = "nav-proguard-rule.pro"
    }
}
