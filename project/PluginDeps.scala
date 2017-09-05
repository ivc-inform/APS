import sbt.addSbtPlugin
import sbt._

object PluginDeps {
    object Versions {
        val scalaCrossProjectPluginVersion = "0.3.3-SNAPSHOT"
        val scalaJSPluginVersion = "0.6.20"
    }

    val scalaJSPlugin = addSbtPlugin("org.scala-js" % "sbt-scalajs" % Versions.scalaJSPluginVersion)
    val scalaJSCrossProjectPlugin = addSbtPlugin("org.scala-native" % "sbt-scalajs-crossproject" % Versions.scalaCrossProjectPluginVersion)
    val scalaCrossProjectPlugin = addSbtPlugin("org.scala-native" % "sbt-crossproject" % Versions.scalaCrossProjectPluginVersion)
}

object CommonDeps {
    object versions {
        val scalaJSVersion = "1.4.0.1"
        val scalaTestVersion = "3.0.3"
        val scalaTagsVersion = "0.6.5"
        val uPickleVersion = "0.4.4"
        val scalajsDOMVersion = "0.9.3"
        val scalajsJQueryVersion = "0.9.2"
        val akkaVersion = "2.5.4"
        val akkaHttpVersion = "10.0.10"

    }

    val akkaHttp = "com.typesafe.akka" %% "akka-http" % versions.akkaHttpVersion
    val akkaActor = "com.typesafe.akka" %% "akka-actor" % versions.akkaVersion
    val akkaStream = "com.typesafe.akka" %% "akka-stream" % versions.akkaVersion
    val scalaTags = "com.lihaoyi" %% "scalatags" % versions.scalaTagsVersion
}
