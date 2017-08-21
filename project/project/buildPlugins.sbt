import sbt._
import com.ivc_inform.build.{CommonSettings, PluginDeps}

lazy val root = Project(id = "buildPlugins", base = file(".")).
  settings(inThisBuild(CommonSettings.defaultSettings)).
  settings(
      PluginDeps.scalaJSPlugin
  )
