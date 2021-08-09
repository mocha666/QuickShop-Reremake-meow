package org.maxgamer.quickshop.gui

import net.wdsj.mcserver.gui.common.gui.sign.GuiSign
import org.bukkit.entity.Player
import org.maxgamer.quickshop.QuickShop
import org.maxgamer.quickshop.shop.Info
import org.maxgamer.quickshop.shop.PurchaseAction
import org.maxgamer.quickshop.util.MsgUtil
import org.maxgamer.quickshop.util.Util

/**
 * @author  MeowRay
 * @date  2021/8/7 15:58
 * @version 1.0
 */
class ShopPurchaseBuySign(val player: Player, val info: Info) : GuiSign<Player>(player, "", "请输入售卖的个数", "价格: ${info.shop?.price} 元/单位") {
    init {
        addExecutor { handler, lines ->
            if (lines[0].isBlank()) {
                info.shop?.price
                MsgUtil.sendMessage(player, "shop-purchase-cancelled")
            } else {
                Util.mainThreadRun {
                    QuickShop.getInstance().shopManager.actionTrade(player, info, PurchaseAction.BUY, lines[0])
                }
            }
            return@addExecutor true
        }
    }
}

class ShopPurchaseSellSign(val player: Player, val info: Info) : GuiSign<Player>(player, "", "请输入购买的个数", "价格: ${info.shop?.sellPrice} 元/单位") {
    init {
        addExecutor { handler, lines ->
            if (lines[0].isBlank()) {
                MsgUtil.sendMessage(player, "shop-purchase-cancelled")
            } else {
                Util.mainThreadRun {
                    QuickShop.getInstance().shopManager.actionTrade(player,
                        info,
                        PurchaseAction.SELL,
                        lines[0])
                }
            }
            return@addExecutor true

        }
    }
}