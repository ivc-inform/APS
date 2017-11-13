package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.{AbstractMenuSSCompanion, MenuSS}
import com.simplesys.SmartClient.Grids.{ListGrid, ListGridEditor}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js.|

trait GridContextMenuData extends js.Object {
    val grid: js.UndefOr[ListGridEditor] = js.undefined
    val captionMenu: String
    val customMenu: js.UndefOr[Seq[MenuSSItem]] = js.undefined
}

@js.native
trait CompoundGridsContextMenu extends MenuSS {
    val gridsContextMenuData: Seq[GridContextMenuData]
}

@js.native
abstract trait AbstractCompoundGridsContextMenuCompanion extends AbstractMenuSSCompanion {
}
 
