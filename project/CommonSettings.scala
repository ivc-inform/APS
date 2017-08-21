package com.ivcInform.build

object CommonSettings {
    object settingValues {
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
        val scalaTestVersion = "3.0.1"
        //val sbtssysCoreVersion = "1.3.1"
        val ssysCoreVersion = "1.4-SNAPSHOT"
    }


    val defaultSettings = {
        import sbt.Keys._
        Seq(
            scalacOptions := settingValues.scalacOptions,
            organization := settingValues.organization
        )
    }
}
