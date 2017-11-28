package com.simplesys.js.components.gantt.props

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.{JSAny, JSUndefined}
import com.simplesys.function._
import com.simplesys.gantt.JS.GanttChart._
import com.simplesys.gantt.JS.{GanttChart, GanttChartExt, TaskItemExt}
import com.simplesys.gantt.TaskCssClass._
import com.simplesys.gantt.{CaptionType, Format, Group, _}
import com.simplesys.js.components.gantt.GanttImprovedChartTest
import com.simplesys.option.ScOption._
import org.scalajs.dom

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
                        g.AddTaskItem(new TaskItemExt(pID = 332, pName = "Task Label Table", pStart = "2016-03-06 00:00".toLDT, pEnd = "2016-03-09 00:00".toLDT, pClass = gtaskgreen, pRes = "Brian", pComp = 60, pParentID = 33, pOpen = Opening.open))
                        g.AddTaskItem(new TaskItemExt(pID = 333, pName = "Task Scrolling Grid", pStart = "2016-03-11 00:00".toLDT, pEnd = "2016-03-20 00:00".toLDT, pClass = gtaskblue, pRes = "Brian1", pParentID = 33, pOpen = Opening.open, pDepend = 332))
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
