import com.simplesys.mergewebapp.MergeWebappPlugin._
import sbtcrossproject.{CrossType, crossProject}
import spray.revolver.RevolverPlugin.autoImport._

lazy val aps = crossProject(JSPlatform, JVMPlatform)
  .settings(
      inThisBuild(Seq(
          scalaVersion := CommonSettings.settingValues.scalaVersion,
          version := CommonSettings.settingValues.version
      ) ++ CommonSettings.defaultSettings),
      publishArtifact in(Compile, packageBin) := false,
      publishArtifact in(Compile, packageDoc) := false,
      publishArtifact in(Compile, packageSrc) := false
  )
  .aggregate(webUI)
  .dependsOn(webUI)

lazy val apsJS = aps.js
lazy val apsJVM = aps.jvm

val commonJsSettings = Seq(
    scalacOptions ++= (if (scalaJSVersion.startsWith("0.6.")) Seq("-P:scalajs:sjsDefinedByDefault") else Nil)
)

lazy val common = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .jsSettings(
      commonJsSettings
  )
  .jvmSettings(
      libraryDependencies ++= Seq(
          CommonDeps.ssysConfigWrapper
      )
  )

lazy val commonJS = common.js
lazy val commonJVM = common.jvm

lazy val webUI = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .dependsOn(common)
  .settings(
      libraryDependencies ++= Seq(
          CommonDeps.ssysCommon,
          CommonDeps.scalaTags,
          CommonDeps.circeCore,
          CommonDeps.circeParser,
          CommonDeps.circeGeneric
      )
  )
  .jsSettings(
      commonJsSettings,

      //scala.js
      crossTarget in fastOptJS := baseDirectory.value.getParentFile / "webapp" / "javascript" / "generated" / "generatedComponentsJS",
      crossTarget in fullOptJS := baseDirectory.value.getParentFile / "webapp" / "javascript" / "generated" / "generatedComponentsJS",
      crossTarget in packageJSDependencies := baseDirectory.value.getParentFile / "webapp" / "javascript" / "generated" / "generatedComponentsJS",

      //merger
      mergeMapping in MergeWebappConfig := Seq(
          ("com.simplesys.core", "common-webapp") -> Seq(
              Seq("webapp", "javascript", "generated", "generatedComponents", "coffeescript") -> Some(Seq("managed", "javascript", "common-webapp", "generated", "generatedComponents", "coffeescript")),
              Seq("webapp", "javascript", "developed") -> Some(Seq("managed", "javascript", "common-webapp", "developed")),
              Seq("webapp", "coffeescript", "developed") -> Some(Seq("managed", "coffeescript", "common-webapp", "developed")),
              Seq("webapp", "css") -> Some(Seq("managed", "css", "common-webapp")),
              Seq("webapp", "images") -> Some(Seq("managed", "images", "common-webapp"))
          ),
          ("com.simplesys", "smartclient-js") -> Seq(
              Seq("isomorphic") -> Some(Seq("isomorphic"))
          )
      ),
      webAppDirPath in MergeWebappConfig := baseDirectory.value.getParentFile / "webapp",

      MergeWebappPlugin.mergeWebappSettings,
      libraryDependencies ++= Seq(
          CommonDepsScalaJS.smartClientWrapper.value,
          CommonDepsScalaJS.scalaTags.value,
          CommonDepsScalaJS.macroJS.value,
          CommonDeps.smartclient,
          CommonDeps.ssysCommonWebapp
      ),

      (resourceGenerators in Compile) += task[Seq[File]] {

          val aboutFile: File = baseDirectory.value.getParentFile / "webapp" / "javascript" / "generated" / "generatedComponents" / "MakeAboutData.js"

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

                      Common.list append Info(item.name.toString, res)
              }
          }

          Common.list ++= Seq(
              Info("Разработка :", "АО ИВЦ \"Информ\" (info@ivc-inform.ru)"),
              Info("Версия :", version.value)
          )

          makeVersionList(CommonDeps.versions)
          makeVersionList(PluginDeps.versions)

          IO.write(aboutFile, s"simpleSyS.aboutData = ${Common.list.toString()}")
          Seq()
      }
  )
  .jvmSettings(
      reJRebelJar := "/home/uandrew/jrebel/legacy/jrebel.jar",

      javaOptions in reStart ++= Seq(
          "-noverify",
          "-Xmx2g",
          "-XX:+UseConcMarkSweepGC",
          "-XX:+CMSClassUnloadingEnabled"
      ),

      libraryDependencies ++= Seq(
          CommonDeps.akkaActor,
          CommonDeps.akkaStream,
          CommonDeps.akkaHttp //,
          //          CommonDeps.tika,
          //          CommonDeps.fs2Core,
          //          CommonDeps.fs2IO
      )
  )

lazy val webUIJS = webUI.js
lazy val webUIJVM = webUI.jvm




