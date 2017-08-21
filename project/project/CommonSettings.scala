package com.ivc_inform.build

object CommonSettings {
    object settingValues {
        val baseVersion = "1.4"

        val scalaVersion = "2.12.3"
        val organization = "com.simplesys"
        val scalacOptions = Seq(
            "-feature",
            "-language:higherKinds",
            "-language:implicitConversions",
            "-language:postfixOps",
            "-deprecation",
            "-unchecked")
    }

    object versions {
        val ssysCoreVersion = "1.4-SNAPSHOT"
        val scalaTestVersion = "3.0.1"
    }


    val defaultSettings = {
        import sbt.Keys._
        Seq(
            scalacOptions := settingValues.scalacOptions,
            organization := settingValues.organization
        )
    }
}
