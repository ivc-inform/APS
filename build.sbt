import com.simplesys.jrebel.JRebelPlugin
import com.simplesys.jrebel.JRebelPlugin._
import com.simplesys.json.{JsonList, JsonObject}
import com.typesafe.sbt.packager.docker.DockerPlugin._
import ru.simplesys.eakd.sbtbuild.{CommonDeps, CommonDepsScalaJS, CommonSettings, PluginDeps}
import ru.simplesys.plugins.sourcegen.DevPlugin._
import sbt.Keys.version

name := CommonSettings.settingValues.name

lazy val root = (project in file(".")).
  aggregate(dbObjects, webUI, common).
  settings(
      inThisBuild(Seq(
          scalaVersion := CommonSettings.settingValues.scalaVersion,
          version := CommonSettings.settingValues.version,
          liquibaseUsername in DevConfig := "eakd",
          liquibasePassword in DevConfig := "eakd",
          liquibaseDriver in DevConfig := "oracle.jdbc.OracleDriver",
          liquibaseUrl in DevConfig := "jdbc:oracle:thin:@orapg.simplesys.lan:1521/test"

      )
        ++ CommonSettings.defaultSettings),
      publishArtifact in(Compile, packageBin) := false,
      publishArtifact in(Compile, packageDoc) := false,
      publishArtifact in(Compile, packageSrc) := false
  )

lazy val common = Project(id = "common", base = file("common")).settings(
    libraryDependencies ++= Seq(
        CommonDeps.commonsIO,
        CommonDeps.configWrapper,
        CommonDeps.ssysCommon,
        CommonDeps.scalaTest % Test
    )
)

lazy val testModule = Project(id = "test", base = file("test")).
  dependsOn(dbObjects).
  settings(
      libraryDependencies ++= Seq(
          CommonDeps.ssysJDBCWrapper,
          CommonDeps.scalaTest % Test
      )
  )

lazy val dbObjects = Project(id = "db-objects", base = file("db-objects")).
  dependsOn(common).
  enablePlugins(DevPlugin).
  settings(
      libraryDependencies ++= Seq(
          CommonDeps.ssysCoreLibrary,
          CommonDeps.ssysJDBCWrapper,
          CommonDeps.oraclePoolDataSources,
          CommonDeps.hikariPoolDataSources,
          CommonDeps.jdbcOracle12,
          CommonDeps.jdbcOracle12UCP,
          CommonDeps.jdbcOracleN18_12,
          CommonDeps.scalaTest % Test
      )
  ).settings(DevPlugin.devPluginGeneratorSettings).
  settings({
      import ru.simplesys.plugins.sourcegen.DevPlugin._
      Seq(
          sourceSchemaDir in DevConfig := (resourceDirectory in Compile).value / "defs",
          startPackageName in DevConfig := "ru.simplesys.defs",
          contextPath in DevConfig := "aps",
          maxArity := 254,
          quoted := true,
          sourceGenerators in Compile += (generateBoScalaCode in DevConfig)
      )
  })

lazy val webUI = Project(id = "web-ui", base = file("web-ui")).
  enablePlugins(
      DevPlugin, MergeWebappPlugin, SbtCoffeeScript, ScalaJSPlugin, JettyPlugin, WarPlugin, WebappPlugin, JRebelPlugin, DockerPlugin
  ).dependsOn(
    dbObjects
).aggregate(dbObjects).settings(

    addCommandAlias("debug-restart", "; jetty:stop ; clean ; fastOptJS ; package ; jetty:start"),
    addCommandAlias("reset", "; clean ; compile ; fastOptJS "),
    addCommandAlias("full-reset", "; clean ; package ; fastOptJS "),
    addCommandAlias("buildDockerImage", "; clean ; fastOptJS ; package; docker:buildImage"),
    addCommandAlias("buildAndPublishDockerImage", "; clean ; fastOptJS ; package; docker:publishToCloud"),

    JRebelPlugin.jrebelSettings,
    jrebel.webLinks += (sourceDirectory in Compile).value / "webapp",
    jrebel.enabled := true,

    javaOptions in Jetty ++= Seq(
        "-javaagent:/home/uandrew/jrebel/legacy/jrebel.jar",
        "-noverify",
        "-XX:+UseConcMarkSweepGC",
        "-XX:+CMSClassUnloadingEnabled"
    ),

    scalacOptions ++= (if (scalaJSVersion.startsWith("0.6.")) Seq("-P:scalajs:sjsDefinedByDefault")else Nil),

    libraryDependencies ++= Seq(
        CommonDeps.servletAPI % Provided,
        CommonDeps.ssysCommon,
        CommonDeps.ssysCommonWebapp,
        CommonDeps.ssysIscComponents,
        CommonDeps.ssysScalaIOExtender,
        CommonDeps.ssysXMLExtender,
        CommonDeps.ssysIscMisc,

        CommonDeps.smartclient,

        CommonDeps.commonsFileupload,
        CommonDeps.commonsIO,

        CommonDeps.scalaTest % Test,

        CommonDeps.scalaJSWrapper,
        CommonDeps.scalaTags,
        CommonDeps.scalaURI,
        CommonDepsScalaJS.smartClientWrapper.value,
        CommonDepsScalaJS.macroJS.value,
        CommonDepsScalaJS.scalaTags.value,
        CommonDepsScalaJS.jQuery.value,
        CommonDepsScalaJS.scalaDom.value

    )
).settings({
    import com.simplesys.mergewebapp.MergeWebappPlugin._
    import com.typesafe.sbt.coffeescript.SbtCoffeeScript.autoImport._
    import com.typesafe.sbt.web.Import.WebKeys._
    import com.typesafe.sbt.web.SbtWeb.autoImport._
    import ru.simplesys.plugins.sourcegen.DevPlugin._

    Seq(
        //scala.js
        crossTarget in fastOptJS := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponentsJS",
        crossTarget in fullOptJS := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponentsJS",
        crossTarget in packageJSDependencies := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponentsJS",

        //coffeeScript
        CoffeeScriptKeys.sourceMap := false,
        CoffeeScriptKeys.bare := false,
        sourceDirectory in Assets := (sourceDirectory in Compile).value / "webapp" / "coffeescript" / "developed" / "developedComponents",
        webTarget := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponents" / "coffeescript",
        (managedResources in Compile) ++= CoffeeScriptKeys.coffeeScript.value,

        //dev plugin
        sourceSchemaDir in DevConfig := (resourceDirectory in(dbObjects, Compile)).value / "defs",
        startPackageName in DevConfig := "ru.simplesys.defs",
        contextPath in DevConfig := "aps",
        maxArity in DevConfig := 254,
        quoted in DevConfig := true,
        sourceGenerators in Compile += (generateScalaCode in DevConfig),

        //merger
        mergeMapping in MergeWebappConfig := Seq(
            ("com.simplesys.core", "common-webapp") -> Seq(
                Seq("webapp", "javascript", "generated", "generatedComponents", "coffeescript") -> Some(Seq("webapp", "managed", "javascript", "common-webapp", "generated", "generatedComponents", "coffeescript")),
                Seq("webapp", "javascript", "developed") -> Some(Seq("webapp", "managed", "javascript", "common-webapp", "developed")),
                Seq("webapp", "coffeescript", "developed") -> Some(Seq("webapp", "managed", "coffeescript", "common-webapp", "developed")),
                Seq("webapp", "css") -> Some(Seq("webapp", "managed", "css", "common-webapp")),
                Seq("webapp", "html") -> Some(Seq("webapp", "managed", "html", "common-webapp")),
                Seq("webapp", "images") -> Some(Seq("webapp", "managed", "images", "common-webapp"))
            ),
            ("com.simplesys.core", "isc-components") -> Seq(
                Seq("webapp", "javascript", "generated", "generatedComponents") -> Some(Seq("webapp", "managed", "javascript", "isc-components", "generated", "generatedComponents")),
                Seq("webapp", "javascript", "generated", "generatedComponents", "coffeescript") -> Some(Seq("webapp", "managed", "javascript", "isc-components", "generated", "generatedComponents", "coffeescript")),
                Seq("javascript", "com", "simplesys") -> Some(Seq("webapp", "managed", "javascript", "isc-components", "developed", "developedComponents")),
                Seq("coffeescript") -> Some(Seq("webapp", "managed", "coffeescript", "isc-components", "developed", "developedComponents"))
            ),
            ("com.simplesys.core", "isc-misc") -> Seq(
                Seq("javascript") -> Some(Seq("webapp", "managed", "javascript", "isc-misc"))
            ),
            ("com.simplesys", "smartclient-js") -> Seq(
                Seq("isomorphic") -> Some(Seq("webapp", "isomorphic"))
            )
        ),
        currentProjectGenerationDirPath in MergeWebappConfig := (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponents",
        currentProjectDevelopedDirPath in MergeWebappConfig := (sourceDirectory in Compile).value / "webapp" / "javascript" / "developed",
        currentProjectCoffeeDevelopedDirPath in MergeWebappConfig := (sourceDirectory in Compile).value / "webapp" / "coffeescript" / "developed",
        merge in MergeWebappConfig := (merge in MergeWebappConfig).dependsOn(CoffeeScriptKeys.coffeeScript in Assets).value,

        //xsbtWeb
        containerPort := 8084,
        containerArgs := Seq("--path", "/aps"),
        containerLibs in Jetty := Seq(
            CommonDeps.jettyRuner intransitive()
        ),
        artifactName := { (v: ScalaVersion, m: ModuleID, a: Artifact) =>
            a.name + "." + a.extension
        },
        webappWebInfClasses := true,

        defaultLinuxInstallLocation in Docker := "",
        dockerBaseImage := "ivcinform/jetty:9.4.6.v20170531",
        daemonUser in Docker := "",
        daemonGroup in Docker := "",
        dockerDocfileCommands := Seq(),
        dockerEntrypoint := Seq(),
        dockerCmd := Seq(),
        dockerExposedPorts in Docker := Seq(8080),

        //docker
        version := CommonSettings.settingValues.version,
        packageName in Docker := CommonSettings.settingValues.name,
        dockerUsername in Docker := None,
        dockerRepository in Docker := Some("hub.docker.com"),
        dockerRepository := Some("ivcinform"),
        dockerUpdateLatest := false,
        dockerAlias in Docker := DockerAlias(dockerRepository.value, (dockerUsername in Docker).value, CommonSettings.settingValues.name, Some(CommonSettings.settingValues.version)),
        dockerDocfileCommands := Seq(
            copy(s"webapp/", s"/var/lib/jetty/webapps/${CommonSettings.settingValues.name}"),
            entrypoint("/docker-entrypoint.sh")
        ),
        (resourceGenerators in Compile) += task[Seq[File]] {

            val aboutFile: File = (sourceDirectory in Compile).value / "webapp" / "javascript" / "generated" / "generatedComponents" / "MakeAboutData.js"

            val list = JsonList()

            import scala.reflect.ClassTag
            import scala.reflect.runtime.universe._
            import scala.reflect.runtime.{universe ⇒ ru}

            def makeVersionList[T: TypeTag : ClassTag](e: T): Unit = {

                val classLoaderMirror = ru.runtimeMirror(this.getClass.getClassLoader)
                val `type`: ru.Type = ru.typeOf[T]

                val classSymbol = `type`.typeSymbol.asClass

                val decls = `type`.declarations.sorted.filter(_.isMethod).filter(!_.name.toString.contains("<init>"))
                val im = classLoaderMirror reflect e

                decls.foreach {
                    item =>

                        val shippingTermSymb = `type`.declaration(ru.newTermName(item.name.toString)).asTerm
                        val shippingFieldMirror = im reflectField shippingTermSymb
                        val res = shippingFieldMirror.get.toString()

                        list += JsonObject("libName" -> item.name.toString, "libVersion" -> res)
                }
            }

            list ++= Seq(
                JsonObject("libName" -> "Разработка :", "libVersion" -> "АО ИВЦ \"Информ\" (info@ivc-inform.ru)"),
                JsonObject("libName" -> "Версия :", "libVersion" -> version.value)
            )

            makeVersionList(CommonDeps.versions)
            makeVersionList(PluginDeps.versions)

            IO.write(aboutFile, s"simpleSyS.aboutData = ${list.toPrettyString}")
            Seq()
        }

    )
},
    skip in packageJSDependencies := false,
    jsDependencies += "org.webjars" % "jquery" % "3.2.1" / "3.2.1/jquery.js"
)
