package ipsis.buildersguides.init;

import ipsis.buildersguides.block.BlockBG;
import ipsis.buildersguides.block.BlockMarker;
import ipsis.buildersguides.reference.Names;
import ipsis.buildersguides.tileentity.TileEntityMarker;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

    public static void initialize() {

        blockMarker = new BlockMarker();
        GameRegistry.registerBlock(blockMarker, BlockMarker.BASENAME);
        GameRegistry.registerTileEntity(TileEntityMarker.class, "tile." + BlockMarker.BASENAME);
        /* setup recipes */
    }

    public static BlockBG blockMarker;
}
