import ru.simplesys.eakd.sbtbuild.{CommonSettings, PluginDeps}
import sbt._
import sbt.Project._


//lazy val devPlugin = uri("../../sbt-plugins/dev-plugin")
//lazy val sbtCoffeeScript = uri("../../sbt-plugins/sbt-coffeescript")
//lazy val sbtNativePackager = uri("../../sbt-plugins/sbt-native-packager")
//lazy val mergeJS = uri("../../sbt-plugins/merge-js")

lazy val root = Project(id = "buildPlugins", base = file(".")).dependsOn(/*RootProject(devPlugin)*/  /*RootProject(sbtCoffeeScript)*/  /*RootProject(sbtNativePackager)*/ /*, RootProject(mergeJS)*/).
  settings(sbt.inThisBuild(CommonSettings.defaultSettings)).
  settings(
      classpathTypes += "maven-plugin",
      PluginDeps.devPlugin,
      PluginDeps.sbtCoffeeScript,
      PluginDeps.mergeJS,
      PluginDeps.xsbtWeb,
      PluginDeps.sbtNativePackager,
      PluginDeps.jrebelPlugin,
      PluginDeps.scalaJSPlugin
  )
