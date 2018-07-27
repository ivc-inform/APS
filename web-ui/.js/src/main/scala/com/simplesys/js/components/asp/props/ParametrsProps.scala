package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.App.formItems.props.LookupTreeGridEditorItemProps
import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.TreeGridEditor
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.RPC.props.RPCRequestProps
import com.simplesys.SmartClient.RPC.{RPCManagerSS, RPCRequest, RPCResponse}
import com.simplesys.SmartClient.System
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.{FormItemComponentType, ListGridFieldType, SelectionAppearance, SelectionStyle}
import com.simplesys.System._
import com.simplesys.app.Tasks
import com.simplesys.circe.Circe._
import com.simplesys.function._
import com.simplesys.isc.dataBinging.DSResponse
import com.simplesys.js.components.asp.Parametrs
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.request.CalculateRequest
import com.simplesys.response.ResponseCalculateData
import io.circe.generic.auto._
import io.circe.scalajs.{convertJsToJson, convertJsonToJs}
import io.circe.syntax._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS, aps_orders_code_task_Id_task_NameStrong}
import ru.simplesys.defs.app.scala.container.ApsParamsDataRecord

class ParametrsProps extends CommonListGridEditorComponentProps {
    type classHandler <: Parametrs
    identifier = "4EEF794F-EE8F-C38D-73D6-CF6F0F5170E4".opt

    simpleTable = true.opt

    dataSource = DataSourcesJS.aps_params_DS.opt

    editingFields = FormItemsJS.aps_params_FRMITM.opt
    fields = ListGridFiledsJS.aps_params_FLDS.opt

    selectionAppearance = SelectionAppearance.checkbox.opt
    selectionType = SelectionStyle.multiple.opt

    editWindowProperties = System.WindowSS(
        new WindowSSProps {
            width = 285
            height = 500
        }
    ).opt

    replacingFields = Seq(
        new ListGridFieldProps {
            nameStrong = aps_orders_code_task_Id_task_NameStrong.opt
            `type` = ListGridFieldType.sCaption_SimpleType.opt
            editorType = FormItemComponentType.LookupTreeGridEditorItem
            editorProperties = LookupTreeGridEditorItem(
                new LookupTreeGridEditorItemProps {
                    treeGridEditor = Tasks.create(new TasksProps).opt
                }).opt
        }
    ).opt

    customMenu = Seq(
        MenuSSItem(
            new MenuSSItemProps {
                title = "Расчет расписания".ellipsis.opt
                identifier = "calculate".opt
                icon = Common.calculator.opt
                click = {
                    (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                        val owner = item.owner.asInstanceOf[TreeGridEditor]
                        simpleSyS checkOwner owner

                        val selectedRecord = owner.getSelectedRecord.asInstanceOf[ApsParamsDataRecord]
                        RPCManagerSS.(
                            RPCRequest(
                                new RPCRequestProps {
                                    actionURL = "logic/calculateTask".opt
                                    data = convertJsonToJs(CalculateRequest(
                                        idTask = selectedRecord.id_task.getOrElse(0.0).toLong,
                                        idParam = selectedRecord.id_params.getOrElse(0.0).toLong).asJson).opt
                                    timeout = 60000.opt
                                    sendNoQueue = true.opt
                                    callback = {
                                        (resp: RPCResponse, data: JSAny, req: RPCRequest) ⇒
                                            if (resp.httpResponseCode == 200) {
                                                convertJsToJson(data) match {
                                                    case Right(json) ⇒
                                                        json.getJsonObject("response").as[DSResponse] match {
                                                            case Right(dsResponse) ⇒
                                                                dsResponse.data.as[ResponseCalculateData] match {
                                                                    case Right(result) ⇒
                                                                        isc ok result.message
                                                                    case Left(failure) ⇒
                                                                        isc error (failure.getMessage)
                                                                }
                                                            case Left(failure) ⇒
                                                                isc error (failure.getMessage)
                                                        }
                                                    case Left(failure) ⇒
                                                        isc error (failure.getMessage)
                                                }
                                            }

                                    }.toFunc.opt
                                }
                            )
                        )
                }.toFunc.opt
            }
        )
    ).opt
}
