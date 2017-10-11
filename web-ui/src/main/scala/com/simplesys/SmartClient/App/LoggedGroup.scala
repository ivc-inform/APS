package com.simplesys.SmartClient.App

object LoggedGroup {
    var logged = false
    var loginedGroup: Option[String] = None

    def isDevsGroup(): Boolean = {
        val _loginedGroup = loginedGroup.getOrElse("")
        _loginedGroup == "developers"
    }
    def isAdminsGroup(): Boolean = loginedGroup.getOrElse("") == "admins"
    def isAnalystsGroup(): Boolean = loginedGroup.getOrElse("") == "analysts"
    def isRoot(): Boolean = loginedGroup.getOrElse("") == "root"
}

