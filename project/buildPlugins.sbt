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
        CommonDeps.scalaJSPlugin,
        CommonDeps.scalaCrossProjectPlugin,
        CommonDeps.scalaJSCrossProjectPlugin
  )


