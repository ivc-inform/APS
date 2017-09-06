import sbt.addSbtPlugin
import sbt._

object PluginDeps {
    object versions {
        val scalaCrossProjectPluginVersion = "0.3.3-SNAPSHOT"
        val scalaJSPluginVersion = "0.6.20"
        val mergeJSVersion = "1.0.12"
        val sbtNativePackagerVersion = "1.2.4"
        val sbtResolverVersion = "0.9.0.1"
    }

    val scalaJSPlugin = addSbtPlugin("org.scala-js" % "sbt-scalajs" % versions.scalaJSPluginVersion)
    val scalaJSCrossProjectPlugin = addSbtPlugin("org.scala-native" % "sbt-scalajs-crossproject" % versions.scalaCrossProjectPluginVersion)
    val scalaCrossProjectPlugin = addSbtPlugin("org.scala-native" % "sbt-crossproject" % versions.scalaCrossProjectPluginVersion)
    val mergeJS = addSbtPlugin("ru.simplesys" % "merge-js" % versions.mergeJSVersion)
    val sbtNativePackager = addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % versions.sbtNativePackagerVersion)
    val sbtResolver = addSbtPlugin("io.spray" % "sbt-revolver" % versions.sbtResolverVersion)
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
        val smartclientVersion = "11.1-v20170703.1"
        val fs2Version = "0.9.2"
        val tikaVersion = "1.16"
        val ssysCoreVersion = "1.4-SNAPSHOT"
        //val ssysCoreVersion = "1.4.0.2"
    }

    val akkaHttp = "com.typesafe.akka" %% "akka-http" % versions.akkaHttpVersion
    val akkaActor = "com.typesafe.akka" %% "akka-actor" % versions.akkaVersion
    val akkaStream = "com.typesafe.akka" %% "akka-stream" % versions.akkaVersion
    val scalaTags = "com.lihaoyi" %% "scalatags" % versions.scalaTagsVersion
    val ssysConfigWrapper = "com.simplesys.core" %% "config-wrapper" % versions.ssysCoreVersion
    val ssysCommon = "com.simplesys.core" %% "common" % versions.ssysCoreVersion
    val smartclient = "com.simplesys" % "smartclient-js" % versions.smartclientVersion
//    val tika = "org.apache.tika" % "tika-core" % versions.tikaVersion
//    val fs2Core = "co.fs2" %% "fs2-core" % versions.fs2Version
//    val fs2IO = "co.fs2" %% "fs2-io" % versions.fs2Version
}
