package ru.simplesys.eakd.sbtbuild

import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt._

object CommonDepsScalaJS {

    val macroJS = Def.setting("com.simplesys" %%% "macrojs" % CommonDeps.versions.scalaJSVersion)
    val smartClientWrapper = Def.setting("com.simplesys" %%% "smartclient-wrapper" % CommonDeps.versions.scalaJSVersion)
    //val smartClientWrapper = Def.setting("com.simplesys" %%% "common-types" % CommonDeps.versions.scalaJSVersion)

    val uPickleJS = Def.setting("com.lihaoyi" %%% "upickle" % CommonDeps.versions.uPickleVersion)
    val scalaTags = Def.setting("com.lihaoyi" %%% "scalatags" % CommonDeps.versions.scalaTagsVersion)
    val scalaDom = Def.setting("org.scala-js" %%% "scalajs-dom" % CommonDeps.versions.scalajsDOMVersion)
    //val jQuery = Def.setting("be.doeraene" %%% "scalajs-jquery" % CommonDeps.versions.jQueryVersion)
    val jQuery = Def.setting("io.udash" %%% "udash-jquery" % CommonDeps.versions.scalajsJQueryVersion)
}
