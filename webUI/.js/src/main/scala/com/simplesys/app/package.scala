package com.simplesys

import com.simplesys.SmartClient.System.SCApply
import com.simplesys.js.components.asp._
import com.simplesys.js.components.asp.props._
import com.simplesys.js.components.gantt.{GanttChart, GanttImprovedChart, GanttImprovedChartTest}
import com.simplesys.js.components.gantt.props.{GanttChartProps, GanttImprovedChartProps, GanttImprovedChartTestProps}

//Должны быть
//import com.simplesys.SmartClient.System._
//import com.simplesys.macros.PropsToDictionary
//

import com.simplesys.SmartClient.System._
import com.simplesys.macros.PropsToDictionary

package object app {
    val loadSchemas = false
    val organization = "organization.png"
    val abonents = "abonents.png"
    val admin = "admin.png"
    val attach = "attach.png"
    val guid = "guid.png"
    val emulateOutMessage = "emulateOutMessage.png"
    val status = "status.png"
    val statusversion = "statusversion.png"
    val mvid = "mvid.png"
    val doctypes = "doctypes.png"
    val card = "card.png"
    val zapros = "zapros.png"
    val docizv = "docizv.png"
    val groupitem = "groupitem.png"
    val docizvtype = "docizvtype.png"
    val format = "format.png"
    val doccats = "doccats.png"
    val docitem = "docitem.png"
    val docliter = "docliter.png"
    val mtype = "mtype.png"
    val state = "state.png"
    val iconAdd = "Add_Column.png"
    val iconNew = "Actions-insert-link-icon.png"
    val iconFolder = "folder.png"
    val iconFile = "file.png"
    val iconRefresh = "Refresh.png"
    val iconTreeNode = "tree_node.png"
    val iconEllipsis = "ellipsis.png"
    val iconFunctions = "function-selection.png"
    val iconStatistiq = "global_manager.png"
    val iconEdit = "edit.png"
    val inventory = "inventory.png"
    val delete_Column = "Delete_Column.png"
    val delete_icon = "Delete-icon.png"
    val iconSave = "Save-icon.png"
    val iconDiscard = "cancel-icon.png"
    val iconOpenFolder = "openFolder.png"
    val iconUnknown = "unknown.png"
    val iconStatistic = "systemservice_SeqGenerator.png"
    val iconConstructor = "Start-Menu-icon.png"
    val iconControl = "ControlReading.png"
    val structure = "structure.png"
    val Actions_insert_link_icon = "Actions-insert-link-icon.png"
    val node = "node.png"
    val admin_User = "admin_User.png"
    val data = "data.png"
    val Help_icon = "Help-icon.png"
    val login = "login.png"
    val tools = "tools.png"
    val insert = "insert.png"
    val Workflow = "Workflow.png"
    val properties = "properties.png"
    val gradient = "gpadient.png"
    val copy = "copy.png"
    val send = "send.png"
    val exportDB = "exportDB.png"
    val systemservice = "systemservice.png"
    val accounts = "accounts.png"
    val export = "export.png"
    val ellipsis = "ellipsis.png"
    val font = "font.png"
    val shadow = "shadow.png"
    val info = "info.png"
    val closeProgram = "Windows-Close-Program-icon.png"
    val approved = "approved.png"
    val settings = "settings.png"
    val ref = "ref.png"
    val ref_RefRefs = "ref_RefRefs.png"
    val copyProduct = "copyproduct.png"
    val cards = "cards.png"
    val reports = "reports.png"
    val recorder = "recorder.png"
    val dictionary = "dictionary.png"
    val admin_UserGroup = "admin_UserGroup.png"
    val Actions_document_edit_icon = "Actions-document-edit-icon.png"

    object ChangeOver extends SCApply[ChangeOver, ChangeOverProps]
    object Parametrs extends SCApply[Parametrs, ParametrsProps]
    object ProdCalendar extends SCApply[ProdCalendar, ProdCalendarProps]
    object Rc extends SCApply[Rc, RcProps]
    object OpersType extends SCApply[OpersType, OpersTypeProps]
    object ResultItem extends SCApply[ResultItem, ResultItemProps]
    object Result extends SCApply[Result, ResultProps]
    object GanttChart extends SCApply[GanttChart, GanttChartProps]
    object GanttImprovedChartTest extends SCApply[GanttImprovedChartTest, GanttImprovedChartTestProps]
    object GanttImprovedChart extends SCApply[GanttImprovedChart, GanttImprovedChartProps]
    object Orders extends SCApply[Orders, OrdersProps]
    object TasksOrders extends SCApply[TasksOrders, TasksOrdersProps]
    object TasksLayout extends SCApply[TasksLayout, TasksLayoutProps]
    object Tasks extends SCApply[Tasks, TasksProps]
    object TaskResult extends SCApply[TaskResult, TaskResultProps]
}

