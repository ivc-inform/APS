package com.simplesys.app

import com.simplesys.SmartClient.App.StaticJSCode
import com.simplesys.SmartClient.System.{Canvas, CommonListGridEditorComponent, isc}

import scala.scalajs.js.annotation.JSExportTopLevel

object PrepareJSCode extends StaticJSCode {
    @JSExportTopLevel("CreateAppJS")
    override def createJS(): Unit = {
        isc.defineClass(Parametrs.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(ChangeOver.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(ProdCalendar.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(Rc.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(Result.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(ResultItem.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(Orders.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(GanttChart.getClass.getSimpleName, Canvas.getClass.getSimpleName)
    }
}
