package com.simplesys.SmartClient.DataBinding

import com.simplesys.System.Types.{URL, void}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
trait DataSourceSS extends DataSource {
}

@js.native
abstract trait AbstractDataSourceSSCompanion extends AbstractDataSourceCompanion {
    var loaderSchemasURL: URL = js.native
    def loadComponentSchemas(callback: js.Function0[_]): void = js.native
    def getLogDataSources():void = js.native
}

@js.native
@JSGlobal("DataSourceSS")
object DataSourceSSstatic extends AbstractDataSourceSSCompanion
