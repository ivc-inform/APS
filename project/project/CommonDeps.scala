package com.ivc_inform.build

import com.ivc_inform.build.CommonSettings.{settingValues, versions}
import sbt._

object PluginDeps {
    object versions {
        val scalaJSPluginVersion = "0.6.19"
    }

    val scalaJSPlugin = addSbtPlugin("org.scala-js" % "sbt-scalajs" % versions.scalaJSPluginVersion)
}


object CommonDeps {
    val scalaTest = "org.scalatest" %% "scalatest" % versions.scalaTestVersion % Test
}
