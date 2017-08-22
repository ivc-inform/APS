import sbtcrossproject.{CrossType, crossProject}

val _scalaVersion = "2.12.3"

lazy val aps = crossProject(JSPlatform, JVMPlatform)
  .settings(scalaVersion := _scalaVersion)
  .aggregate(bar)
  .dependsOn(bar)

lazy val apsJS = aps.js
lazy val apsJVM = aps.jvm

lazy val bar = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .settings(scalaVersion := _scalaVersion)

lazy val barJS = bar.js
lazy val barJVM = bar.jvm


