import sbtcrossproject.{CrossType, crossProject}
import com.simplesys.jrebel.JRebelPlugin
import com.simplesys.jrebel.JRebelPlugin._
import com.typesafe.sbt.packager.docker.DockerPlugin._
import ru.simplesys.plugins.sourcegen.DevPlugin._
import sbt.Keys.version
import com.simplesys.mergewebapp.MergeWebappPlugin._
import com.typesafe.sbt.coffeescript.SbtCoffeeScript.autoImport._
import com.typesafe.sbt.web.Import.WebKeys._
import com.typesafe.sbt.web.SbtWeb.autoImport._
import ru.simplesys.plugins.sourcegen.DevPlugin._

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
  .aggregate(common, dbObjects, testModule, webUI)
  .dependsOn(common, dbObjects, testModule, webUI)

val commonJSSettings = Seq(
    crossTarget in fastOptJS := (sourceDirectory in Compile).value / ".." / ".." / ".." / "src" / "main" / "webapp" / "javascriptJS",
    crossTarget in fullOptJS := (sourceDirectory in Compile).value / ".." / ".." / ".." / "src" / "main" / "webapp" / "javascriptJS",
    crossTarget in packageJSDependencies := (sourceDirectory in Compile).value / ".." / ".." / ".." / "src" / "main" / "webapp" / "javascriptJS",
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
  .dependsOn(common)
  .jvmConfigure(_ enablePlugins DevPlugin)
  .jvmSettings(
      DevPlugin.devPluginGeneratorSettings,
      libraryDependencies ++= Seq(
          CommonDeps.ssysCoreLibrary,
          CommonDeps.ssysJDBCWrapper,
          CommonDeps.oraclePoolDataSources,
          CommonDeps.hikariPoolDataSources,
          CommonDeps.jdbcOracle12,
          CommonDeps.jdbcOracle12UCP,
          CommonDeps.jdbcOracleN18_12
      ),
      sourceSchemaDir in DevConfig := (resourceDirectory in Compile).value / "defs",
      startPackageName in DevConfig := "ru.simplesys.defs",
      contextPath in DevConfig := "aps",
      maxArity := 254,
      quoted := true,
      sourceGenerators in Compile += (generateBoScalaCode in DevConfig)
  )
  .jvmSettings(commonJVMSettings)
  .jsSettings(commonJSSettings)

lazy val dbObjectsJS = dbObjects.js
lazy val dbObjectsJVM = dbObjects.jvm

lazy val testModule = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .settings(CommonSettings.noPublishSettings)
  .settings(
      name := "test"
  )
  .dependsOn(dbObjects)
  .settings(
      libraryDependencies ++= Seq(
          //CommonDeps.ssysJDBCWrapper,
          CommonDeps.scalaTest % Test
      )
  )
  .jvmSettings(commonJVMSettings)
  .jsSettings(commonJSSettings)

lazy val testModuleJS = testModule.js
lazy val testModuleJVM = testModule.jvm

val prefixPath = Seq("..", "..", "..", "src", "main")

lazy val webUI = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .settings(CommonSettings.noPublishSettings)
  .settings(
      name := "web-ui"
  )
  .dependsOn(dbObjects)
  .aggregate(dbObjects)
  .settings(CommonSettings.defaultSettings)
  .jvmConfigure(_ enablePlugins(DevPlugin, MergeWebappPlugin, SbtCoffeeScript, ScalaJSPlugin, JettyPlugin, WarPlugin, WebappPlugin, JRebelPlugin, DockerPlugin, JavaAppPackaging))
  .settings(
      addCommandAlias("debug-restart", "; jetty:stop ; fastOptJS ; package ; jetty:start"),
      addCommandAlias("reset", "; clean ; compile ; fastOptJS "),
      addCommandAlias("full-reset", "; clean ; package ; fastOptJS "),
      addCommandAlias("buildDockerImage", "; clean ; fastOptJS ; package; docker:buildImage"),
      addCommandAlias("buildAndPublishDockerImage", "; clean ; fastOptJS ; package; docker:publishToCloud"),
      libraryDependencies ++= Seq(
          //CommonDeps.ssysJDBCWrapper,
          CommonDeps.scalaTest % Test
      )
  )
  .jvmSettings(

      JRebelPlugin.jrebelSettings,
      jrebel.webLinks += (sourceDirectory in Compile).value / "webapp",
      jrebel.enabled := true,

      javaOptions in Jetty ++= Seq(
          "-javaagent:jrebel/jrebel.jar",
          "-noverify",
          "-XX:+UseConcMarkSweepGC",
          "-XX:+CMSClassUnloadingEnabled"
      ),

      libraryDependencies ++= Seq(
          CommonDeps.servletAPI % Provided,
          CommonDeps.ssysIscComponents,
          CommonDeps.ssysScalaIOExtender,
          CommonDeps.ssysXMLExtender,
          CommonDeps.ssysIscMisc,
          CommonDeps.circeExtender,
          CommonDeps.ssysServletWrapper,
          CommonDeps.ssysCommonWebapp,

          CommonDeps.smartclient,

          CommonDeps.commonsFileupload,
          CommonDeps.commonsIO,

          CommonDeps.scalaTest % Test,

          CommonDeps.scalaJSWrapper,
          CommonDeps.scalaTags,
          CommonDeps.scalaURI,

          CommonDeps.jsgantImproved
      )
  )
  .jvmSettings(

      //coffeeScript
      CoffeeScriptKeys.sourceMap := false,
      CoffeeScriptKeys.bare := false,
      sourceDirectory in Assets := (sourceDirectory in Compile).value / ".." / ".." / ".." / "src" / "main" / "webapp" / "coffeescript" / "developed" / "developedComponents",
      webTarget := (sourceDirectory in Compile).value / ".." / ".." / ".." / "src" / "main" / "webapp" / "javascript" / "generated" / "generatedComponents" / "coffeescript",
      (managedResources in Compile) ++= CoffeeScriptKeys.coffeeScript.value,

      //dev plugin
      sourceSchemaDir in DevConfig := (resourceDirectory in(dbObjectsJVM, Compile)).value / "defs",
      startPackageName in DevConfig := "ru.simplesys.defs",
      contextPath in DevConfig := "aps",
      maxArity in DevConfig := 254,
      quoted in DevConfig := true,
      sourceGenerators in Compile += (generateScalaCode in DevConfig),

      //merger
      mergeMapping in MergeWebappConfig := Seq(
          ("com.simplesys.core", "common-webapp") -> Seq(
              Seq("webapp", "javascript", "generated", "generatedComponents", "coffeescript") -> Some(prefixPath ++ Seq("webapp", "managed", "javascript", "common-webapp", "generated", "generatedComponents", "coffeescript")),
              Seq("webapp", "javascript", "developed") -> Some(prefixPath ++ Seq("webapp", "managed", "javascript", "common-webapp", "developed")),
              Seq("webapp", "coffeescript", "developed") -> Some(prefixPath ++ Seq("webapp", "managed", "coffeescript", "common-webapp", "developed")),
              Seq("webapp", "css") -> Some(prefixPath ++ Seq("webapp", "managed", "css", "common-webapp")),
              Seq("webapp", "html") -> Some(prefixPath ++ Seq("webapp", "managed", "html", "common-webapp")),
              Seq("webapp", "images") -> Some(prefixPath ++ Seq("webapp", "managed", "images", "common-webapp"))
          ),
          ("com.simplesys.core", "isc-components") -> Seq(
              Seq("webapp", "javascript", "generated", "generatedComponents") -> Some(prefixPath ++ Seq("webapp", "managed", "javascript", "isc-components", "generated", "generatedComponents")),
              Seq("webapp", "javascript", "generated", "generatedComponents", "coffeescript") -> Some(prefixPath ++ Seq("webapp", "managed", "javascript", "isc-components", "generated", "generatedComponents", "coffeescript")),
              Seq("javascript", "com", "simplesys") -> Some(prefixPath ++ Seq("webapp", "managed", "javascript", "isc-components", "developed", "developedComponents")),
              Seq("coffeescript") -> Some(prefixPath ++ Seq("webapp", "managed", "coffeescript", "isc-components", "developed", "developedComponents"))
          ),
          ("com.simplesys.core", "isc-misc") -> Seq(
              Seq("javascript") -> Some(prefixPath ++ Seq("webapp", "managed", "javascript", "isc-misc"))
          ),
          ("com.simplesys", "jsgantt-improved") -> Seq(
              Seq("javascript") -> Some(prefixPath ++ Seq("webapp", "managed", "javascript", "jsgantt-improved")),
              Seq("css") -> Some(prefixPath ++ Seq("webapp", "managed", "css", "jsgantt-improved"))
          ),
          ("com.simplesys", "smartclient-js") -> Seq(
              Seq("isomorphic") -> Some(prefixPath ++ Seq("webapp", "isomorphic"))
          )
      ),
      webAppDirPath in MergeWebappConfig := (sourceDirectory in Compile).value,
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
      dockerBaseImage := "ivcinform/jetty:9.4.7.v20170914",
      daemonUser in Docker := "",
      daemonGroup in Docker := "",
      dockerDocfileCommands := Seq(),
      dockerEntrypoint := Seq(),
      dockerCmd := Seq(),
      dockerExposedPorts in Docker := Seq(8080),

      //docker
      packageName in Docker := CommonSettings.settingValues.name,
      dockerUsername in Docker := None,
      dockerRepository in Docker := Some("hub.docker.com"),
      dockerRepository := Some("ivcinform"),
      dockerUpdateLatest := false,
      dockerAlias in Docker := DockerAlias(dockerRepository.value, (dockerUsername in Docker).value, CommonSettings.settingValues.name, Some(version.value)),
      dockerDocfileCommands := Seq(
          copy(s"webapp/", s"/var/lib/jetty/webapps/${CommonSettings.settingValues.name}"),
          entrypoint("/docker-entrypoint.sh"),
      ),
      (resourceGenerators in Compile) += task[Seq[File]] {

          val aboutFile: File = (sourceDirectory in Compile).value / ".." / ".." / ".." / "src" / "main" / "webapp" / "javascript" / "generated" / "generatedComponents" / "MakeAboutData.js"

          import scala.reflect.ClassTag
          import scala.reflect.runtime.universe._
          import scala.reflect.runtime.{universe ⇒ ru}

          def makeVersionList[T: TypeTag : ClassTag](e: T): Unit = {

              val classLoaderMirror = ru.runtimeMirror(this.getClass.getClassLoader)
              val `type`: ru.Type = ru.typeOf[T]

              val classSymbol = `type`.typeSymbol.asClass

              val decls = `type`.declarations.sorted.filter(_.isMethod).filter(!_.name.toString.contains("<init>"))
              val im = classLoaderMirror reflect e

              Common.list append (decls.map {
                  item =>
                      val shippingTermSymb = `type`.declaration(ru.newTermName(item.name.toString)).asTerm
                      val shippingFieldMirror = im reflectField shippingTermSymb
                      val res = shippingFieldMirror.get.toString()

                      Info(item.name.toString, res)
              }: _ *)
          }

          Common.list append (Seq(
              Info("Разработка :", "АО ИВЦ \"Информ\" (info@ivc-inform.ru)"),
              Info("Версия :", version.value)
          ): _*)

          makeVersionList(CommonDeps.versions)
          makeVersionList(PluginDeps.versions)

          IO.write(aboutFile, s"simpleSyS.aboutData = ${Common.spaces2}")
          Seq()
      },

      skip in packageJSDependencies := false
  )
  .jvmSettings(commonJVMSettings)
  .jsConfigure(_ enablePlugins (DevPlugin))
  .jsSettings(
      scalacOptions ++= (if (scalaJSVersion.startsWith("0.6.")) Seq("-P:scalajs:sjsDefinedByDefault") else Nil),
      skip in packageJSDependencies := false,
      jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),

      //dev plugin
      sourceSchemaDir in DevConfig := (resourceDirectory in(dbObjectsJVM, Compile)).value / "defs",
      startPackageName in DevConfig := "ru.simplesys.defs",
      contextPath in DevConfig := "aps",
      maxArity in DevConfig := 254,
      quoted in DevConfig := true,
      sourceGenerators in Compile += (generateScalaJSCode in DevConfig),

      //scala.js
      crossTarget in fastOptJS := (sourceDirectory in Compile).value  / ".." / ".." / ".." / "src" / "main" / "webapp" / "javascript" / "generated" / "generatedComponentsJS",
      crossTarget in fullOptJS := (sourceDirectory in Compile).value  / ".." / ".." / ".." / "src" / "main" / "webapp" / "javascript" / "generated" / "generatedComponentsJS",
      crossTarget in packageJSDependencies := (sourceDirectory in Compile).value  / ".." / ".." / ".." / "src" / "main" / "webapp" / "webapp" / "javascript" / "generated" / "generatedComponentsJS",

      libraryDependencies ++= Seq(
          CommonDepsScalaJS.jsgantImproved.value,

          CommonDepsScalaJS.smartClientWrapper.value,
          CommonDepsScalaJS.scalaTags.value,
          CommonDepsScalaJS.jQuery.value,
          CommonDepsScalaJS.scalaDom.value,

          CommonDepsScalaJS.circeCore.value,
          CommonDepsScalaJS.circeGeneric.value,
          CommonDepsScalaJS.circeParser.value
      )
  )
  .jsSettings(commonJSSettings)

lazy val webUIJS = webUI.js
lazy val webUIJVM = webUI.jvm





