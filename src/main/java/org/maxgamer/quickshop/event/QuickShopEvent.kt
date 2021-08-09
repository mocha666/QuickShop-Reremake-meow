package org.maxgamer.quickshop.event

import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * @author  Arthur
 * @date  2021/5/8 16:41
 * @version 1.0
 */
open class QuickShopEvent() : Event(true) {

    companion object {
        private val handlers = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return handlers
        }
    }


    override fun getHandlers(): HandlerList {
        return Companion.handlers
    }

}