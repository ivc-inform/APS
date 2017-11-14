package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.{AbstractMenuSSCompanion, MenuSS}
import com.simplesys.SmartClient.Grids.ListGridEditor
import com.simplesys.SmartClient.System.IscArray

import scala.scalajs.js

trait GridContextMenuData extends js.Object {
    val grid: ListGridEditor
    val captionMenu: String
    val customMenu: Seq[MenuSSItem]
}

@js.native
trait CompoundGridsContextMenu extends MenuSS {
    val gridsContextMenuData: IscArray[GridContextMenuData]
}

@js.native
abstract trait AbstractCompoundGridsContextMenuCompanion extends AbstractMenuSSCompanion {
}
 
