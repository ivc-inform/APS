import sbt.Keys._
import sbt._
import sbt.{Credentials, Path}

object CommonSettings {
    object settingValues {

        val name = "aps"
        val baseVersion = "1.0"
        val scalaVersion = "2.12.5"
        val organization = "com.simplesys"
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

    val publishSettings = inThisBuild(Seq(
        publishMavenStyle := true,
        publishTo := {
            val corporateRepo = "http://toucan.simplesys.lan/"

            if (version.value.endsWith("-SNAPSHOT"))
                Some("snapshots" at corporateRepo + "artifactory/libs-snapshot-local")
            else
                Some("releases" at corporateRepo + "artifactory/libs-release-local")
        },
        credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
    ))

    val noPublishSettings = inThisBuild(Seq(
        publishArtifact := false,
        packagedArtifacts := Map.empty,
        publish := {},
        publishLocal := {}
    ))
}
