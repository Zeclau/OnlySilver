package mod.zotmc.onlysilver.init;

import mod.alexndr.simplecorelib.helpers.TagUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;

public class ModTags
{
    public static class Items
    {
        public static final ITag.INamedTag<Item> INGOTS_SILVER = TagUtils.forgeTag("ingots/silver");
        public static final ITag.INamedTag<Item> NUGGETS_SILVER = TagUtils.forgeTag("nuggets/silver");
        public static final ITag.INamedTag<Item> BLOCK_SILVER = TagUtils.forgeTag("storage_blocks/silver");
        public static final ITag.INamedTag<Item> RODS_SILVER = TagUtils.forgeTag("rods/silver");
        
    } // end class Items
    
    
    public static class Blocks
    {
        public static final ITag.INamedTag<Block> BLOCK_SILVER = TagUtils.forgeBlockTag("storage_blocks/silver");
    } // end class Blocks
} // end class ModTags
