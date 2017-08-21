import com.ivc_inform.build.{CommonSettings, PluginDeps}
import sbt._

lazy val root = Project(id = "buildPlugins", base = file(".")).enablePlugins(GitVersioning).
  settings(inThisBuild(CommonSettings.defaultSettings)).
  settings(
      PluginDeps.scalaJSPlugin
  )
