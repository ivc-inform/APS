package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.props.CommonTreeGridEditorComponentProps
import com.simplesys.js.components.asp.Tasks
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.FormItemsJS._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.ListGridFiledsJS._

class TasksProps extends CommonTreeGridEditorComponentProps {
    type classHandler <: Tasks

    identifier = "C4C132D1-7823-F191-BD54-88A8A2238DCC".opt
    
    dataSource = aps_tasks_DS.opt
    fields = aps_tasks_FLDS.opt
    editingFields = aps_tasks_FRMITM.opt
}
