package com.simplesys

import com.simplesys.gantt.JS.{GanttChart, GanttChartExt, TaskItemExt ⇒ TaskItemExtJS}
import com.simplesys.gantt.JS.GanttChart._

package object gantt {
  implicit def taskItem2JS(ti:TaskItemExt)(implicit pGantt: GanttChartExt):TaskItemExtJS = new TaskItemExtJS(
    pLink = ti.pLink.map(Link(_)).getOrElse(Link()),
    pID = ti.pID,
    pGroup = ti.pGroup.getOrElse(Group.normalTask),
    pName = ti.pName,
    pOpen = ti.pOpen.getOrElse(Opening.open),
    pComp = ti.pComp.map(_.toDouble).getOrElse(0.0),
    pRes = ti.pRes.getOrElse(""),
    pClass = ti.pClass,
    pMile = ti.pMile.getOrElse(MileStone.notMilestone),
    pNotes = ti.pNotes.getOrElse(""),
    pParent = ti.pParent.map(_.toDouble).getOrElse(0.0),
    pStart = ti.pStart.map(_.toLDT),
    pEnd = ti.pEnd.map(_.toLDT),
    pDepend = ti.pDepend,
    pCaption = ti.pCaption.getOrElse("")
  )
}
