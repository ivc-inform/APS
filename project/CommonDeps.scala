package ru.simplesys.eakd.sbtbuild

import sbt._

object PluginDeps {

    val devPluginVersion = "1.3.14-SNAPSHOT"
    val sbtCoffeScriptVersion = "1.1.4"
    val mergeJSVersion = "1.0.12"
    val xsbtWebVersion = "4.0.2"
    val sbtNativePackagerVersion = "1.2.4"
    val scalaJSPluginVersion = "0.6.20"
    val jrabelPluginVersion = "0.11.1"

    val devPlugin = addSbtPlugin("ru.simplesys" % "dev-plugin" % devPluginVersion)
    val mergeJS = addSbtPlugin("ru.simplesys" % "merge-js" % mergeJSVersion)
    val sbtCoffeeScript = addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % sbtCoffeScriptVersion)
    val xsbtWeb = addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % xsbtWebVersion)
    val sbtNativePackager = addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % sbtNativePackagerVersion)
    val scalaJSPlugin = addSbtPlugin("org.scala-js" % "sbt-scalajs" % scalaJSPluginVersion)
    val jrebelPlugin = addSbtPlugin("com.simplesys" % "jrebel-plugin" % jrabelPluginVersion)
}

object CommonDeps {

    val scalaModulesVersion = "1.0.6"

    val jodaVersion = "2.8.2"
    val jodaConvertVersion = "1.7"

    val ssysCoreVersion = "1.4-SNAPSHOT"
    //val ssysCoreVersion = "1.4.0.2"
    val smartclientVersion = "11.1-v20170703.1"

    val servletAPIVersion = "3.1.0"

    val scalaTestVersion = "3.0.4"
    val scalaTagsVersion = "0.6.7"

    val scalaJSVersion = "1.4-SNAPSHOT"
    //val scalaJSVersion = "1.4.0.1"

    val scalajsDOMVersion = "0.9.3"
    val udashJQueryVersion = "1.1.1-SNAPSHOT"

    val jettyVersion = "9.4.7.v20170914"
    val jdbcOracle12DriverVersion = "12.2.0.1"

    val commonsFileuploadVersion = "1.3.3"
    val commonsIOVersion = "2.5"
    val scalaURIVersion = "0.4.16"
    val configWrapperVersion = "0.4.4"

    val servletAPI = "javax.servlet" % "javax.servlet-api" % servletAPIVersion

    val smartclient = "com.simplesys" % "smartclient-js" % smartclientVersion

    val ssysCommonWebapp = "com.simplesys.core" %% "common-webapp" % ssysCoreVersion
    val ssysIscComponents = "com.simplesys.core" %% "isc-components" % ssysCoreVersion
    val ssysIscMisc = "com.simplesys.core" %% "isc-misc" % ssysCoreVersion

    val ssysXMLExtender = "com.simplesys.core" %% "xml-extender" % ssysCoreVersion
    val ssysCoreLibrary = "com.simplesys.core" %% "core-library" % ssysCoreVersion
    val ssysCoreUtils = "com.simplesys.core" %% "core-utils" % ssysCoreVersion
    val ssysAkkaExtender = "com.simplesys.core" %% "akka-extender" % ssysCoreVersion
    val ssysConfigWrapper = "com.simplesys.core" %% "config-wrapper" % ssysCoreVersion
    val ssysCommon = "com.simplesys.core" %% "common" % ssysCoreVersion
    val ssysScalaIOExtender = "com.simplesys.core" %% "scala-io-extender" % ssysCoreVersion
    val ssysJDBCWrapper = "com.simplesys.core" %% "jdbc-wrapper" % ssysCoreVersion
    val ssysCoreDomains = "com.simplesys.core" %% "core-domains" % ssysCoreVersion
    val ssysScalaGen = "com.simplesys.core" %% "scala-gen" % ssysCoreVersion

    val oraclePoolDataSources = "com.simplesys.core" %% "oracle-pool-datasources" % ssysCoreVersion
    val hikariPoolDataSources = "com.simplesys.core" %% "hikari-cp" % ssysCoreVersion
    val ssysLogBackWrapper = "com.simplesys.core" %% "logback-wrapper" % ssysCoreVersion
    val scalaJSWrapper = "com.simplesys" %% "common-types" % scalaJSVersion
    val scalaTags = "com.lihaoyi" %% "scalatags" % scalaTagsVersion

    val jettyRuner = "org.eclipse.jetty" % "jetty-runner" % jettyVersion

    val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion

    val jdbcOracle12 = "com.oracle.jdbc" % "ojdbc8" % jdbcOracle12DriverVersion
    val jdbcOracle12UCP = "com.oracle.jdbc" % "ucp" % jdbcOracle12DriverVersion
    val jdbcOracleN18_12 = "com.oracle.jdbc" % "orai18n" % jdbcOracle12DriverVersion

    val commonsFileupload = "commons-fileupload" % "commons-fileupload" % commonsFileuploadVersion
    val commonsIO = "commons-io" % "commons-io" % commonsIOVersion

    val scalaURI = "com.netaporter" %% "scala-uri" % scalaURIVersion
    val configWrapper = "com.github.kxbmap" %% "configs" % configWrapperVersion

}

