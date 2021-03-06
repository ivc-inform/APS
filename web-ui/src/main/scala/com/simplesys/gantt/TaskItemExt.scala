package com.simplesys.gantt

import com.simplesys.gantt.Group.Group
import com.simplesys.gantt.MileStone.MileStone
import com.simplesys.gantt.Opening.Opening
import com.simplesys.gantt.TaskCssClass.TaskCssClass
import io.circe.{Decoder, Encoder}

object TaskItemExt {
    implicit val taskCssClassDecoder: Decoder[TaskCssClass.Value] = Decoder.enumDecoder(TaskCssClass)
    implicit val taskCssClassEncoder: Encoder[TaskCssClass.Value] = Encoder.enumEncoder(TaskCssClass)

    implicit val groupDecoder: Decoder[Group.Value] = Decoder.enumDecoder(Group)
    implicit val groupEncoder: Encoder[Group.Value] = Encoder.enumEncoder(Group)

    implicit val openingDecoder: Decoder[Opening.Value] = Decoder.enumDecoder(Opening)
    implicit val openingEncoder: Encoder[Opening.Value] = Encoder.enumEncoder(Opening)

    implicit val mileStoneDecoder: Decoder[MileStone.Value] = Decoder.enumDecoder(MileStone)
    implicit val mileStoneEncoder: Encoder[MileStone.Value] = Encoder.enumEncoder(MileStone)

    implicit val dependEnDecoder: Decoder[DependEn.Value] = Decoder.enumDecoder(DependEn)
    implicit val dependEnEncoder: Encoder[DependEn.Value] = Encoder.enumEncoder(DependEn)
}

case class TaskItemExt(
                        pID: Long,
                        pName: String,
                        pStart: String = "",
                        pEnd: String = "",
                        pClass: TaskCssClass,
                        pLink: Option[String] = None,
                        pMile: Option[MileStone] = None,
                        pRes: Option[String] = None,
                        pComp: Int = 100,
                        pGroup: Option[Group] = None,
                        pParent: Long = 0,
                        pOpen: Opening = Opening.closed,
                        pDepend: Option[Seq[Depend]] = None,
                        pCaption: Option[String] = None,
                        pNotes: Option[String] = None
                      )
