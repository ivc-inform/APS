package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Control.props.ProgressbarProps
import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.{CanvasItem, ProgressbarItem}
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.FormItemComponentType
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{IntString, ScNone, ScOption}

import scala.scalajs.js._

class ProgressbarItemProps extends CanvasItemProps {
    type classHandler <: ProgressbarItem

    height = 20
    width = "*"
    rowSpan = "*"
    colSpan = "*"
    endRow = true.opt
    startRow = true.opt

    var length: ScOption[IntString[Int, String]] = 100
    var breadth: ScOption[Int] = 20.opt

    var minValue: ScOption[Double] = 0.0.opt
    var maxValue: ScOption[Double] = 100.0.opt
    var oneStep: ScOption[Double] = ScNone
    var showProgressBarTitle: ScOption[Boolean] = true.opt

    var setTitle: ScOption[ThisFunction1[classHandler, String, _]] = {
            (thiz: classHandler, title: String) ⇒
                thiz.progressBar setTitle  title
        }.toThisFunc.opt

    var setPercentDone: ScOption[ThisFunction1[classHandler, Double, _]] = {
        (thiz: classHandler, percentDone: Double) ⇒
            thiz.progressBar setPercentDone percentDone
            thiz.progressBar setTitle s"Upload progress: ${thiz.progressBar.percentDone}%"
    }.toThisFunc.opt

    createCanvas = {
        (thizTop: classHandler, form: DynamicFormSS, item: CanvasItem) =>
            thizTop.progressBar = Progressbar.create(
                new ProgressbarProps {
                    length = "*"
                    breadth = thizTop.breadth.opt
                    showTitle = thizTop.showProgressBarTitle.opt
                }
            )

            thizTop.oneStep = 1 / ((thizTop.maxValue - thizTop.minValue) / 100)
            thizTop setPercentDone 0.0

            thizTop.progressBar
    }.toThisFunc.opt

    var nextStep: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) ⇒
            isc.Timer.setTimeout({
                () ⇒
                    var percentDone: Double = thiz.progressBar.percentDone + thiz.oneStep

                    if (100.0 - percentDone < thiz.oneStep)
                        percentDone = 100.0

                    //isc debugTrap (percentDone)
                    //println(s"percentDone: $percentDone")

                    thiz setPercentDone percentDone
            }, 0)
    }.toThisFunc.opt

    `type` = FormItemComponentType.ProgressbarItem
}
