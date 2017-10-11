package com.simplesys.SmartClient.Control

import com.simplesys.SmartClient.Foundation.{AbstractStatefulCanvasCompanion, Img, AbstractImgCompanion}
import com.simplesys.System.Types.void

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
trait ImgButton extends Img {
    var action: js.Function1[void, _]
    var hiliteAccessKey: Boolean
    var iconAlign: String
    var labelHPad: Int
    var labelVPad: Int
    def setSelected(): void
    var showClippedTitleOnHover: Boolean
    var showDisabledIcon: Boolean
    var showTitle: Boolean
    var titleClipped: js.Function1[void, Boolean]
    var titleHover: js.Function1[void, Boolean]
}

@js.native
@JSGlobal
abstract class AbstractImgButtonCompanion extends AbstractImgCompanion



