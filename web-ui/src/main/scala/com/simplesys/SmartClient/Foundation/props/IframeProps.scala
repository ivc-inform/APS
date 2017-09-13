package com.simplesys.SmartClient.Foundation.props

import com.simplesys.SmartClient.Foundation.Iframe
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.Visibility
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._

class IframeProps extends CanvasProps {
    type classHandler <: Iframe

    visibility = Visibility.hidden.opt

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>
            thiz.Super("initWidget", arguments)

            thiz setContents "<IFRAME NAME=\""+ thiz.ID + "\" style=\"width:0;height:0;border:0\"></IFRAME>"

            //isc debugTrap thiz

    }.toThisFunc.opt
}
