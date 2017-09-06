import sbtcrossproject.{CrossType, crossProject}
import spray.revolver.RevolverPlugin.autoImport._

lazy val aps = crossProject(JSPlatform, JVMPlatform)
  .settings(
      inThisBuild(Seq(
          scalaVersion := CommonSettings.settingValues.scalaVersion,
          version := CommonSettings.settingValues.version
      ) ++ CommonSettings.defaultSettings),
      publishArtifact in(Compile, packageBin) := false,
      publishArtifact in(Compile, packageDoc) := false,
      publishArtifact in(Compile, packageSrc) := false
  )
  .aggregate(webUI)
  .dependsOn(webUI)

lazy val apsJS = aps.js
lazy val apsJVM = aps.jvm


val commonJsSettings = Seq(
    scalacOptions ++= (if (scalaJSVersion.startsWith("0.6.")) Seq("-P:scalajs:sjsDefinedByDefault") else Nil)
)

lazy val common = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .jsSettings(
      commonJsSettings
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
  .settings(

      libraryDependencies ++= Seq(
          CommonDeps.ssysCommon,
          CommonDeps.scalaTags
      )
  )
  .jsSettings(
      commonJsSettings,

      //scala.js
      crossTarget in fastOptJS := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponentsJS",
      crossTarget in fullOptJS := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponentsJS",
      crossTarget in packageJSDependencies := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponentsJS",

      libraryDependencies ++= Seq(
          CommonDepsScalaJS.smartClientWrapper.value,
          CommonDepsScalaJS.scalaTags.value,
          CommonDepsScalaJS.macroJS.value
      )
  )
  .jvmSettings(
      reJRebelJar :=  "/home/uandrew/jrebel/legacy/jrebel.jar",
      
      javaOptions in reStart ++= Seq(
          "-noverify",
          "-Xmx2g",
          "-XX:+UseConcMarkSweepGC",
          "-XX:+CMSClassUnloadingEnabled"
      ),

      libraryDependencies ++= Seq(
          CommonDeps.akkaActor,
          CommonDeps.akkaStream,
          CommonDeps.akkaHttp
      )
  )

lazy val webUIJS = webUI.js
lazy val webUIJVM = webUI.jvm




