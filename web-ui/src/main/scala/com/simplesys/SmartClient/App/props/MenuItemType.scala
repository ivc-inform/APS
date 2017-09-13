package com.simplesys.SmartClient.App.props

import com.simplesys.System.JSObject

import scala.language.implicitConversions

sealed trait MenuItemType extends JSObject {
    val enabled: Boolean
    val name: String
}

class miNew(val enabled: Boolean = true) extends MenuItemType{
    override val name: String = "new"
}

object miNew {
    def apply(enabled: Boolean = true): miNew = new miNew(enabled)
}

class miNewWithForm(val enabled: Boolean = true) extends MenuItemType{
    override val name: String = "newWithForm"
}

object miNewWithForm {
    def apply(enabled: Boolean = true): miNewWithForm = new miNewWithForm(enabled)
}

class miCopy(val enabled: Boolean = true) extends MenuItemType{
    override val name: String = "copy"
}
object miCopy {
    def apply(enabled: Boolean = true) = new miCopy(enabled)
}

class miEdit(val enabled: Boolean = true) extends MenuItemType{
    override val name: String = "edit"
}
object miEdit {
    def apply(enabled: Boolean = true) = new miEdit(enabled)
}

class miDelete(val enabled: Boolean = true) extends MenuItemType{
    override val name: String = "delete"
}
object miDelete {
    def apply(enabled: Boolean = true) = new miDelete(enabled)
}

class miRefresh(val enabled: Boolean = true) extends MenuItemType{
    override val name: String = "refresh"
}
object miRefresh {
    def apply(enabled: Boolean = true) = new miRefresh(enabled)
}

