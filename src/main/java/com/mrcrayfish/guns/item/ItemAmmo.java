package com.mrcrayfish.guns.item;

import com.google.gson.annotations.SerializedName;
import com.mrcrayfish.guns.MrCrayfishGunMod;
import com.mrcrayfish.guns.Reference;
import com.mrcrayfish.guns.init.ModGuns;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Author: MrCrayfish
 */
public class ItemAmmo extends Item implements ISubItems
{
    public ItemAmmo()
    {
        this.setUnlocalizedName("ammo");
        this.setRegistryName("ammo");
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(MrCrayfishGunMod.GUN_TAB);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        if(stack.getItemDamage() >= Type.values().length) return super.getUnlocalizedName(stack);
        return super.getUnlocalizedName(stack) + "_" + Type.values()[stack.getItemDamage()].name;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if(isInCreativeTab(tab))
        {
            for (int i = 0; i < Type.values().length; ++i)
            {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    public static ItemStack getAmmo(Type type, int amount)
    {
        amount = Math.min(amount, 64);
        return new ItemStack(ModGuns.AMMO, amount, type.ordinal());
    }

    @Override
    public NonNullList<ResourceLocation> getModels()
    {
        NonNullList<ResourceLocation> modelLocations = NonNullList.create();
        for(Type type : Type.values())
        {
            modelLocations.add(new ResourceLocation(Reference.MOD_ID, "ammo_" + type.name));
        }
        return modelLocations;
    }

    public enum Type
    {
        @SerializedName("basic")
        BASIC("basic"),
        @SerializedName("advanced")
        ADVANCED("advanced"),
        @SerializedName("shell")
        SHELL("shell"),
        @SerializedName("grenade")
        GRENADE("grenade"),
        @SerializedName("missile")
        MISSILE("missile");

        public final String name;

        Type(String name)
        {
            this.name = name;
        }

        @Nullable
        public static Type getType(String name)
        {
            for(Type type : Type.values())
            {
                if(type.name.equals(name))
                {
                    return type;
                }
            }
            return null;
        }
    }
}
