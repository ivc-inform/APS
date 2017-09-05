unmanagedSources in Compile ++= {
  val rootProjDir = baseDirectory.value.getParentFile
  Seq(
    rootProjDir / "PluginDeps.scala"
  )
}
