package com.simplesys.SmartClient.Control.props

import com.simplesys.SmartClient.App.props._
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Control.{ListGridContextMenu, MenuSS}
import com.simplesys.SmartClient.DataBinding.DataSource
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.ListGridEditor
import com.simplesys.SmartClient.System
import com.simplesys.SmartClient.System.{Common, simpleSyS, _}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.collection.mutable.ArrayBuffer

object ListGridContextMenuProps {
    def deletePKField(dataSource: DataSource, record: JSObject): JSObject = {
        dataSource.getPrimaryKeyFieldNames().foreach {
            name =>
                isc.deleteProp(record, name)
        }
        record
    }

    def newMenuItemWithForm = MenuSSItem(
        new MenuSSItemProps {
            title = "Новый".ellipsis.opt
            identifier = "new".opt
            icon = Common.iconAdd.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    owner.startEditingNewInForm()
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    true
            }.toFunc.opt
        })

    def newMenuItem(_enabled: Boolean = true) = MenuSSItem(
        new MenuSSItemProps {
            title = "Новый".ellipsis.opt
            identifier = "new".opt
            icon = Common.iconAdd.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    simpleSyS checkOwner owner
                    owner.startEditingNew()
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    _enabled
            }.toFunc.opt
        })

    def copyMenuItem(_enabled: Boolean = true) = MenuSSItem(
        new MenuSSItemProps {
            title = "Копировать".opt
            identifier = "copy".opt
            icon = Common.copy_icon.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    simpleSyS checkOwner owner
                    owner.getSelectedRecords().foreach(record => owner.dataSource.addData(deletePKField(owner.dataSource, record)))
                    owner.fullRefresh()
                    false
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    simpleSyS checkOwner owner
                    _enabled && owner.getSelectedRecords().length > 0
            }.toFunc.opt
        })

    def editMenuItem(_enabled: Boolean = true) = MenuSSItem(
        new MenuSSItemProps {
            title = "Изменить".opt
            identifier = "edit".opt
            icon = Common.Actions_document_edit_icon.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    simpleSyS checkOwner owner
                    owner.startEditing()
                    false
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    simpleSyS checkOwner owner
                    _enabled && owner.getSelectedRecords().length == 1
            }.toFunc.opt
        })

    def deleteMenuItem(_enabled: Boolean = true) = MenuSSItem(
        new MenuSSItemProps {
            title = "Удалить".opt
            identifier = "remove".opt
            icon = Common.delete_icon.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    simpleSyS checkOwner owner
                    isc.ask(simpleSyS.config.confirmDeleting, {
                        (value: Boolean) =>
                            if (value) owner.removeSelectedData()
                    })

                    false
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    simpleSyS checkOwner owner
                    _enabled && owner.getSelectedRecords().length > 0
            }.toFunc.opt
        })

    def refreshMenuItem(_enabled: Boolean = true) = System.MenuSSItem(
        new MenuSSItemProps {
            title = "Обновить".opt
            identifier = "refresh".opt
            icon = "Refresh.png".opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    _enabled
            }.toFunc.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    simpleSyS checkOwner owner
                    owner.fullRefresh()
            }.toFunc.opt
        })

    def separetorMenuItem = MenuSSItem(
        new MenuSSItemProps {
            isSeparator = true.opt
        })

    def otherItems(itemsType: Seq[MenuItemType] = Seq(miNew(), miCopy(), miDelete(), miEdit(), miRefresh())): Seq[MenuSSItem] = {
        val res = ArrayBuffer.empty[MenuSSItem]


        itemsType.find(_.name == miNew().name) match {
            case Some(menuItem) ⇒
                res += newMenuItem(menuItem.enabled)
            case _ ⇒
        }

        itemsType.find(_.name == miCopy().name) match {
            case Some(menuItem) ⇒
                res += copyMenuItem(menuItem.enabled)
            case _ ⇒
        }

        itemsType.find(_.name == miEdit().name) match {
            case Some(menuItem) ⇒
                res += editMenuItem(menuItem.enabled)
            case _ ⇒
        }

        itemsType.find(_.name == miDelete().name) match {
            case Some(menuItem) ⇒ res +=
              deleteMenuItem(menuItem.enabled)
            case _ ⇒
        }

        itemsType.find(_.name == miRefresh().name) match {
            case Some(menuItem) ⇒ res += refreshMenuItem(menuItem.enabled)
            case _ ⇒
        }

        res.toSeq
    }

    def saveMenuItem = MenuSSItem(
        new MenuSSItemProps {
            title = "Сохранить изменения".opt
            identifier = "saveAll".opt
            icon = Common.iconSave.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    simpleSyS checkOwner owner
                    owner.saveAllEdits()

                    false
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    simpleSyS checkOwner owner
                    owner.hasChanges() && !owner.hasErrors()
            }.toFunc.opt
        })

    def cancelMenuItem = MenuSSItem(
        new MenuSSItemProps {
            title = "Отменить изменения".opt
            identifier = "discardAll".opt
            icon = Common.cancel.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    simpleSyS checkOwner owner
                    owner.discardAllEdits()

                    false
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    simpleSyS checkOwner owner
                    owner.hasChanges()
            }.toFunc.opt
        })

    def otherItems1 = Seq(
        separetorMenuItem,
        saveMenuItem,
        cancelMenuItem
    )

    def getCustomMenuItems(customMenu: JSUndefined[IscArray[MenuSSItem]]): Seq[MenuSSItem] = {
        if (customMenu.isEmpty)
            Seq()
        else {
            Seq(
                MenuSSItem(
                    new MenuSSItemProps {
                        isSeparator = true.opt
                    })
            ) ++ customMenu.get
        }
    }
}

class ListGridContextMenuProps extends MenuSSProps {
    type classHandler <: ListGridContextMenu

    var customMenu: ScOption[Seq[MenuSSItem]] = ScNone
    var itemsType: ScOption[Seq[MenuItemType]] = Seq(miNew(), miCopy(), miDelete(), miEdit(), miRefresh()).opt

    initWidget = {
        (thiz: classHandler, args: IscArray[JSAny]) =>

            val items = ListGridContextMenuProps.otherItems(if (thiz.itemsType.isDefined) thiz.itemsType.get else Seq()) ++
              ListGridContextMenuProps.getCustomMenuItems(thiz.customMenu) ++
              ListGridContextMenuProps.otherItems1

            thiz.items = IscArray(items: _*)
            thiz.Super("initWidget", args)
    }.toThisFunc.opt
}

class ListGridContextMenuWithFormProps extends MenuSSProps {
    type classHandler <: ListGridContextMenu

    var customMenu: ScOption[Seq[MenuSSItem]] = ScNone
    var itemsType: ScOption[Seq[MenuItemType]] = Seq(miNew(), miCopy(), miDelete(), miEdit(), miRefresh()).opt

    initWidget = {
        (thiz: classHandler, args: IscArray[JSAny]) =>

            val items = ListGridContextMenuProps.otherItems(if (thiz.itemsType.isDefined) thiz.itemsType.get else Seq()) ++
              ListGridContextMenuProps.getCustomMenuItems(thiz.customMenu) ++
              ListGridContextMenuProps.otherItems1

            thiz.items = IscArray(items: _*)
            thiz.Super("initWidget", args)
    }.toThisFunc.opt
}

