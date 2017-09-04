import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt._

object CommonDepsScalaJS {

    val macroJS = Def.setting("com.simplesys" %%% "macrojs" % Versions.scalaJSVersion)
    //val smartClientWrapper = Def.setting("com.simplesys" %%% "smartclient-wrapper" % Versions.scalaJSVersion)
    val smartClientWrapper = Def.setting("com.simplesys" %%% "common-types" % Versions.scalaJSVersion)

    val uPickleJS = Def.setting("com.lihaoyi" %%% "upickle" % Versions.uPickleVersion)
    val scalaTags = Def.setting("com.lihaoyi" %%% "scalatags" % Versions.scalaTagsVersion)
    val scalaDom = Def.setting("org.scala-js" %%% "scalajs-dom" % Versions.scalaDomVersion)
    val jQuery = Def.setting("be.doeraene" %%% "scalajs-jquery" % Versions.jQueryVersion)
}
