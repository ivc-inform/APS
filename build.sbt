//import sbt.{Credentials, Path}
//import org.scalajs.sbtplugin.ScalaJSPlugin.AutoImport.crossProject
//
//name := "auto planing sheduler"
//
//lazy val root = project.in(file(".")).
//  aggregate(fooJS, fooJVM).
//  settings(
//      scalaVersion := "2.12.3",
//      /*publishTo := {
//          val corporateRepo = "http://toucan.simplesys.lan/"
//          if (isSnapshot.value)
//              Some("snapshots" at corporateRepo + "artifactory/libs-snapshot-local")
//          else
//              Some("releases" at corporateRepo + "artifactory/libs-release-local")
//      },*/
//      credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
//      publishLocal := {}
//  )
//
//lazy val foo = crossProject.in(file(".")).
//  settings(
//      name := "foo",
//      version := "0.1-SNAPSHOT"
//  ).
//  jvmSettings(
//      // Add JVM-specific settings here
//  ).
//  jsSettings(
//      // Add JS-specific settings here
//  )
//
//lazy val fooJVM = foo.jvm
//lazy val fooJS = foo.js
