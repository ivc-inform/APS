package com.simplesys.app

import com.simplesys.SmartClient.App.StaticJSCode
import com.simplesys.SmartClient.System.{Canvas, CommonListGridEditorComponent, CommonTreeGridEditorComponent, CommonTreeListGridEditorComponent, isc}

import scala.scalajs.js.annotation.JSExportTopLevel

object PrepareJSCode extends StaticJSCode {
    @JSExportTopLevel("CreateAppJS")
    override def createJS(): Unit = {
        isc.defineClass(Parametrs.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(ChangeOver.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(ProdCalendar.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(Rc.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(OpersType.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(Result.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(ResultItem.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(Orders.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(TasksOrders.getClass.getSimpleName, CommonTreeListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(Tasks.getClass.getSimpleName, CommonTreeGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(GanttChart.getClass.getSimpleName, Canvas.getClass.getSimpleName)
        isc.defineClass(GanttImprovedChart.getClass.getSimpleName, Canvas.getClass.getSimpleName)
    }
}
