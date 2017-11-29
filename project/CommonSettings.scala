package ru.simplesys.eakd.sbtbuild

import sbt.Setting

object CommonSettings {
  object settingValues {

    val scalaVersion = "2.12.4"
    val organization = "com.ivc-inform"
    val name = "aps"
    val baseVersion = "1.0"
    val scalacOptions = Seq(
      "-feature",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-language:existentials",
      "-language:postfixOps",
      "-deprecation",
      "-unchecked")
  }

  val defaultSettings = {
    import sbt.Keys._
    Seq(
      scalacOptions := settingValues.scalacOptions,
      organization := settingValues.organization
    )
  }
}
