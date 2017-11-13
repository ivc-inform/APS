package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.{CompoundGridsContextMenu, GridContextMenuData, TreeListGridContextMenu}
import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Control.props.{ListGridContextMenuProps, ListGridContextMenuWithFormProps, MenuSSProps, TreeGridContextMenuProps}
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._

import scala.scalajs.js._

class CompoundGridsContextMenuProps extends MenuSSProps {
    type classHandler <: CompoundGridsContextMenu

    var gridsContextMenuData: ScOption[Seq[GridContextMenuData]] = ScNone

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
                            val menu: Seq[MenuSS] = if (isc.isA.ListGridEditor(grid)) {
                                if (grid.simpleTable.getOrElse(false)) {
                                    val res = listGridEditorMenu
                                    grid setContextMenu res
                                    Seq(res)
                                }
                                else {
                                    val res = listGridEditorMenuWithForm
                                    grid setContextMenu res
                                    Seq(res)
                                }
                            } else if (isc.isA.TreeGridEditor(grid)) {
                                val res = treeGridEditorMenu
                                grid setContextMenu res
                                Seq(res)
                            } else Seq()

                            val _menu = menu ++ gridContextMenuData.customMenu.getOrElse(Seq())
                            thizTop.addItem(
                                new MenuSSItemProps {
                                    submenu = .opt
                                    title = thiz.captionMenuList.ellipsis.opt
                                    icon = Common.ellipsis.opt
                                }
                            )

                    }
            }
    }.toThisFunc.opt
}
