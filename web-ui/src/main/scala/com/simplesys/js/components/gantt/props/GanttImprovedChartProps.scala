package com.simplesys.js.components.gantt.props

import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.{JSAny, JSUndefined}
import com.simplesys.function._
import com.simplesys.gantt.GanttChart._
import com.simplesys.gantt._
import com.simplesys.js.components.gantt.GanttImprovedChart
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}
import org.scalajs.dom

import scala.language.implicitConversions
import scalatags.Text.all._

class GanttImprovedChartProps extends CanvasProps {
    type classHandler <: GanttImprovedChart

    height = "100%"
    getID1 = {
        (thiz: classHandler) ⇒
            s"${thiz.getID}_ganttChartImproved"
    }.toThisFunc.opt

    getInnerHTML = {
        (thiz: classHandler) ⇒
            div(
                style := "position:relative",
                id := thiz.getID1,
                `class` := "gantt"
            ).render
    }.toThisFunc.opt

    var canvasWidth: ScOption[Int] = ScNone

    draw = {
        (thiz: classHandler, args: JSUndefined[IscArray[JSAny]]) ⇒
            if (!thiz.readyToDraw())
                thiz
            else {
                thiz.Super("draw")
                val div = dom.document.getElementById(thiz.getID1)

                val g = new GanttChartExt(div, Format.day)
                g.addLang("rus", GanttChart.langRus)
                g setLang "rus"

                g.getDivId.foreach {
                    _ ⇒
                        g setCaptionType CaptionType.Complete
                        g setQuarterColWidth 36
                        g setDateTaskDisplayFormat "day dd month yyyy"
                        g setDayMajorDateDisplayFormat "mon yyyy - Week ww"
                        g setWeekMinorDateDisplayFormat "dd mon"
                        g setShowTaskInfoLink 1
                        g setShowEndWeekDate 0
                        g setUseSingleCell 10000
                        g setFormatArr(Format.hour, Format.day, Format.week, Format.month, Format.quarter)

                        g.AddTaskItem(new TaskItem(1, "Define Chart API", "", "", "ggroupblack", "", 0, "Brian", 0, 1, 0, 1, "", "", "Some Notes text", g))
                        g.AddTaskItem(new TaskItem(11, "Chart Object", "2016-02-20", "2016-02-20", "gmilestone", "", 1, "Shlomy", 100, 0, 1, 1, "", "", "", g))
                        g.AddTaskItem(new TaskItem(12, "Task Objects", "", "", "ggroupblack", "", 0, "Shlomy", 40, 1, 1, 1, "", "", "", g))
                        g.AddTaskItem(new TaskItem(121, "Constructor Proc", "2016-02-21", "2016-03-09", "gtaskblue", "", 0, "Brian T.", 60, 0, 12, 1, "", "", "", g))
                        g.AddTaskItem(new TaskItem(122, "Task Variables", "2016-03-06", "2016-03-11", "gtaskred", "", 0, "Brian", 60, 0, 12, 1, "121", "", "", g))
                        g.AddTaskItem(new TaskItem(123, "Task by Minute/Hour", "2016-03-09", "2016-03-14 12:00", "gtaskyellow", "", 0, "Ilan", 60, 0, 12, 1, "", "", "", g))
                        g.AddTaskItem(new TaskItem(124, "Task Functions", "2016-03-09", "2016-03-29", "gtaskred", "", 0, "Anyone", 60, 0, 12, 1, "123SS", "This is a caption", null, g))
                        g.AddTaskItem(new TaskItem(2, "Create HTML Shell", "2016-03-24", "2016-03-24", "gtaskyellow", "", 0, "Brian", 20, 0, 0, 1, "122", "", "", g))
                        g.AddTaskItem(new TaskItem(3, "Code Javascript", "", "", "ggroupblack", "", 0, "Brian", 0, 1, 0, 1, "", "", "", g))
                        g.AddTaskItem(new TaskItem(31, "Define Variables", "2016-02-25", "2016-03-17", "gtaskpurple", "", 0, "Brian", 30, 0, 3, 1, "", "Caption 1", "", g))
                        g.AddTaskItem(new TaskItem(32, "Calculate Chart Size", "2016-03-15", "2016-03-24", "gtaskgreen", "", 0, "Shlomy", 40, 0, 3, 1, "", "", "", g))
                        g.AddTaskItem(new TaskItem(33, "Draw Task Items", "", "", "ggroupblack", "", 0, "Someone", 40, 2, 3, 1, "", "", "", g))
                        g.AddTaskItem(new TaskItem(332, "Task Label Table", "2016-03-06", "2016-03-09", "gtaskblue", "", 0, "Brian", 60, 0, 33, 1, "", "", "", g))
                        g.AddTaskItem(new TaskItem(333, "Task Scrolling Grid", "2016-03-11", "2016-03-20", "gtaskblue", "", 0, "Brian", 0, 0, 33, 1, "332", "", "", g))
                        g.AddTaskItem(new TaskItem(34, "Draw Task Bars", "", "", "ggroupblack", "", 0, "Anybody", 60, 1, 3, 0, "", "", "", g))
                        g.AddTaskItem(new TaskItem(341, "Loop each Task", "2016-03-26", "2016-04-11", "gtaskred", "", 0, "Brian", 60, 0, 34, 1, "", "", "", g))
                        g.AddTaskItem(new TaskItem(342, "Calculate Start/Stop", "2016-04-12", "2016-05-18", "gtaskpink", "", 0, "Brian", 60, 0, 34, 1, "", "", "", g))
                        g.AddTaskItem(new TaskItem(343, "Draw Task Div", "2016-05-13", "2016-05-17", "gtaskred", "", 0, "Brian", 60, 0, 34, 1, "", "", "", g))
                        g.AddTaskItem(new TaskItem(344, "Draw Completion Div", "2016-05-17", "2016-06-04", "gtaskred", "", 0, "Brian", 60, 0, 34, 1, "342,343", "", "", g))
                        g.AddTaskItem(new TaskItem(35, "Make Updates", "2016-07-17", "2017-09-04", "gtaskpurple", "", 0, "Brian", 30, 0, 3, 1, "333", "", "", g))
                        g.Draw()
                }
                thiz
            }
    }.toThisFunc.opt

    redrawOnResize = false.opt
}
