package ru.simplesys.eakd.sbtbuild

import sbt.{ModuleID, ProjectRef, _}

object PluginDeps {

    object versions {
        val devPluginVersion = "1.3.15-SNAPSHOT"
        val sbtCoffeScriptVersion = "1.1.4"
        val mergeJSVersion = "1.0.14-SNAPSHOT"
        val xsbtWebVersion = "4.0.2"
        val sbtNativePackagerVersion = "1.2.4"
        val jrabelPluginVersion = "0.11.1"
        val scalaCrossProjectPluginVersion = "0.3.3-SNAPSHOT"
    }

    val devPlugin = addSbtPlugin("ru.simplesys" % "dev-plugin" % versions.devPluginVersion)
    val mergeJS = addSbtPlugin("ru.simplesys" % "merge-js" % versions.mergeJSVersion)
    val sbtCoffeeScript = addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % versions.sbtCoffeScriptVersion)
    val xsbtWeb = addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % versions.xsbtWebVersion)
    val sbtNativePackager = addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % versions.sbtNativePackagerVersion)
    val sbtCrossproject = addSbtPlugin("org.scala-native" % "sbt-scalajs-crossproject" % versions.scalaCrossProjectPluginVersion)
    val crossproject = addSbtPlugin("org.scala-native" % "sbt-crossproject" % versions.scalaCrossProjectPluginVersion)
    val jrebelPlugin = addSbtPlugin("com.simplesys" % "jrebel-plugin" % versions.jrabelPluginVersion)
    val sbtHydraPlugin = addSbtPlugin("com.triplequote" % "sbt-hydra" % "0.9.8")
    val circeExtender = "com.simplesys.core" %% "circe-extender" % CommonDeps.versions.ssysCoreVersion
}

object CommonDeps {

    //case class SsysConf(projectRef: ProjectRef, moduleID: ModuleID)

    object versions {
        val scalaModulesVersion = "1.0.6"

        val ssysCoreVersion = "1.5-SNAPSHOT"
        //val ssysCoreVersion = "1.4.0.2"
        val smartclientVersion = "11.1-v20170703.1"

        val servletAPIVersion = "3.1.0"

        val scalaTestVersion = "3.0.4"
        val scalaTagsVersion = "0.6.7"

        val scalaJSVersion = "1.5-SNAPSHOT"
        //val scalaJSVersion = "1.4.0.1"

        val scalajsDOMVersion = "0.9.3"
        val udashJQueryVersion = "1.1.1-SNAPSHOT"

        val jettyVersion = "9.4.7.v20170914"
        val jdbcOracle12DriverVersion = "12.2.0.1"

        val commonsFileuploadVersion = "1.3.3"
        val commonsIOVersion = "2.5"
        val scalaURIVersion = "0.4.16"
        val configWrapperVersion = "0.4.4"
        val jsgantImprovedVersion = "0.9.4-SNAPSHOT"

        val circe = "0.8.0"
    }

    val uriSimpleSys = uri("../../JOB/simplesys")
    val withSimpleSysSources = true

    val servletAPI = "javax.servlet" % "javax.servlet-api" % versions.servletAPIVersion

    val smartclient = "com.simplesys" % "smartclient-js" % versions.smartclientVersion

    val ssysIscComponents = "com.simplesys.core" %% "isc-components" % versions.ssysCoreVersion
    //val ssysIscComponents = ProjectRef(uriSimpleSys, "isc-components")

    val ssysIscMisc = "com.simplesys.core" %% "isc-misc" % versions.ssysCoreVersion
    //val ssysIscMisc = ProjectRef(uriSimpleSys, "isc-misc")

    val ssysXMLExtender = "com.simplesys.core" %% "xml-extender" % versions.ssysCoreVersion
    //val ssysXMLExtender = ProjectRef(uriSimpleSys, "xml-extender")

    val ssysCoreLibrary = "com.simplesys.core" %% "core-library" % versions.ssysCoreVersion
    //val ssysCoreLibrary = ProjectRef(uriSimpleSys, "core-library")

    val ssysAkkaExtender = "com.simplesys.core" %% "akka-extender" % versions.ssysCoreVersion
    //val ssysAkkaExtender = ProjectRef(uriSimpleSys, "akka-extender")

    val ssysConfigWrapper = "com.simplesys.core" %% "config-wrapper" % versions.ssysCoreVersion
    //val ssysConfigWrapper = ProjectRef(uriSimpleSys, "config-wrapper")

    val ssysScalaIOExtender = "com.simplesys.core" %% "scala-io-extender" % versions.ssysCoreVersion
    //val ssysScalaIOExtender = ProjectRef(uriSimpleSys, "scala-io-extender")

    val ssysCommon = "com.simplesys.core" %% "common" % versions.ssysCoreVersion
    //val ssysCommon = ProjectRef(uriSimpleSys, "common")

    val ssysServletWrapper = "com.simplesys.core" %% "servlet-wrapper" % versions.ssysCoreVersion
    //val ssysServletWrapper = ProjectRef(uriSimpleSys, "servlet-wrapper")

    val ssysCommonWebapp = "com.simplesys.core" %% "common-webapp" % versions.ssysCoreVersion
    //val ssysCommonWebapp = ProjectRef(uriSimpleSys, "common-webapp")

    val ssysJDBCWrapper = "com.simplesys.core" %% "jdbc-wrapper" % versions.ssysCoreVersion
    //val ssysJDBCWrapper: ProjectRef = ProjectRef(uriSimpleSys, "jdbc-wrapper")

    val circeExtender = "com.simplesys.core" %% "circe-extender" % versions.ssysCoreVersion
    //val circeExtender = ProjectRef(uriSimpleSys, "circe-extender")

    val oraclePoolDataSources: ModuleID = "com.simplesys.core" %% "oracle-pool-datasources" % versions.ssysCoreVersion
    val hikariPoolDataSources = "com.simplesys.core" %% "hikari-cp" % versions.ssysCoreVersion
    val ssysLogBackWrapper = "com.simplesys.core" %% "logback-wrapper" % versions.ssysCoreVersion
    val scalaJSWrapper = "com.simplesys" %% "common-types" % versions.scalaJSVersion
    val scalaTags = "com.lihaoyi" %% "scalatags" % versions.scalaTagsVersion

    val jettyRuner = "org.eclipse.jetty" % "jetty-runner" % versions.jettyVersion

    val scalaTest = "org.scalatest" %% "scalatest" % versions.scalaTestVersion

    val jdbcOracle12 = "com.oracle.jdbc" % "ojdbc8" % versions.jdbcOracle12DriverVersion
    val jdbcOracle12UCP = "com.oracle.jdbc" % "ucp" % versions.jdbcOracle12DriverVersion
    val jdbcOracleN18_12 = "com.oracle.jdbc" % "orai18n" % versions.jdbcOracle12DriverVersion

    val commonsFileupload = "commons-fileupload" % "commons-fileupload" % versions.commonsFileuploadVersion
    val commonsIO = "commons-io" % "commons-io" % versions.commonsIOVersion

    val scalaURI = "com.netaporter" %% "scala-uri" % versions.scalaURIVersion
    val configWrapper = "com.github.kxbmap" %% "configs" % versions.configWrapperVersion

    val jsgantImproved = "com.simplesys" %% "jsgantt-improved" % versions.jsgantImprovedVersion
    //val jsgantImproved =  ProjectRef(uri("../../JOB/jsgantt-improved"), "ganttImprovedJVM")
}

