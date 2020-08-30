package mod.zotmc.onlysilver.content;

import net.minecraft.block.OreBlock;
import net.minecraft.util.BlockRenderLayer;

public class SilverOreBlock extends OreBlock {

	public SilverOreBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

} // end-class
