package ipsis.buildersguides.block;

import ipsis.buildersguides.common.UnlistedPropertyBoolean;
import ipsis.buildersguides.item.ItemMallet;
import ipsis.buildersguides.manager.MarkerManager;
import ipsis.buildersguides.manager.MarkerType;
import ipsis.buildersguides.init.ModItems;
import ipsis.buildersguides.item.ItemMarkerCard;
import ipsis.buildersguides.tileentity.TileEntityMarker;
import ipsis.buildersguides.util.ItemStackHelper;
import ipsis.buildersguides.util.WorldHelper;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

public class BlockMarker extends BlockContainerBG {

    public static final String BASENAME = "marker";

    public static final UnlistedPropertyBoolean NORTH = new UnlistedPropertyBoolean("NORTH");
    public static final UnlistedPropertyBoolean SOUTH = new UnlistedPropertyBoolean("SOUTH");
    public static final UnlistedPropertyBoolean EAST = new UnlistedPropertyBoolean("EAST");
    public static final UnlistedPropertyBoolean WEST = new UnlistedPropertyBoolean("WEST");
    public static final UnlistedPropertyBoolean UP = new UnlistedPropertyBoolean("UP");
    public static final UnlistedPropertyBoolean DOWN = new UnlistedPropertyBoolean("DOWN");

    public BlockMarker() {
        super(Material.ground, BASENAME);
    }

    @Override
    protected BlockState createBlockState() {
        IUnlistedProperty[] unlistedProperties = new IUnlistedProperty[]{
                NORTH, SOUTH, EAST, WEST, UP, DOWN
        };
        return new ExtendedBlockState(this, new IProperty[0], unlistedProperties);
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
        if (state instanceof IExtendedBlockState) {
            IExtendedBlockState extendedBlockState = (IExtendedBlockState)state;
            TileEntityMarker te = (TileEntityMarker)world.getTileEntity(pos);
            boolean north = te.hasValidV(EnumFacing.NORTH);
            boolean south = te.hasValidV(EnumFacing.SOUTH);
            boolean east = te.hasValidV(EnumFacing.EAST);
            boolean west = te.hasValidV(EnumFacing.WEST);
            boolean up = te.hasValidV(EnumFacing.UP);
            boolean down = te.hasValidV(EnumFacing.DOWN);

            return extendedBlockState
                    .withProperty(NORTH, north)
                    .withProperty(SOUTH, south)
                    .withProperty(EAST, east)
                    .withProperty(WEST, west)
                    .withProperty(UP, up)
                    .withProperty(DOWN, down);
        }

        return state;
    }

    @Override
    public int getRenderType() { return 3; }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        return new TileEntityMarker();
    }

    @Override
    public boolean isOpaqueCube() {

        return false;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

        TileEntity te = worldIn.getTileEntity(pos);
        if (te != null && te instanceof TileEntityMarker) {
            if (((TileEntityMarker) te).getType() != MarkerType.BLANK)
                ItemStackHelper.spawnInWorld(worldIn, pos, ItemMarkerCard.getItemStack(((TileEntityMarker) te).getType()));
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

        TileEntity te = worldIn.getTileEntity(pos);
        if (te != null && te instanceof TileEntityMarker) {
            ((TileEntityMarker) te).setFacing(BlockPistonBase.getFacingFromEntity(worldIn, pos, placer));
            worldIn.markBlockForUpdate(pos);
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {

        TileEntity te = worldIn.getTileEntity(pos);
        if (te == null || !(te instanceof TileEntityMarker))
            return false;

        ItemStack heldItem = playerIn.getCurrentEquippedItem();
        if (heldItem == null || heldItem.getItem() != ModItems.itemMallet)
            return false;

        if (WorldHelper.isServer(worldIn)) {
            // side is the side of the block hit, but we want to act on the opposite side
            // ie. hitting the front face, pushes blocks out the back
            ItemMallet.MalletMode currMode = ItemMallet.getMode(heldItem);
            MarkerType t = ((TileEntityMarker) te).getType();
            MarkerManager.handleMalletMode(worldIn, (TileEntityMarker) te, playerIn, side.getOpposite(), currMode);
        }

        return true;
    }

    @Override
    public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {

        TileEntity te = world.getTileEntity(pos);
        if (te == null || !(te instanceof TileEntityMarker))
            return false;

        if (WorldHelper.isServer(world)) {
            ((TileEntityMarker) te).rotateTile(axis);
            return true;
        }

        return false;
    }
}
