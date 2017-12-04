package com.simplesys

import com.simplesys.gantt.JS.{GanttChart, GanttChartExt, TaskItemExt ⇒ TaskItemExtJS}
import com.simplesys.gantt.JS.GanttChart._

package object gantt {
  implicit def taskItem2JS(ti:TaskItemExt)(implicit pGantt: GanttChartExt):TaskItemExtJS = new TaskItemExtJS(
    pLink = ti.pLink.map(Link(_)).getOrElse(Link()),
    pID = ti.pID,
    pGroup = ti.pGroup.getOrElse(Group.normalTask),
    pName = ti.pName,
    pOpen = ti.pOpen,
    pComp = ti.pComp,
    pRes = ti.pRes,
    pClass = ti.pClass,
    pMile = ti.pMile.getOrElse(MileStone.notMilestone),
    pNotes = ti.pNotes,
    pParent = ti.pParent.toDouble,
    pStart = ti.pStart.toLDTOpt,
    pEnd = ti.pEnd.toLDTOpt,
    pDepend = ti.pDepend,
    pCaption = ti.pCaption
  )
}
