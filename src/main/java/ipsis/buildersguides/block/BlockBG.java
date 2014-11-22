package ipsis.buildersguides.block;

import ipsis.buildersguides.creative.CreativeTab;
import ipsis.buildersguides.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockBG extends Block {

    public BlockBG()
    {
        /* Don't want to require a tool to break the blocks, nor do we want a long break time */
        this(Material.ground);
        this.setHardness(1.0F);
    }

    public BlockBG(Material material)
    {
        super(material);
        this.setCreativeTab(CreativeTab.BG_TAB);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference.MOD_ID + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}