package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.asp.Parametrs
import com.simplesys.option.ScOption._
import com.simplesys.option.DoubleType._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

import scala.scalajs.js._

class ParametrsProps extends CommonListGridEditorComponentProps {
    type classHandler <: Parametrs
    identifier = "4EEF794F-EE8F-C38D-73D6-CF6F0F5170E4".opt

    //simpleTable = true.opt

    dataSource = DataSourcesJS.aps_params_DS.opt

    editingFields = FormItemsJS.aps_params_FRMITM.opt
    fields = ListGridFiledsJS.aps_params_FLDS.opt

    editWindowProperties = WindowSS(
        new WindowSSProps {
            width = 285
            height = 500
        }
    ).opt
}
