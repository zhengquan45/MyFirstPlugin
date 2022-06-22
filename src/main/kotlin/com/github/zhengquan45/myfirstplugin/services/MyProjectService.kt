package com.github.zhengquan45.myfirstplugin.services

import com.intellij.openapi.project.Project
import com.github.zhengquan45.myfirstplugin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
