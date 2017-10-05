package com.simplesys.js

import scala.collection.mutable
import scala.scalajs.js

package object common {
    implicit class jsArray2ZipSeq[T](arr: js.Array[T]) {
        def toSeqZipWithIndex: mutable.Seq[(T, Int)] = {
            val res: mutable.Seq[T] = arr
            res.zipWithIndex
        }

        def toSeq: mutable.Seq[T] = {
            val res: mutable.Seq[T] = arr
            res
        }
    }
}
