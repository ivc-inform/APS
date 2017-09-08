import CommonDeps.versions
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt._

object CommonDepsScalaJS {

    val macroJS = Def.setting("com.simplesys" %%% "macrojs" % versions.scalaJSVersion)
    val smartClientWrapper = Def.setting("com.simplesys" %%% "smartclient-wrapper" % versions.scalaJSVersion)
    //val smartClientWrapper = Def.setting("com.simplesys" %%% "common-types" % versions.scalaJSVersion)

    val uPickleJS = Def.setting("com.lihaoyi" %%% "upickle" % versions.uPickleVersion)
    val scalaTags = Def.setting("com.lihaoyi" %%% "scalatags" % versions.scalaTagsVersion)
    val scalaDom = Def.setting("org.scala-js" %%% "scalajs-dom" % versions.scalajsDOMVersion)
    val jQuery = Def.setting("be.doeraene" %%% "scalajs-jquery" % versions.scalajsJQueryVersion)
}
