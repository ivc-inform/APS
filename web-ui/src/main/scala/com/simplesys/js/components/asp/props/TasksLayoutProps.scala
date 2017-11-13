package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.Layout.props.{ChainMasterDetailProps, HLayoutSSProps, TabSetSSProps}
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.app.Tasks
import com.simplesys.function._
import com.simplesys.js.components.asp.TasksLayout
import com.simplesys.option.ScOption._

import scala.scalajs.js._

class TasksLayoutProps extends ChainMasterDetailProps {
    type classHandler <: TasksLayout

    identifier = "6256A539-4BE7-227E-2507-25896B994FC6".opt

    initWidget = {
        (thiz: classHandler, args: IscArray[JSAny]) ⇒
            thiz.Super("initWidget", args)

            val tasks = Tasks.create(new TasksProps{
                identifier = s"${thiz.identifier}_tasks".opt
            })
            tasks.showResizeBar = true
            thiz addMember tasks

            val tabSet = TabSetSS.create(
                new TabSetSSProps {
                    identifier = s"${thiz.identifier}_tabSet".opt
                }
            )
            thiz addMember tabSet

            thiz.getViewState()

    }.toThisFunc.opt
}
