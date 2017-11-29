import sbtcrossproject.{CrossType, crossProject}

lazy val root = crossProject(JSPlatform, JVMPlatform)
  .enablePlugins(GitVersioning)
  .settings(CommonSettings.noPublishSettings)
  .settings(
      inThisBuild(Seq(
          git.baseVersion := CommonSettings.settingValues.baseVersion,
          scalaVersion := CommonSettings.settingValues.scalaVersion,
          scalacOptions := CommonSettings.settingValues.scalacOptions,
          organization := CommonSettings.settingValues.organization,
          name := CommonSettings.settingValues.name
      ) ++ CommonSettings.defaultSettings)
  )
  .aggregate(common, dbObjects)
  .dependsOn(common, dbObjects)

val commonJSSettings = Seq(
    crossTarget in fastOptJS := (sourceDirectory in Compile).value / "javascriptJS",
    crossTarget in fullOptJS := (sourceDirectory in Compile).value / "javascriptJS",
    crossTarget in packageJSDependencies := (sourceDirectory in Compile).value / "javascriptJS",
    libraryDependencies ++= Seq(
        "org.scalatest" %%% "scalatest" % "3.0.4" % Test
    ),
    scalacOptions ++= {
        if (scalaJSVersion.startsWith("0.6."))
            Seq("-P:scalajs:sjsDefinedByDefault")
        else
            Nil
    }
)

val commonJVMSettings = Seq(
    libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "3.0.4" % Test
    )
)

lazy val common = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .settings(CommonSettings.noPublishSettings)
  .settings(
      name := "common"
  )
  .settings(CommonSettings.defaultSettings)
  .jvmSettings(commonJVMSettings)
  .jsSettings(commonJSSettings)

lazy val noNameJS = common.js
lazy val noNameJVM = common.jvm

lazy val dbObjects = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .settings(CommonSettings.noPublishSettings)
  .settings(
      name := "db-objects"
  )
  .settings(CommonSettings.defaultSettings)
  .aggregate(common)
  .dependsOn(common)
  .jvmConfigure(_ enablePlugins DevPlugin)
  .jvmSettings(DevPlugin.devPluginGeneratorSettings)
  .jvmSettings(
      {
          import ru.simplesys.plugins.sourcegen.DevPlugin._
          Seq(
              sourceSchemaDir in DevConfig := (resourceDirectory in Compile).value / "defs",
              startPackageName in DevConfig := "ru.simplesys.defs",
              contextPath in DevConfig := "aps",
              maxArity := 254,
              quoted := true,
              sourceGenerators in Compile += (generateBoScalaCode in DevConfig)
          )
      }
  )
  .jvmSettings(commonJVMSettings)
  .jvmSettings(
      libraryDependencies ++= Seq(
          CommonDeps.ssysCoreLibrary,
          CommonDeps.ssysJDBCWrapper,
          CommonDeps.oraclePoolDataSources,
          CommonDeps.hikariPoolDataSources,
          CommonDeps.jdbcOracle12,
          CommonDeps.jdbcOracle12UCP,
          CommonDeps.jdbcOracleN18_12
      )
  )
  .jsSettings(commonJSSettings)

lazy val dbObjectsJS = dbObjects.js
lazy val dbObjectsJVM = dbObjects.jvm





