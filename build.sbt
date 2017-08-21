import com.ivc_inform.build
import sbt.{Credentials, Path}
import org.scalajs.sbtplugin.ScalaJSPlugin.AutoImport.crossProject

name := "auto planing sheduler"

lazy val root = (project in file(".")).
  aggregate(fooJS, fooJVM).
  settings(inThisBuild(Seq(
      git.baseVersion := CommonSettings.settingValues.baseVersion,
      scalaVersion := CommonSettings.settingValues.scalaVersion,
      scalacOptions := CommonSettings.settingValues.scalacOptions,
      organization := CommonSettings.settingValues.organization,
      publishTo := {
          val corporateRepo = "http://toucan.simplesys.lan/"
          if (isSnapshot.value)
              Some("snapshots" at corporateRepo + "artifactory/libs-snapshot-local")
          else
              Some("releases" at corporateRepo + "artifactory/libs-release-local")
      },
      credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
  )
    ++ CommonSettings.defaultSettings),
      publishArtifact in(Compile, packageBin) := false,
      publishArtifact in(Compile, packageDoc) := false,
      publishArtifact in(Compile, packageSrc) := false
  )

lazy val foo = crossProject.in(file(".")).
  settings(
      name := "foo",
      version := "0.1-SNAPSHOT"
  ).
  jvmSettings(
      // Add JVM-specific settings here
  ).
  jsSettings(
      // Add JS-specific settings here
  )

lazy val fooJVM = foo.jvm
lazy val fooJS = foo.js
