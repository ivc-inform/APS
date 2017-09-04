import sbtcrossproject.{CrossType, crossProject}

val _scalaVersion = "2.12.3"

lazy val aps = crossProject(JSPlatform, JVMPlatform)
  .settings(scalaVersion := _scalaVersion)
  .aggregate(webUI)
  .dependsOn(webUI)

lazy val apsJS = aps.js
lazy val apsJVM = aps.jvm

val sharedSettings = Seq(scalaVersion := _scalaVersion)

lazy val common = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .settings(sharedSettings)
  .jsSettings(/* ... */)
  .jvmSettings(/* ... */)

lazy val commonJS = common.js
lazy val commonJVM = common.jvm

lazy val webUI = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .settings(sharedSettings)
  .dependsOn(common)
  .jsSettings(/* ... */)
  .jvmSettings(/* ... */)

lazy val webUIJS = webUI.js
lazy val webUIJVM = webUI.jvm




