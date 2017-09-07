
//lazy val mergeJS = uri("../../sbt-plugins/merge-js")

lazy val root = Project(id = "buildPlugins", base = file(".")).dependsOn(/*RootProject(mergeJS)*/).
  settings(sbt.inThisBuild(scalacOptions ++= Seq(
      "-deprecation",
      "-unchecked",
      "-feature",
      "-encoding",
      "utf8"
  ))).
  settings(
      classpathTypes += "maven-plugin",
      PluginDeps.scalaJSPlugin,
      PluginDeps.scalaCrossProjectPlugin,
      PluginDeps.scalaJSCrossProjectPlugin,
      PluginDeps.mergeJS,
      PluginDeps.sbtNativePackager,
      PluginDeps.sbtResolver
  )


