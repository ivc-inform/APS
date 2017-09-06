import com.simplesys.mergewebapp.MergeWebappPlugin._
import sbtcrossproject.{CrossType, crossProject}
import spray.revolver.RevolverPlugin.autoImport._
import com.simplesys.mergewebapp.MergeWebappPlugin._

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
          CommonDeps.scalaTags
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
          ("com.simplesys", "smartclient-js") -> Seq(
              Seq("isomorphic") -> Some(Seq("isomorphic"))
          )
      ),
      webAppDirPath in MergeWebappConfig := baseDirectory.value.getParentFile / "webapp",

      MergeWebappPlugin.mergeWebappSettings,

      (resourceGenerators in Compile) += task[Seq[File]] {

          val aboutFile: File = baseDirectory.value.getParentFile / "webapp" / "javascript" / "generated" / "generatedComponents" / "MakeAboutData.js"

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
      },

      libraryDependencies ++= Seq(
          CommonDepsScalaJS.smartClientWrapper.value,
          CommonDepsScalaJS.scalaTags.value,
          CommonDepsScalaJS.macroJS.value,
          CommonDeps.smartclient
      )
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




