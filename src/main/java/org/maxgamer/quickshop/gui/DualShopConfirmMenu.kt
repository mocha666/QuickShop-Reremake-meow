package org.maxgamer.quickshop.gui

import mc233.cn.wdsjlib.bukkit.gui.CustomGuiManager
import mc233.cn.wdsjlib.global.common.itemstack.ItemCommonBuilder
import net.wdsj.mcserver.gui.common.GuiManager
import net.wdsj.mcserver.gui.common.gui.menu.GuiMenuStatic
import net.wdsj.mcserver.gui.common.item.GuiItemCommon
import net.wdsj.servercore.compatible.XMaterial
import net.wdsj.servercore.eunm.inventory.InventoryAction
import net.wdsj.servercore.eunm.inventory.InventoryType
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.maxgamer.quickshop.shop.Info
import org.maxgamer.quickshop.shop.Shop

/**
 * @author  MeowRay
 * @date  2021/8/7 15:55
 * @version 1.0
 */
class DualShopConfirmMenu(player: Player, shop: Shop, val info: Info) : GuiMenuStatic<Player, ItemStack>(player,
    InventoryType.GENERIC_9X3,
    CustomGuiManager.getTitleDomainPrefix() + "购买类型") {
    private val m = inventoryType.size / 2


    init {
        guiMenuProp.clickSound = null

        setItem(m, GuiItemCommon(ItemCommonBuilder.createFromBukkitItemStack(shop.item)))
        setItem(m - 2, GuiItemCommon<Player, ItemStack>(ItemCommonBuilder(XMaterial.GREEN_WOOL).apply {
            display = "§a购买"
            addLore("§e价格: ${info.shop?.sellPrice} 元/单位")
        }).addActionExecutor(InventoryAction.LEFT) {
            GuiManager.open(it,
                ShopPurchaseSellSign(it, info))
        })
        setItem(m + 2, GuiItemCommon<Player, ItemStack>(ItemCommonBuilder(XMaterial.YELLOW_WOOL).apply {
            display = "§e出售"
            addLore("§e价格: ${info.shop?.price} 元/单位")
        }).addActionExecutor(InventoryAction.LEFT) {
            GuiManager.open(it,
                ShopPurchaseBuySign(it, info))
        })
    }
}