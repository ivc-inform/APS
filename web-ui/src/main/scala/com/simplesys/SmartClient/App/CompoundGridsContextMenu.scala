package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.{AbstractMenuSSCompanion, MenuSS}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js.|

trait GridContextMenuData extends js.Object {
    val simpleTable: Boolean = js.native
    val captionMenu: String
    val customMenu: Seq[MenuSSItem] = js.native
}

@js.native
trait CompoundGridsContextMenu extends MenuSS {
}

@js.native
abstract trait AbstractCompoundGridsContextMenuCompanion extends AbstractMenuSSCompanion {
}
 
