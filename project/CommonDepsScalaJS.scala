package ru.simplesys.eakd.sbtbuild

import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt._

object CommonDepsScalaJS {

    val macroJS = Def.setting("com.simplesys" %%% "macrojs" % CommonDeps.scalaJSVersion)
    //val smartClientWrapper = Def.setting("com.simplesys" %%% "smartclient-wrapper" % CommonDeps.scalaJSVersion)
    val smartClientWrapper = Def.setting("com.simplesys" %%% "common-types" % CommonDeps.scalaJSVersion)

    //val uPickleJS = Def.setting("com.lihaoyi" %%% "upickle" % CommonDeps.uPickleVersion)
    val scalaTags = Def.setting("com.lihaoyi" %%% "scalatags" % CommonDeps.scalaTagsVersion)
    val scalaDom = Def.setting("org.scala-js" %%% "scalajs-dom" % CommonDeps.scalajsDOMVersion)
    //val jQuery = Def.setting("be.doeraene" %%% "scalajs-jquery" % CommonDeps.jQueryVersion)
    val jQuery = Def.setting("io.udash" %%% "udash-jquery" % CommonDeps.udashJQueryVersion)
}
