lazy val root = Project(id = "buildPlugins", base = file(".")).dependsOn().
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
        PluginDeps.jrebelPlugin,
        PluginDeps.sbtResolver
  )


