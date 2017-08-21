import sbt._
import com.ivcInform.build.{CommonSettings}

lazy val root = Project(id = "buildPlugins", base = file(".")).
  settings(inThisBuild(CommonSettings.defaultSettings)).
  settings(
      //PluginDeps.scalaJSPlugin
  )
