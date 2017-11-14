package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.{CompoundGridsContextMenu, GridContextMenuData}
import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Control.props.{ListGridContextMenuProps, ListGridContextMenuWithFormProps, MenuSSProps, TreeGridContextMenuProps}
import com.simplesys.SmartClient.System.{MenuSSItem, _}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

class CompoundGridsContextMenuProps extends MenuSSProps {
    type classHandler <: CompoundGridsContextMenu

    var gridsContextMenuData: ScOption[Seq[GridContextMenuData]] = ScNone

    initWidget = {
        (thizTop: classHandler, arguments: IscArray[JSAny]) =>
            thizTop.Super("initWidget", arguments)

            isc debugTrap 0
            isc debugTrap thizTop.gridsContextMenuData
            thizTop.gridsContextMenuData.foreach {
                gridContextMenuData ⇒
                    def listGridEditorMenu = ListGridContextMenu.create(
                        new ListGridContextMenuProps {
                            customMenu = if (gridContextMenuData.customMenu.isEmpty) ScNone else gridContextMenuData.customMenu.opt
                            owner = gridContextMenuData.grid.opt
                        }
                    )

                    def listGridEditorMenuWithForm = ListGridContextMenuWithForm.create(
                        new ListGridContextMenuWithFormProps {
                            customMenu = if (gridContextMenuData.customMenu.isEmpty) ScNone else gridContextMenuData.customMenu.opt
                            owner = gridContextMenuData.grid.opt
                        }
                    )

                    def treeGridEditorMenu = TreeGridContextMenu.create(
                        new TreeGridContextMenuProps {
                            customMenu = if (gridContextMenuData.customMenu.isEmpty) ScNone else gridContextMenuData.customMenu.opt
                            owner = gridContextMenuData.grid.opt
                        }
                    )

                    val grid = gridContextMenuData.grid

                    val menu: Option[MenuSS] =
                        if (isc.isA.ListGridEditor(grid)) {
                            if (grid.simpleTable.getOrElse(false)) {
                                val res = listGridEditorMenu
                                grid setContextMenu res
                                Some(res)
                            }
                            else {
                                val res = listGridEditorMenuWithForm
                                grid setContextMenu res
                                Some(res)
                            }
                        } else if (isc.isA.TreeGridEditor(grid)) {
                            val res = treeGridEditorMenu
                            grid setContextMenu res
                            Some(res)
                        } else
                            None

                    menu.foreach {
                        menu ⇒
                            thizTop.addItem(
                                MenuSSItem(
                                    new MenuSSItemProps {
                                        submenu = menu.opt
                                        title = gridContextMenuData.captionMenu.ellipsis.opt
                                        icon = Common.ellipsis.opt
                                    })
                            )
                    }
            }
    }.toThisFunc.opt
}
