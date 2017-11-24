package com.simplesys.js.components.gantt.props

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.{JSAny, JSUndefined}
import com.simplesys.function._
import com.simplesys.gantt._
import com.simplesys.js.components.gantt.GanttImprovedChartTest
import com.simplesys.option.ScOption._
import org.scalajs.dom
import com.simplesys.gantt.GanttChart._
import com.simplesys.gantt.TaskCssClass._

import scala.language.implicitConversions

class GanttImprovedChartTestProps extends GanttImprovedChartProps {
    type classHandler <: GanttImprovedChartTest

    draw = {
        (thiz: classHandler, args: JSUndefined[IscArray[JSAny]]) ⇒
            if (!thiz.readyToDraw())
                thiz
            else {
                thiz.Super("draw")
                val div = dom.document.getElementById(thiz.getID1)

                implicit val g = new GanttChartExt(div, Format.day)
                g.addLang("rus", GanttChart.langRus)
                g setLang "rus"

                /*g.getDivId.foreach {
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
                }*/

                g.getDivId.foreach {
                    _ ⇒
                        g setCaptionType CaptionType.Complete
                        g setQuarterColWidth 36
                        g setDateTaskDisplayFormat "day dd mm yyyy HH:MI"
                        g setDateTaskTableDisplayFormat "dd/mm/yyyy HH:MI"
                        g setDayMajorDateDisplayFormat "mon yyyy - Week ww"
                        g setWeekMinorDateDisplayFormat "dd mon"
                        g setShowTaskInfoLink 1
                        g setShowEndWeekDate 0
                        g setUseSingleCell 20000
                        g setShowComp 1
                        //g setShowTaskInfoStartDate 0
                        //g setFormatArr(Format.hour, Format.day, Format.week, Format.month, Format.quarter)
                        g.AddTaskItem(new TaskItemExt(pID = 1, pName = "Define Chart API", pClass = ggroupblack, pRes = "Brian", pGroup = Group.standardGroupTask, pNotes = "Some Notes text"))
                        g.AddTaskItem(new TaskItemExt(pID = 11, pName = "Chart Object", pStart = "2016-02-20 12:30".toLDT, pEnd = "2016-02-22 01:22".toLDT, pClass = gmilestone, pLink = Link("Link about"), pMile = MileStone.milestone, pRes = "Shlomy", pComp = 100, pParentID = 1))
                        g.AddTaskItem(new TaskItemExt(pID = 12, pName = "Task Objects", pClass = ggroupblack, pRes = "Shlomy", pComp = 40, pGroup = Group.standardGroupTask, pParentID = 1, pOpen = Opening.open))
                        g.AddTaskItem(new TaskItemExt(pID = 121, pName = "Constructor Proc", pStart = "2016-02-21 00:00".toLDT, pEnd = "2016-03-09 00:00".toLDT, pClass = gtaskblue, pRes = "Brian T.", pComp = 60, pParentID = 12, pOpen = Opening.open))
                        g.AddTaskItem(new TaskItemExt(pID = 122, pName = "Task Variables", pStart = "2016-03-06 00:00".toLDT, pEnd = "2016-03-11 00:00".toLDT, pClass = gtaskred, pRes = "Brian", pComp = 60, pParentID = 12, pOpen = Opening.open, pDepend = 121))
                        g.AddTaskItem(new TaskItemExt(pID = 123, pName = "Task by Minute/Hour", pStart = "2016-03-09 00:00".toLDT, pEnd = "2016-03-14 00:00".toLDT, pClass = gtaskyellow, pRes = "Ilan", pComp = 60, pParentID = 12, pOpen = Opening.open))
                        g.AddTaskItem(new TaskItemExt(pID = 124, pName = "Task Functions", pStart = "2016-03-09 00:00".toLDT, pEnd = "2016-03-29 00:00".toLDT, pClass = gtaskred, pRes = "Anyone", pComp = 60, pParentID = 12, pOpen = Opening.open, pDepend = (123).SS, pCaption = "This is a caption"))
                        g.AddTaskItem(new TaskItemExt(pID = 2, pName = "Create HTML Shell", pStart = "2016-03-24 00:00".toLDT, pEnd = "2016-03-24 00:00".toLDT, pClass = gtaskyellow, pRes = "Brian", pComp = 20, pOpen = Opening.open, pDepend = 122))
                        g.AddTaskItem(new TaskItemExt(pID = 3, pName = "Code Javascript", pClass = ggroupblack, pRes = "Brian", pGroup = Group.standardGroupTask, pOpen = Opening.open))
                        g.AddTaskItem(new TaskItemExt(pID = 31, pName = "Define Variables", pStart = "2016-02-25 00:00".toLDT, pEnd = "2016-03-17 00:00".toLDT, pClass = gtaskpurple, pRes = "Brian", pComp = 30, pParentID = 3, pOpen = Opening.open, pCaption = "Caption 1"))
                        g.AddTaskItem(new TaskItemExt(pID = 32, pName = "Calculate Chart Size", pStart = "2016-03-15 00:00".toLDT, pEnd = "2016-03-24 00:00".toLDT, pClass = gtaskgreen, pRes = "Shlomy", pComp = 40, pParentID = 3, pOpen = Opening.open))
                        g.AddTaskItem(new TaskItemExt(pID = 33, pName = "Draw Task Items", pClass = ggroupblack, pRes = "Someone", pComp = 40, pGroup = Group.combinedGroup, pParentID = 3, pOpen = Opening.open))
                        g.AddTaskItem(new TaskItemExt(pID = 332, pName = "Task Label Table", pStart = "2016-03-06 00:00".toLDT, pEnd = "2016-03-09 00:00".toLDT, pClass = gtaskblue, pRes = "Brian", pComp = 60, pParentID = 33, pOpen = Opening.open))
                        g.AddTaskItem(new TaskItemExt(pID = 333, pName = "Task Scrolling Grid", pStart = "2016-03-11 00:00".toLDT, pEnd = "2016-03-20 00:00".toLDT, pClass = gtaskblue, pRes = "Brian", pParentID = 33, pOpen = Opening.open, pDepend = 332))
                        g.AddTaskItem(new TaskItemExt(pID = 34, pName = "Draw Task Bars", pClass = ggroupblack, pRes = "Anybody", pComp = 60, pGroup = Group.standardGroupTask, pParentID = 3))
                        g.AddTaskItem(new TaskItemExt(pID = 341, pName = "Loop each Task", pStart = "2016-03-26 00:00".toLDT, pEnd = "2016-04-11 00:00".toLDT, pClass = gtaskred, pRes = "Brian", pComp = 60, pParentID = 34, pOpen = Opening.open))
                        g.AddTaskItem(new TaskItemExt(pID = 342, pName = "Calculate Start/Stop", pStart = "2016-04-12 00:00".toLDT, pEnd = "2016-05-18 00:00".toLDT, pClass = gtaskpink, pRes = "Brian", pComp = 60, pParentID = 34, pOpen = Opening.open))
                        g.AddTaskItem(new TaskItemExt(pID = 343, pName = "Draw Task Div", pStart = "2016-05-13 00:00".toLDT, pEnd = "2016-05-17 00:00".toLDT, pClass = gtaskred, pRes = "Brian", pComp = 60, pParentID = 34, pOpen = Opening.open))
                        g.AddTaskItem(new TaskItemExt(pID = 344, pName = "Draw Completion Div", pStart = "2016-05-17 00:00".toLDT, pEnd = "2016-06-04 00:00".toLDT, pClass = gtaskred, pRes = "Brian", pComp = 60, pParentID = 34, pOpen = Opening.open, pDepend = Seq(342, 343)))
                        g.AddTaskItem(new TaskItemExt(pID = 35, pName = "Make Updates", pStart = "2016-07-17 00:00".toLDT, pEnd = "2017-09-04 00:00".toLDT, pClass = gtaskpurple, pRes = "Brian", pComp = 30, pParentID = 3, pOpen = Opening.open, pDepend = 333))
                        g.Draw()
                }
                thiz
            }
    }.toThisFunc.opt
}
