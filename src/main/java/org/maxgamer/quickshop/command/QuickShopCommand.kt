package org.maxgamer.quickshop.command

import net.wdsj.servercore.common.command.WdsjCommand
import net.wdsj.servercore.common.command.anntations.GlobalCommand
import net.wdsj.servercore.common.command.anntations.SubCommand
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.util.BlockIterator
import org.maxgamer.quickshop.QuickShop
import org.maxgamer.quickshop.command.subcommand.*
import org.maxgamer.quickshop.shop.ShopType
import org.maxgamer.quickshop.util.MsgUtil
import org.maxgamer.quickshop.util.Util

/**
 * @author  MeowRay
 * @date  2021/8/7 15:12
 * @version 1.0
 */
@GlobalCommand(permission = "quickshop" , subCmdPerm = true )
class QuickShopCommand : WdsjCommand<CommandSender> {

    var plugin: QuickShop = QuickShop.getInstance()

    @SubCommand(alias = ["b"], permission = "create.buy", player = true, help = "将商店切换为收购模式")
    fun buy(sender: Player) {
        SubCommand_Buy.instance.onCommand(sender, "" , emptyArray())
    }

    @SubCommand(alias = ["b"], permission = "create.buy", player = true, help = "将商店切换为收购模式", hide =  true)
    fun silentbuy(sender: Player , args: Array<String>) {
        SubCommand_SilentBuy.instance.onCommand(sender, "" ,args)
    }

    @SubCommand(alias = ["s"], permission = "create.sell", player = true, help = "将商店切换为出售模式")
    fun sell(sender: Player) {
        SubCommand_Sell.instance.onCommand(sender, "" , emptyArray())
    }

    @SubCommand(permission = "remove", player = true, hide =  true)
    fun silentremove(sender: Player , args: Array<String>) {
        SubCommand_SilentRemove.instance.onCommand(sender, "" , args)
    }
    @SubCommand(alias = ["s"], permission = "create.sell", player = true, help = "将商店切换为出售模式" , hide =  true)
    fun silentSell(sender: Player , args: Array<String>) {
        SubCommand_SilentSell.instance.onCommand(sender, "" , args)
    }

    @SubCommand(permission = "create.dual", player = true, help = "切换为双向模式")
    fun dual(sender: Player) {
        val bIt = BlockIterator(sender, 10)

        if (!bIt.hasNext()) {
            MsgUtil.sendMessage(sender, "not-looking-at-shop")
            return
        }

        while (bIt.hasNext()) {
            val b = bIt.next()
            val shop = plugin.shopManager.getShop(b.location)
            if (shop != null) {
                if (shop.moderator.isModerator(sender.uniqueId) || QuickShop.getPermissionManager()
                        .hasPermission(sender, "quickshop.other.control")
                ) {
                    shop.shopType = ShopType.DUALING
                    shop.update()
                    MsgUtil.sendMessage(sender, "command.now-dualing", Util.getItemStackName(shop.item))
                } else {
                    MsgUtil.sendMessage(sender, "not-managed-shop")
                }
                return
            }
        }
        MsgUtil.sendMessage(sender, "not-looking-at-shop")
    }

    @SubCommand(alias = ["p"], command = "/{cmd} {name} <价格>", permission = "create.changeprice", help = "修改商店价格")
    fun price(sender: Player, price: Double) {
        SubCommand_Price.call(sender, "", SubCommand_Price.PriceType.BUY_OR_SELL, arrayOf(price.toString()));
    }

    @SubCommand(alias = ["sp"],
        command = "/{cmd} {name} <价格>",
        permission = "create.changeSellPrice",
        help = "修改商店售卖价格")
    fun sellPrice(sender: Player, price: Double) {
        SubCommand_Price.call(sender, "", SubCommand_Price.PriceType.SELL, arrayOf(price.toString()));
    }

    @SubCommand
    fun silentpreview(sender: Player, args: Array<String> ) {
        SubCommand_SilentPreview.instance.onCommand(sender, "", args)
    }

}