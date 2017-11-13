package com.simplesys.js.components.asp.props

import com.simplesys.SmartClient.Layout.props.tabSet.TabProps
import com.simplesys.SmartClient.Layout.props.{ChainMasterDetailProps, HLayoutSSProps, TabSetSSProps}
import com.simplesys.SmartClient.System.Tab
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.app
import com.simplesys.app.{OpersType, Tasks}
import com.simplesys.function._
import com.simplesys.js.components.asp.TasksLayout
import com.simplesys.option.ScOption._
import com.simplesys.option.DoubleType._

import scala.scalajs.js._

class TasksLayoutProps extends ChainMasterDetailProps {
    type classHandler <: TasksLayout

    identifier = "6256A539-4BE7-227E-2507-25896B994FC6".opt

    initWidget = {
        (thiz: classHandler, args: IscArray[JSAny]) ⇒
            thiz.Super("initWidget", args)

            val tasks = Tasks.create(new TasksProps {
                identifier = s"${thiz.identifier}_tasks".opt
                width = "20%"
            })
            tasks.showResizeBar = true
            thiz addMember tasks

            val tabSet = TabSetSS.create(
                new TabSetSSProps {
                    identifier = s"${thiz.identifier}_tabSet".opt
                    width = "*"
                    canCloseTabs = false.opt
                    tabs = Seq(
                        Tab(
                            new TabProps {
                                name = "rc".opt
                                pane = OpersType.create(new OpersTypeProps).opt
                                title = "Типы операций".opt
                                icon = app.doctypes.opt
                            }
                        )
                    ).opt
                }
            )
            thiz addMember tabSet

            thiz.getViewState()

    }.toThisFunc.opt
}
