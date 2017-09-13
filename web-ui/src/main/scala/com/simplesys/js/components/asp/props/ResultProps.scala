package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.props._
import com.simplesys.SmartClient.DataBinding.props.{AdvancedCriteriaProps, CriterionProps}
import com.simplesys.SmartClient.System.{AdvancedCriteria, Criterion}
import com.simplesys.System.JSAny
import com.simplesys.System.Types.{ListGridEditEvent, OperatorId}
import com.simplesys.app.ResultItem
import com.simplesys.function._
import com.simplesys.js.components.asp.Result
import com.simplesys.option.ScOption._
import com.simplesys.option.DoubleType._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._
import ru.simplesys.defs.app.scala.container.math.ResultDataRecord

class ResultProps extends CommonListGridEditorComponentProps {
    type classHandler <: Result
    identifier = "4EEF794F-EE8F-C38D-73D6-CF6F0F5170E5".opt

    //simpleTable = true.opt

    dataSource = DataSourcesJS.math_result_DS.opt

    editingFields = FormItemsJS.math_result_FRMITM.opt
    fields = ListGridFiledsJS.math_result_FLDS.opt

    editEvent = ListGridEditEvent.none.opt
    canExpandRecords = true.opt

    itemsType = Seq(miNewWithForm(false), miCopy(false), miDelete(false), miEdit(false), miRefresh()).opt

    getExpansionComponent = {
        (thiz: classHandler, record: ResultDataRecord) ⇒
            ResultItem.create(
                new ResultItemProps {
                    height = 250
                    initialCriteria = AdvancedCriteria(
                        new AdvancedCriteriaProps {
                            operator = OperatorId.and.opt
                            criteria = Seq(
                                Criterion(
                                    new CriterionProps {
                                        fieldName =  math_result_id_NameStrong.name.opt
                                        operator = OperatorId.equals.opt
                                        value = record.id.getOrElse(0).asInstanceOf[JSAny].opt
                                    })
                            ).opt
                        }
                    ).opt
                }
            )
    }.toThisFunc.opt
}
