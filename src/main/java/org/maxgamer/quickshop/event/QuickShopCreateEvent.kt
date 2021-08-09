package org.maxgamer.quickshop.event

import net.wdsj.servercore.utils.extensions.BukkitCancellable
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable

/**
 * @author  Arthur
 * @date  2021/5/8 16:42
 * @version 1.0
 */
class QuickShopCreateEvent(var player: Player) : QuickShopEvent(), Cancellable by BukkitCancellable() {


}