package mod.zotmc.onlysilver.helpers;

import mod.alexndr.simplecorelib.helpers.InjectionTableLookup;

@SuppressWarnings("serial")
public class OnlySilverInjectionLookup extends InjectionTableLookup
{

    public OnlySilverInjectionLookup()
    {
        super();
        this.put("abandoned_mineshaft", "abandoned_mineshaft");
        this.put("desert_pyramid", "desert_pyramid");
        this.put("simple_dungeon", "simple_dungeon");
        this.put("simple_dungeon", "stronghold_crossing");
        this.put("simple_dungeon", "stronghold_corridor");
        this.put("buried_treasure", "buried_treasure");
        this.put("village_fletcher", "village_fletcher");
        this.put("village_temple", "village_temple");
    } // end ctor

} // end-class
