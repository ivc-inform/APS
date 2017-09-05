package com.ivcInform.http.server

import com.simplesys.config.Config
import com.simplesys.log.Logging
import configs.syntax._

case class MainConfig(host: String, port: Int)

object AppSettings extends Config with Logging{
    logger info "Initialization AppSettings"

    val http = config.get[MainConfig]("aps.http").value
}
