package com.ivcInform

package object common {
    implicit class String1Opts(x: String) {
        def ellipsis = s"$x..."
    }
}
