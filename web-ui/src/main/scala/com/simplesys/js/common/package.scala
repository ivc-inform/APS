package com.simplesys.js

import scala.scalajs.js

package object common {
    implicit class undef[T](value:T) {
        def isUndefigned: Boolean = js.UndefOr.any2undefOrA(value).isEmpty
        def isDefigned: Boolean = js.UndefOr.any2undefOrA(value).isDefined
        def isNull: Boolean = js.UndefOr.any2undefOrA(value).isDefined && (value == null)
    }
}
