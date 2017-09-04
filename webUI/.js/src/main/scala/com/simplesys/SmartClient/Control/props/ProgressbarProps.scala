package com.simplesys.SmartClient.Control.props

import com.simplesys.SmartClient.Foundation.props.StretchImgProps
import com.simplesys.SmartClient.System.{IscArray, isc}
import com.simplesys.System.JSAny
import com.simplesys.System.Types.void
import com.simplesys.option.{IntString, ScNone, ScOption}
import com.simplesys.function._
import com.simplesys.option.ScOption._

import scala.scalajs.js
import scala.scalajs.js.ThisFunction0

class ProgressbarProps extends StretchImgProps {

    import com.simplesys.SmartClient.Control.Progressbar

    type classHandler <: Progressbar

    var breadth: ScOption[Int] = ScNone
    var length: ScOption[IntString[Int, String]] = ScNone

    var percentChanged: ScOption[js.Function1[void, _]] = ScNone
    var percentDone: ScOption[Int] = ScNone

    var oneStep: ScOption[Double] = ScNone
    var minValue: ScOption[Double] = 0.0.opt
    var maxValue: ScOption[Double] = 100.0.opt

    var nextStep: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) ⇒
            isc.Timer.setTimeout({
                () ⇒
                    var percentDone: Double = thiz.percentDone + thiz.oneStep

                    if (100.0 - percentDone < thiz.oneStep)
                        percentDone = 100.0

                    thiz setPercentDone percentDone
            }, 0)
    }.toThisFunc.opt

    initWidget = {
        (thisTop: classHandler, args: IscArray[JSAny]) ⇒

            thisTop.Super("initWidget", args)

            thisTop.oneStep = 1 / ((thisTop.maxValue - thisTop.minValue) / 100)
            thisTop setPercentDone 0.0

    }.toThisFunc.opt
}
