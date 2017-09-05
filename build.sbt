import sbtcrossproject.{CrossType, crossProject}

val _scalaVersion = "2.12.3"

lazy val aps = crossProject(JSPlatform, JVMPlatform)
  .settings(scalaVersion := _scalaVersion)
  .aggregate(webUI)
  .dependsOn(webUI)

lazy val apsJS = aps.js
lazy val apsJVM = aps.jvm

val sharedSettings = Seq(
    scalaVersion := _scalaVersion,
    scalacOptions += "-P:scalajs:sjsDefinedByDefault"
)

lazy val common = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .settings(sharedSettings)
  .jsSettings(
      //scalacOptions ++= (if (scalaJSVersion.startsWith("0.6.")) Seq("-P:scalajs:sjsDefinedByDefault") else Nil)
  )
  .jvmSettings(
      libraryDependencies ++= Seq(
          CommonDeps.ssysConfigWrapper
      )
  )

lazy val commonJS = common.js
lazy val commonJVM = common.jvm

lazy val webUI = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .dependsOn(common)
  .jsSettings(
      libraryDependencies ++= Seq(
          CommonDepsScalaJS.smartClientWrapper.value,
          CommonDepsScalaJS.scalaTags.value,
          CommonDepsScalaJS.macroJS.value
      )
  )
  .jvmSettings(
      libraryDependencies ++= Seq(
          CommonDeps.akkaActor,
          CommonDeps.akkaStream,
          CommonDeps.akkaHttp
      )
  )

lazy val webUIJS = webUI.js
lazy val webUIJVM = webUI.jvm




