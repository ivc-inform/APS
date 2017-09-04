import sbt.addSbtPlugin

import sbt._

object PluginDeps {
    object versions {
        val pluginVersion = "0.3.3-SNAPSHOT"
        val snVersion = "0.3.2"
        val sjsVersion = "0.6.20"
    }

    val scalaJSPlugin = addSbtPlugin("org.scala-js" % "sbt-scalajs" % versions.sjsVersion)
    val scalaJSCrossProjectPlugin = addSbtPlugin("org.scala-native" % "sbt-scalajs-crossproject" % versions.pluginVersion)
    val scalaCrossProjectPlugin = addSbtPlugin("org.scala-native" % "sbt-crossproject" % versions.pluginVersion)
}

object CommonDeps {
    object versions {

    }
}
