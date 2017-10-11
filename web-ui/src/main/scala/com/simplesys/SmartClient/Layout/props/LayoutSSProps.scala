package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Layout.LayoutSS
import com.simplesys.System.JSUndefined
import com.simplesys.function._
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

import scala.scalajs.js._

class LayoutSSProps extends LayoutProps {
    type classHandler <: LayoutSS

    autoDraw = false.opt

    var getFuncMenu: ScOption[ThisFunction0[classHandler, JSUndefined[MenuSS]]] = {
        (thiz: classHandler) ⇒
            thiz.funcMenu
    }.toThisFunc.opt

}
