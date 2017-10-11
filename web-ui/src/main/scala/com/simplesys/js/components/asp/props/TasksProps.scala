package com.simplesys.js.components.asp.props

import akka.actor.Props
import com.simplesys.SmartClient.App.props.CommonTreeListGridEditorComponentProps
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.asp.Tasks
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

import scala.scalajs.js._

class TasksProps extends CommonTreeListGridEditorComponentProps {
    type classHandler <: Tasks

    identifier = "58125E1C-252A-01C4-11A1-667FA3222D3F".opt

    dataSourceList = DataSourcesJS.aps_orders_DS.opt
    dataSourceTree = DataSourcesJS.aps_tasks_DS.opt

    fieldsTree = ListGridFiledsJS.aps_tasks_FLDS.opt
    editingTreeFields = FormItemsJS.aps_tasks_FRMITM.opt

    fieldsList = ListGridFiledsJS.aps_orders_FLDS.opt
    editingListFields = FormItemsJS.aps_orders_FRMITM.opt
}
