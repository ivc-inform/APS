package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.{CompoundGridsContextMenu, GridContextMenuData, TreeListGridContextMenu}
import com.simplesys.SmartClient.Control.props.{ListGridContextMenuProps, ListGridContextMenuWithFormProps, MenuSSProps, TreeGridContextMenuProps}
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScNone
import com.simplesys.option.ScOption._

import scala.scalajs.js._

class CompoundGridsContextMenuProps extends MenuSSProps {
    type classHandler <: CompoundGridsContextMenu

    var gridsContextMenuData: Option[Seq[GridContextMenuData]] = None

    initWidget = {
        (thizTop: classHandler, arguments: IscArray[JSAny]) =>
            thizTop.Super("initWidget", arguments)

            thizTop.gridsContextMenuData.foreach {
                gridContextMenuData ⇒
                    def listGridEditorMenu = ListGridContextMenu.create(
                        new ListGridContextMenuProps {
                            customMenu = if (gridContextMenuData.customMenu.isEmpty) ScNone else gridContextMenuData.customMenu.get.toSeq.opt
                            owner = gridContextMenuData.grid.opt
                        }
                    )

                    def listGridEditorMenuWithForm = ListGridContextMenuWithForm.create(
                        new ListGridContextMenuWithFormProps {
                            customMenu = if (gridContextMenuData.customMenu.isEmpty) ScNone else gridContextMenuData.customMenu.get.toSeq.opt
                            owner = gridContextMenuData.grid.opt
                        }
                    )

                    def treeGridEditorMenu = TreeGridContextMenu.create(
                        new TreeGridContextMenuProps {
                            customMenu = if (gridContextMenuData.customMenu.isEmpty) ScNone else gridContextMenuData.customMenu.get.toSeq.opt
                            owner = gridContextMenuData.grid.opt
                        }
                    )

                    gridContextMenuData.grid.foreach {
                        grid ⇒
                            if (isc.isA.ListGridEditor(grid)) {
                                if (grid.simpleTable.getOrElse(false))
                                    grid setContextMenu listGridEditorMenu
                                else
                                    grid setContextMenu listGridEditorMenuWithForm
                            } else if (isc.isA.TreeGridEditor(grid)) {
                                grid setContextMenu treeGridEditorMenu
                            }
                    }
            }
    }.toThisFunc.opt
}
