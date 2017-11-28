package ru.simplesys.eakd.sbtbuild

import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import ru.simplesys.eakd.sbtbuild.CommonDeps.versions
import sbt._

object CommonDepsScalaJS {

    val smartClientWrapper = Def.setting("com.simplesys" %%% "smartclient-wrapper" % versions.scalaJSVersion)
    //val smartClientWrapper = ProjectRef(uri("../../JOB/scalajs"), "smartClientCrossProjJS")
    
    val scalaTags = Def.setting("com.lihaoyi" %%% "scalatags" % versions.scalaTagsVersion)
    val scalaDom = Def.setting("org.scala-js" %%% "scalajs-dom" % versions.scalajsDOMVersion)
    val jQuery = Def.setting("io.udash" %%% "udash-jquery" % versions.udashJQueryVersion)

    //val jsgantImproved = Def.setting("com.simplesys" %%% "jsgantt-improved" % versions.jsgantImprovedVersion)
    val jsgantImproved =  ProjectRef(uri("../../JOB/jsgantt-improved"), "ganttImprovedJS")
}
