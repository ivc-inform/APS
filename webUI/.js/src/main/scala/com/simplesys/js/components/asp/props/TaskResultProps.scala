package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.props._
import com.simplesys.System.Types.{ListGridEditEvent, SelectionAppearance}
import com.simplesys.js.components.asp.TaskResult
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, ListGridFiledsJS}

class TaskResultProps extends CommonListGridEditorComponentProps {
    type classHandler <: TaskResult

    identifier = "4E45694F-EE8F-C38D-73D6-CF6F0F5170E3".opt

    simpleTable = true.opt

    dataSource = DataSourcesJS.aps_task_result_DS.opt

    fields = ListGridFiledsJS.aps_task_result_FLDS.opt
    editEvent = ListGridEditEvent.none.opt
    selectionAppearance = SelectionAppearance.checkbox.opt

    itemsType = Seq(miNewWithForm(false), miCopy(false), miDelete(), miEdit(false), miRefresh()).opt
}
