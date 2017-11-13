package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.{CompoundGridsContextMenu, GridContextMenuData, TreeListGridContextMenu}
import com.simplesys.SmartClient.Control.props.MenuSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._

import scala.scalajs.js._

class CompoundGridsContextMenuProps extends MenuSSProps {
    type classHandler <: CompoundGridsContextMenu

    var grids : Option[Seq[GridContextMenuData]] = None

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>
            //isc debugTrac(thiz.getClassName(), thiz.getIdentifier())

            thiz.Super("initWidget", arguments)
    }.toThisFunc.opt
}
