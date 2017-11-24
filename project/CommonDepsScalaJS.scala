package ru.simplesys.eakd.sbtbuild

import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import ru.simplesys.eakd.sbtbuild.CommonDeps.versions
import sbt._

object CommonDepsScalaJS {

    val macroJS = Def.setting("com.simplesys" %%% "macrojs" % versions.scalaJSVersion)
    val smartClientWrapper = Def.setting("com.simplesys" %%% "smartclient-wrapper" % versions.scalaJSVersion)
    //val smartClientWrapper = Def.setting("com.simplesys" %%% "common-types" % versions.scalaJSVersion)

    //val uPickleJS = Def.setting("com.lihaoyi" %%% "upickle" % versions.uPickleVersion)
    val scalaTags = Def.setting("com.lihaoyi" %%% "scalatags" % versions.scalaTagsVersion)
    val scalaDom = Def.setting("org.scala-js" %%% "scalajs-dom" % versions.scalajsDOMVersion)
    //val jQuery = Def.setting("be.doeraene" %%% "scalajs-jquery" % versions.jQueryVersion)
    val jQuery = Def.setting("io.udash" %%% "udash-jquery" % versions.udashJQueryVersion)
    val jsgantImproved = Def.setting("com.simplesys" %%% "jsgantt-improved" % versions.jsgantImprovedVersion)
}
