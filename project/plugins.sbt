val pluginVersion = "0.3.3-SNAPSHOT"
val snVersion     = "0.3.2"
val sjsVersion    = "0.6.20"

addSbtPlugin("org.scala-js"     % "sbt-scalajs"              % sjsVersion)
addSbtPlugin("org.scala-native" % "sbt-scalajs-crossproject" % pluginVersion)
addSbtPlugin("org.scala-native" % "sbt-crossproject"         % pluginVersion)

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-feature",
  "-encoding",
  "utf8"
)
