package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Forms.formsItems.SkinBoxItem
import com.simplesys.System.Types.{FormItemComponentType, ValueMap}
import com.simplesys.option.ScOption._
import scalajs.js._

class SkinBoxItemProps extends SelectItemProps {
    type classHandler <: SkinBoxItem

    valueMap = Dictionary(
        "Enterprise" -> "Enterprise",
        "EnterpriseBlue" -> "Enterprise blue",
        "Graphite" -> "Graphite",
        "Tahoe" → "Tahoe",
        "Simplicity" -> "Simplicity",
        "TreeFrog" -> "TreeFrog",
        "BlackOps" -> "Black",
        "Cupertino" -> "Cupertino",
        "Basic" -> "Basic"
    ).opt

    `type` = FormItemComponentType.SkinBoxItem
}
