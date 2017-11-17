package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.{CompoundGridsContextMenu, GridContextMenuData}
import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
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

            implicit def getCustomMenuOpt(seq: Seq[MenuSSItem]): ScOption[Seq[MenuSSItem]] = if (seq.isEmpty) ScNone else seq.opt

            thizTop.gridsContextMenuData.foreach {
                gridContextMenuData ⇒

                    //isc debugTrap(gridContextMenuData.grid, gridContextMenuData.grid.funcMenu)
                    (if (isc.isA.ListGridContextMenu(gridContextMenuData.grid.funcMenu) || isc.isA.ListGridContextMenuWithForm(gridContextMenuData.grid.funcMenu) || isc.isA.TreeGridContextMenu(gridContextMenuData.grid.funcMenu))
                        Some(gridContextMenuData.grid.funcMenu.get)
                    else None).foreach {
                        menu ⇒
                            thizTop.addItem(
                                MenuSSItem(
                                    new MenuSSItemProps {
                                        submenu = menu.opt
                                        title = gridContextMenuData.captionMenu.opt
                                        icon = Common.ellipsis.opt
                                    })
                            )
                    }
            }
    }.toThisFunc.opt
}
