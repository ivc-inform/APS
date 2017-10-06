package io.udash.wrappers.jqueryui

import scala.scalajs.js

trait ResizableOptions extends ResizableEvents {
  val alsoResize: js.UndefOr[js.Any] = js.undefined

  // Selector, JQuery or Element
  val animate: js.UndefOr[Boolean] = js.undefined
  val animateDuration: js.UndefOr[js.Any] = js.undefined

  // number or string
  val animateEasing: js.UndefOr[String] = js.undefined
  val aspectRatio: js.UndefOr[js.Any] = js.undefined
  
  // boolean or number
  val autoHide: js.UndefOr[Boolean] = js.undefined
  val cancel: String 
  val containment: js.UndefOr[js.Any] = js.undefined

  // Selector, Element or string
  val delay: js.UndefOr[Double] = js.undefined
  val disabled: js.UndefOr[Boolean] = js.undefined
  val distance: js.UndefOr[Double] = js.undefined
  val ghost: js.UndefOr[Boolean] = js.undefined
  val grid: js.UndefOr[js.Any] = js.undefined
  val handles: js.UndefOr[js.Any] = js.undefined


  // string or object
  val helper: js.UndefOr[String] = js.undefined
  val maxHeight: js.UndefOr[Double] = js.undefined
  val maxWidth: js.UndefOr[Double] = js.undefined
  val minHeight: js.UndefOr[Double] = js.undefined
  val minWidth: js.UndefOr[Double] = js.undefined
}
