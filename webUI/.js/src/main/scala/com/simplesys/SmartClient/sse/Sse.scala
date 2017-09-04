package com.simplesys.SmartClient.sse

import com.simplesys.SmartClient.System.{IscArray, isc, simpleSyS}
import com.simplesys.SmartClient.sse.Sse.SseCallBack
import com.simplesys.System.JSObject
import com.simplesys.System.Types.Callback
import org.scalajs.dom.{MessageEvent, window}

import scala.scalajs.js


class Sse(val simpleSysContextPath: Option[String] = None) extends JSObject {

    private val eventSources = IscArray[EventSourceSS]()

    def getEventSource(channel: String): IscArray[EventSourceSS] = IscArray(eventSources.filter(_.channelObject.channel == channel): _*)


    private def checkExistsSSE(): Boolean = {
        if (!window.hasOwnProperty("EventSource")) {
            isc error ("Ваш браузер не поддерживает технологию SSE, что делает невозможным автоматическое получение сообщений от сервера. (Данная задача находится в доработке.)")
            false
        } else
            true
    }

    private def checkSimpleSysContextPath(): Boolean = {
        if (simpleSysContextPath.isEmpty && simpleSyS.simpleSysContextPath.isEmpty) {
            isc error ("simpleSysContextPath undefined")
            false
        } else
            true

    }

    private def messagingSubscribeURL() = s"${simpleSysContextPath.getOrElse(simpleSyS.simpleSysContextPath)}Message/Subscribe"

    private def messagingSendURL() = s"${simpleSysContextPath.getOrElse(simpleSyS.simpleSysContextPath)}Message/Send"

    def subscribe(_channel: String, _listener: SseCallBack, subscribeCallback: Option[Callback] = None, _type: String = "message") {
        if (checkExistsSSE() && checkSimpleSysContextPath()) {
            if (isc.Page.isLoaded()) {
                val channelObject = new ChannelObject {
                    override val isChannel: Boolean = true
                    override val channel: String = _channel
                    override val listener: SseCallBack = _listener
                    override val `type`: String = _type
                }

                val eventSource = new EventSourceSS(channelObject, messagingSubscribeURL())
                eventSources push eventSource

                subscribeCallback.foreach(isc.Class.fireCallback(_))
            }
            else
                isc.error("Page not loaded")
        }
    }

    def unsubscribe(channel: String, unsubscribeCallback: Option[Callback] = None) {
        if (checkExistsSSE() && checkSimpleSysContextPath()) {
            getEventSource(channel).map {
                eventSource ⇒
                    eventSource.close()
                    val indexOf = eventSources.map(_.channelObject.channel).indexOf(channel)
                    println(s"indexOf: $indexOf")

                    val resultRemove = eventSources.removeAt(indexOf)
                    println(s"resultRemove: $resultRemove")
                    unsubscribeCallback.foreach(isc.Class.fireCallback(_))
            }
        }
    }

    def unsubscribes(channel: IscArray[String], unsubscribeCallback: Option[Callback] = None): Unit = {
        if (checkExistsSSE() && checkSimpleSysContextPath()) {
            channel.forEach(unsubscribe(_))
            unsubscribeCallback.foreach(isc.Class.fireCallback(_))
        }
    }
}

object Sse {
    type SseCallBack = js.Function1[MessageEvent, _]

    val messaging = new Sse()
}


