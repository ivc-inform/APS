package com.simplesys.gantt.JS

import com.simplesys.gantt.Group.Group
import com.simplesys.gantt.MileStone.MileStone
import com.simplesys.gantt.Opening.Opening
import com.simplesys.gantt.TaskCssClass.TaskCssClass
import com.simplesys.gantt._

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal
import scala.scalajs.js.|

object TaskItem {
    implicit def mile2Int(value: MileStone): Int = value.id
    implicit def group2Int(value: Group): Int = value.id
    implicit def open2Int(value: Opening): Int = value.id
}

/**
  * pID:	(required) a unique numeric ID used to identify each row
  * pName:	(required) the task Label
  * pStart:	(required) the task start date, can enter empty date ('') for groups. You can also enter specific time (2016-02-20 12:00) for additional precision.
  * pEnd:	(required) the task end date, can enter empty date ('') for groups
  * pClass:	(required) the css class for this task
  * pLink:	(optional) any http link to be displayed in tool tip as the "More information" link.
  * pMile:	(optional) indicates whether this is a milestone task - Numeric; 1 = milestone, 0 = not milestone
  * pRes:	(optional) resource name
  * pComp:	(required) completion percent, numeric
  * pGroup:	(optional) indicates whether this is a group task (parent) - Numeric; 0 = normal task, 1 = standard group task, 2 = combined group task*
  *
  * * Combined group tasks show all sub-tasks on one row. The information displayed in the task list and row caption are taken from the parent task. Tool tips are generated individually
  * * for each sub-task from its own information. Milestones are not valid as sub-tasks of a combined group task and will not be displayed. No bounds checking of start and end dates of sub-tasks is performed
  * * therefore it is possible for these task bars to overlap. Dependencies can be set to and from sub-tasks only.
  *
  * pParent:	(required) identifies a parent pID, this causes this task to be a child of identified task. Numeric, top level tasks should have pParent set to 0
  * pOpen:	(required) indicates whether a standard group task is open when chart is first drawn. Value must be set for all items but is only used by standard group tasks. Numeric, 1 = open, 0 = closed
  * pDepend:	(optional) comma separated list of id's this task is dependent on. A line will be drawn from each listed task to this item
  * Each id can optionally be followed by a dependency type suffix. Valid values are:
  * 'FS' - Finish to Start (default if suffix is omitted)
  * 'SF' - Start to Finish
  * 'SS' - Start to Start
  * 'FF' - Finish to Finish
  * If present the suffix must be added directly to the id e.g. '123SS'
  * pCaption:	(optional) caption that will be added after task bar if CaptionType set to "Caption"
  * pNotes:	(optional) Detailed task information that will be displayed in tool tip for this task
  * pGantt:	(required) javascript JSGantt.GanttChart object from which to take settings. Defaults to "g" for backwards compatibility
  *
  **/

@js.native
@JSGlobal("JSGantt.TaskItem")
class TaskItem(
                val pID: Double,
                val pName: String,
                val pStart: String | js.Date,
                val pEnd: String | js.Date,
                val pClass: String,
                val pLink: String,
                val pMile: String ,
                val pRes: String,
                val pComp: Int,
                val pGroup: String,
                val pParent: Double,
                val pOpen: Int,
                val pDepend: String,
                val pCaption: String, val pNotes: String,
                val pGantt: GanttChart
              ) extends js.Object

class TaskItemExt(
                   pID: Double,
                   pName: String,
                   pStart: Option[js.Date] = None,
                   pEnd: Option[js.Date] = None,
                   pClass: TaskCssClass,
                   pLink: Option[Link] = None,
                   pMile: Option[MileStone] = None,
                   pRes: Option[String] = None,
                   pComp: Int = 0,
                   pGroup: Option[Group] = None ,
                   pParent: Double = 0,
                   pOpen: Opening = Opening.open,
                   pDepend: Option[Seq[Depend]] = None,
                   pCaption: Option[String] = None,
                   pNotes: Option[String] = None
                 )(implicit pGantt: GanttChartExt) extends TaskItem(
    pID = pID,
    pName = pName,
    pStart = if (pStart.isDefined) pStart.get else "",
    pEnd = if (pEnd.isDefined) pEnd.get else "",
    pClass = pClass.toString,
    pLink = if (pLink.isDefined) pLink.get.httpLink else null,
    pMile = if (pMile.isDefined) pMile.get.id.toString else null,
    pRes = pRes.orNull,
    pComp = pComp,
    pGroup = if (pGroup.isDefined) pGroup.get.id.toString else null,
    pParent = pParent,
    pOpen = pOpen.id,
    pDepend = if (pDepend.isDefined) pDepend.get.mkString(",") else null,
    pCaption = pCaption.orNull,
    pNotes = pNotes.orNull,
    pGantt = pGantt


) {
    //    override def toString: String = s"{pID: ${this.pID}, pName: ${this.pName}, pStart: ${this.pStart}, pEnd: ${this.pEnd}: pClass: ${this.pClass}, pLink: ${this.pLink.httpLink}, pMile: ${this.pMile}, pRes: ${this.pRes}, pComp: ${this.pComp}, pGroup: ${this.pGroup}, pParent: ${this.pParentID}, pOpen: ${this.pOpen}, pDepend: ${this.pDepend.toString}, pCaption: ${this.pCaption}, pNotes: ${this.pNotes}"

    override def toString: String = s"{pID: ${0}}"
}

