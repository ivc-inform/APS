package com.simplesys

import com.simplesys.gantt.JS.{GanttChart, GanttChartExt, TaskItemExt ⇒ TaskItemExtJS}
import com.simplesys.gantt.JS.GanttChart._

package object gantt {
  implicit def taskItem2JS(ti:TaskItemExt)(implicit pGantt: GanttChartExt):TaskItemExtJS = new TaskItemExtJS(
    pLink = ti.pLink.map(Link(_)),
    pID = ti.pID,
    pGroup = ti.pGroup,
    pName = ti.pName,
    pOpen = ti.pOpen,
    pComp = ti.pComp,
    pRes = ti.pRes,
    pClass = ti.pClass,
    pMile = ti.pMile,
    pNotes = ti.pNotes,
    pParent = ti.pParent.toDouble,
    pStart = ti.pStart,
    pEnd = ti.pEnd,
    pDepend = ti.pDepend,
    pCaption = ti.pCaption
  )
}
