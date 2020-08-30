package mod.zotmc.onlysilver.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class ModTags
{
    public static class Items
    {
        public static final ITag.INamedTag<Item> INGOTS_SILVER = forgeTag("ingots/silver");
        public static final ITag.INamedTag<Item> NUGGETS_SILVER = forgeTag("nuggets/silver");
        public static final ITag.INamedTag<Item> BLOCK_SILVER = forgeTag("storage_blocks/silver");
        public static final ITag.INamedTag<Item> RODS_SILVER = forgeTag("rods/silver");
        
        private static ITag.INamedTag<Item> forgeTag(String name) {
            return ItemTags.makeWrapperTag("forge:" + name);
        }
    } // end class Items
    
    
    public static class Blocks
    {
        public static final ITag.INamedTag<Block> BLOCK_SILVER = forgeTag("storage_blocks/silver");
        
        private static ITag.INamedTag<Block> forgeTag(String name) {
            return BlockTags.makeWrapperTag("forge:" + name);
        }
    } // end class Blocks
} // end class ModTags
