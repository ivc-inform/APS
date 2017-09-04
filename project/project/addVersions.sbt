unmanagedSources in Compile ++= {
  val rootProjDir = baseDirectory.value.getParentFile
  Seq(
    rootProjDir / "CommonDeps.scala"
  )
}
