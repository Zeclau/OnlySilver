package mod.zotmc.onlysilver.init;

import mod.zotmc.onlysilver.OnlySilver;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class ModTags
{

    public static class Items
    {
        public static final Tag<Item> INGOTS_SILVER = forgeTag("ingots/silver");
        public static final Tag<Item> NUGGETS_SILVER = forgeTag("nuggets/silver");
        public static final Tag<Item> BLOCK_SILVER = forgeTag("storage_blocks/silver");
        public static final Tag<Item> ROD_SILVER = forgeTag("rods/silver");
        
//        private static Tag<Item> tag(String name) {
//            return new ItemTags.Wrapper(new ResourceLocation(OnlySilver.MODID, name));
//        }

        private static Tag<Item> forgeTag(String name) {
            return new ItemTags.Wrapper(new ResourceLocation("forge", name));
        }
    } // end class Items
    
    
    public static class Blocks
    {
        public static final Tag<Block> BLOCK_SILVER = forgeTag("storage_blocks/silver");
        
//        private static Tag<Block> tag(String name) {
//            return new BlockTags.Wrapper(new ResourceLocation(OnlySilver.MODID, name));
//        }

        private static Tag<Block> forgeTag(String name) {
            return new BlockTags.Wrapper(new ResourceLocation("forge", name));
        }
    } // end class Blocks

    
} // end class ModTags
