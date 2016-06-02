package zotmc.onlysilver.config;

import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableInt;

import zotmc.onlysilver.Contents;
import zotmc.onlysilver.ItemFeature;
import zotmc.onlysilver.config.AbstractConfig.Property;
import zotmc.onlysilver.config.gui.AbstractBooleanRow;
import zotmc.onlysilver.config.gui.AbstractConfigFactory;
import zotmc.onlysilver.config.gui.BasicOreSettingsLayout;
import zotmc.onlysilver.config.gui.Element;
import zotmc.onlysilver.config.gui.EmptyRow;
import zotmc.onlysilver.config.gui.Holder;
import zotmc.onlysilver.config.gui.Icon;
import zotmc.onlysilver.config.gui.IconButton;
import zotmc.onlysilver.config.gui.IconButtonLayout;
import zotmc.onlysilver.config.gui.ItemIcon;
import zotmc.onlysilver.config.gui.Row;
import zotmc.onlysilver.config.gui.SliderRow;
import zotmc.onlysilver.config.gui.SpriteIcon;
import zotmc.onlysilver.config.gui.TextFieldRow;
import zotmc.onlysilver.config.gui.TinyCheckbox;
import zotmc.onlysilver.config.gui.ValueInjectionRow;
import zotmc.onlysilver.data.LangData;
import zotmc.onlysilver.data.ModData.MoCreatures;
import zotmc.onlysilver.oregen.ExtSettings;
import zotmc.onlysilver.util.Utils;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;

@SideOnly(Side.CLIENT)
public class ConfigGui extends AbstractConfigFactory {

  private static final ResourceLocation inventoryBackground = new ResourceLocation("textures/gui/container/inventory.png");
  private Config temp;
  private boolean hide, tool, armor;

  @Override public void create() {
    Config.inFile().loadFromFile();
    temp = Config.inFile().copy();
    hide = true;
  }

  @Override protected void reset() {
    temp.clear();
    tool = anyTool();
    armor = anyArmor();
  }

  @Override public void save() {
    Config.inFile().apply(temp);
    temp.saveToFile();
  }

  @Override public void destroy() {
    temp = null;
  }

  @Override protected Supplier<String> getTitle() {
    return LangData.ONLYSILVER_OPTIONS;
  }

  @Override protected Iterable<? extends Element> getExtraElements(int w, int h, Holder<List<String>> hoveringText) {
    Mutable<Boolean> state = new Mutable<Boolean>() {
      @Override public Boolean getValue() {
        return !hide;
      }
      @Override public void setValue(Boolean value) {
        hide = !value;
      }
    };
    return ImmutableList.of(new TinyCheckbox(state, Utils.<Boolean>doNothing()).setLeftTop(w / 2 + 48, 14));
  }

  @Override public Iterable<Row> getUpperRows(int w, Holder<List<String>> hoveringText) {
    List<Row> rows = Lists.newArrayList();

    rows.add(new ItemIcon(Blocks.gravel).categoryRow(LangData.ORE_GENERATION));

    rows.add(new GenDefaultsRow() {
      @Override protected Property<GenDefaults> toProperty(Config t) {
        return t.silverGenDefaults;
      }
      @Override protected Supplier<String> title() {
        return LangData.SILVER_GEN_DEFAULTS;
      }
    });

    rows.add(EmptyRow.INSTANCE);

    rows.add(new ItemIcon(Items.enchanted_book).setRenderEffect(false).categoryRow(LangData.ENCHANTMENTS));

    rows.add(new EnchantmentRow() {
      @Override protected Property<Integer> toProperty(Config t) {
        return t.silverAuraId;
      }
      @Override protected Supplier<String> title() {
        return LangData.SILVER_AURA;
      }
    });

    rows.add(new EnchantmentRow() {
      @Override protected Property<Integer> toProperty(Config t) {
        return t.incantationId;
      }
      @Override protected Supplier<String> title() {
        return LangData.INCANTATION;
      }
    });

    rows.add(EmptyRow.INSTANCE);

    rows.add(new SpriteIcon(GuiScreen.statIcons, 36, 18, 128).categoryRow(LangData.STATS));

    rows.add(new BlockStatsRow() {
      @Override protected Property<BlockStats> toProperty(Config t) {
        return t.silverOreStats;
      }
      @Override protected Supplier<Block> getBlock() {
        return Contents.silverOre;
      }
    });

    rows.add(new BlockStatsRow() {
      @Override protected Property<BlockStats> toProperty(Config t) {
        return t.silverBlockStats;
      }
      @Override protected Supplier<Block> getBlock() {
        return Contents.silverBlock;
      }
    });

    tool = anyTool();
    rows.add(new ToolStatsRow() {
      @Override protected Property<ToolStats> toProperty(Config t) {
        return t.silverToolStats;
      }
      @Override protected Supplier<String> title() {
        return LangData.SILVER_TOOLS;
      }
      @Override public boolean folded() {
        return hide && !tool;
      }
    });

    armor = anyArmor();
    rows.add(new ArmorStatsRow() {
      @Override protected Property<ArmorStats> toProperty(Config t) {
        return t.silverArmorStats;
      }
      @Override protected Supplier<String> title() {
        return LangData.SILVER_ARMOR;
      }
      @Override public boolean folded() {
        return hide && !armor;
      }
    });

    rows.add(EmptyRow.INSTANCE);

    rows.add(new ItemIcon(Items.lava_bucket).categoryRow(LangData.MISCELLANEOUS));

    rows.add(new FeatureSetRow() {
      @Override protected Property<Set<String>> toProperty(Config t) {
        return t.disabledFeatures;
      }
      @Override protected Supplier<String> title() {
        return LangData.OPTIONAL_FEATURES;
      }
    });

    rows.add(new BooleanRow() {
      final boolean bow = ItemFeature.silverBow.enabled(temp);

      @Override protected Property<Boolean> toProperty(Config t) {
        return t.meleeBowKnockback;
      }
      @Override protected Supplier<String> title() {
        return LangData.MELEE_BOW_KNOCKBACK;
      }
      @Override public boolean folded() {
        return hide && !bow;
      }
    });

    rows.add(new BooleanRow() {
      final boolean moc = Loader.isModLoaded(MoCreatures.MODID);

      @Override protected Property<Boolean> toProperty(Config t) {
        return t.werewolfEffectiveness;
      }
      @Override protected Supplier<String> title() {
        return LangData.WEREWOLF_EFFECTIVENESS;
      }
      @Override public boolean folded() {
        return hide && !moc;
      }
    });

    rows.add(new BooleanRow() {
      @Override protected Property<Boolean> toProperty(Config t) {
        return t.silverGolemAssembly;
      }
      @Override protected Supplier<String> title() {
        return LangData.SILVER_GOLEM_ASSEMBLY;
      }
    });

    rows.add(EmptyRow.INSTANCE);

    return rows;
  }

  private boolean anyTool() {
    for (ItemFeature i : ItemFeature.values())
      if (i.isTool() && i.enabled(temp)) return true;
    return false;
  }

  private boolean anyArmor() {
    for (ItemFeature i : ItemFeature.values())
      if (i.isArmor() && i.enabled(temp)) return true;
    return false;
  }



  private abstract class BooleanRow extends AbstractBooleanRow<Config> {
    // config
    @Override protected Config getTemp() {
      return temp;
    }
    @Override protected Boolean getRawValue(Property<Boolean> p) {
      return p.getRaw();
    }
    @Override protected void setRawValue(Property<Boolean> p, Boolean v) {
      p.setRaw(v);
    }
  }


  private static abstract class LogSliderRow extends SliderRow {
    protected static final double A = Math.log(0.3819660112501051);
  }

  private static abstract class IntSliderRow extends LogSliderRow {
    private static final double B = Math.log(Integer.MAX_VALUE);
    private final int defaultValue;
    private final double k, l;

    public IntSliderRow(int defaultValue) {
      this.defaultValue = defaultValue;
      k = A / (-B + Math.log(Math.max(1, defaultValue)));
      l = Math.pow(Integer.MAX_VALUE, k);
    }
    protected abstract int getValue();
    protected abstract void setValue(int value);

    // slider
    @Override public String getText() {
      int value = getValue();
      String s = Integer.toString(value);
      return value != defaultValue ? s : LangData.DEFAULT.toString(s);
    }
    @Override public double getPosition() {
      return Math.pow(getValue(), k) / l;
    }
    @Override public void setPosition(double position) {
      setValue((int) Math.rint(Math.exp(B + 1/k * Math.log(position))));
    }
    @Override public void previous() {
      setValue(getValue() - 1);
    }
    @Override public void next() {
      setValue(getValue() + 1);
    }
  }

  private static abstract class FloatSliderRow extends LogSliderRow {
    private static final double B = Math.log(Float.MAX_VALUE);
    private final float defaultValue;
    private final double step, k, l;

    public FloatSliderRow(float defaultValue, double step) {
      this.defaultValue = defaultValue;
      this.step = step;
      k = A / (-B + Math.log(Math.max(1, defaultValue)));
      l = Math.pow(Float.MAX_VALUE, k);
    }
    protected abstract float getValue();
    protected abstract void setValue(float value);

    // slider
    @Override public String getText() {
      float value = getValue();
      String s = Float.toString(value);
      return value != defaultValue ? s : LangData.DEFAULT.toString(s);
    }
    @Override public double getPosition() {
      return Math.pow(getValue(), k) / l;
    }
    @Override public void setPosition(double position) {
      setValue((float) Math.exp(B + 1/k * Math.log(position)));
    }
    @Override public void previous() {
      setValue(Utils.previous(getValue(), step));
    }
    @Override public void next() {
      setValue(Utils.next(getValue(), step));
    }
  }

  private abstract class EnchantmentRow extends SliderRow {
    // row
    @Override protected int widgetPos(int k) {
      return k * 7 / 15;
    }

    // config
    protected abstract Property<Integer> toProperty(Config t);

    private int getValue() {
      return toProperty(temp).get();
    }
    private void setValue(int v) {
      toProperty(temp).set(v);
    }

    // slider
    @Override public String getText() {
      int v = getValue();
      if (v == -1) return LangData.DISABLED.get();
      String s = "ID: " + v;
      Enchantment ench = Enchantment.getEnchantmentById(v);
      return ench == null ? s : s + " / " + I18n.format(ench.getName());
    }

    @Override public double getPosition() {
      return (1 + getValue()) / 256.0;
    }
    @Override public void setPosition(double position) {
      setValue((int) (position * 256) - 1);
    }
    @Override public void previous() {
      setValue(getValue() - 1);
    }
    @Override public void next() {
      setValue(getValue() + 1);
    }
  }


  private abstract class EditorRow<V> extends ValueInjectionRow<Config, V> {
    // config
    @Override protected Config getTemp() {
      return temp;
    }
    @Override protected void setValue(Property<V> p, V v) {
      p.set(v);
    }
  }

  private abstract class GenDefaultsRow extends EditorRow<GenDefaults> {
    String dimensions;
    MutableInt size, count, minHeight, maxHeight;

    @Override protected void injectValue(GenDefaults v) {
      String s = v.dimensions;
      dimensions = s != null ? s : ExtSettings.DEFAULT_DIMS.regex;
      size = new MutableInt(v.size);
      count = new MutableInt(v.count);
      minHeight = new MutableInt(v.minHeight);
      maxHeight = new MutableInt(v.maxHeight);
    }
    @Override protected GenDefaults toImmutable() {
      String s = !Objects.equal(dimensions, ExtSettings.DEFAULT_DIMS.regex) ? dimensions : null;
      return new GenDefaults(s, size.intValue(), count.intValue(), minHeight.intValue(), maxHeight.intValue());
    }

    @Override public Iterable<? extends Row> getUpperRows(int w, Holder<List<String>> hoveringText, GenDefaults defaultValue) {
      List<Row> upper = ImmutableList.<Row>of(new TextFieldRow() {
        @Override public String getText() {
          return dimensions;
        }
        @Override public void setText(String text) {
          dimensions = text;
        }
        @Override protected Supplier<String> title() {
          return LangData.DIMENSIONS.append(":");
        }
        @Override protected int widgetPos(int k) {
          return k * 1 / 3;
        }
      });

      Iterable<Row> lower = new BasicOreSettingsLayout() {
        @Override protected MutableInt size() {
          return size;
        }
        @Override protected MutableInt count() {
          return count;
        }
        @Override protected MutableInt minHeight() {
          return minHeight;
        }
        @Override protected MutableInt maxHeight() {
          return maxHeight;
        }
      };

      return Iterables.concat(upper, lower);
    }
  }

  private abstract class BlockStatsRow extends EditorRow<BlockStats> {
    private final ItemIcon icon = new ItemIcon(getBlock().get());
    private int harvestLevel;
    private float hardness, resistance;

    // title
    protected abstract Supplier<Block> getBlock();

    @Override protected Icon<?> icon() {
      return icon;
    }
    @Override protected Supplier<String> title() {
      return icon;
    }

    // value
    @Override protected void injectValue(BlockStats v) {
      harvestLevel = v.harvestLevel;
      hardness = v.hardness;
      resistance = v.resistance;
    }
    @Override protected BlockStats toImmutable() {
      return new BlockStats(harvestLevel, hardness, resistance);
    }

    // screen
    @Override public Iterable<? extends Row> getUpperRows(int w, Holder<List<String>> hoveringText, final BlockStats defaultValue) {
      List<Row> ret = Lists.newArrayList();

      if (defaultValue.harvestLevel >= 0)
        ret.add(new IntSliderRow(defaultValue.harvestLevel) {
          final Icon<?> icon = new ItemIcon(Items.diamond, Icon.PHIM1).overlay(Blocks.stone);

          @Override protected Icon<?> icon() {
            return icon;
          }
          @Override protected Supplier<String> title() {
            return LangData.HARVEST_LEVEL;
          }

          @Override protected int getValue() {
            return harvestLevel;
          }
          @Override protected void setValue(int value) {
            harvestLevel = value;
          }
        });

      ret.add(new FloatSliderRow(defaultValue.hardness, 0.05) {
        final Icon<?> icon = new ItemIcon(Items.iron_pickaxe, Icon.PHIM1).overlay(Blocks.stone);

        @Override protected Icon<?> icon() {
          return icon;
        }
        @Override protected Supplier<String> title() {
          return LangData.HARDNESS;
        }

        @Override protected float getValue() {
          return hardness;
        }
        @Override protected void setValue(float value) {
          hardness = value;
        }
      });

      ret.add(new FloatSliderRow(defaultValue.resistance, 0.5) {
        final Icon<?> icon = new ItemIcon(Items.gunpowder, Icon.PHIM1).overlay(Blocks.stone);

        @Override protected Icon<?> icon() {
          return icon;
        }
        @Override protected Supplier<String> title() {
          return LangData.RESISTANCE;
        }

        @Override protected float getValue() {
          return resistance;
        }
        @Override protected void setValue(float value) {
          resistance = value;
        }
      });

      return ret;
    }
  }

  private abstract class ToolStatsRow extends EditorRow<ToolStats> {
    private int harvestLevel, maxUses;
    private float efficiency, damage;
    private int enchantability;

    // value
    @Override protected void injectValue(ToolStats v) {
      harvestLevel = v.harvestLevel;
      maxUses = v.maxUses;
      efficiency = v.efficiency;
      damage = v.damage;
      enchantability = v.enchantability;
    }
    @Override protected ToolStats toImmutable() {
      return new ToolStats(harvestLevel, maxUses, efficiency, damage, enchantability);
    }

    // screen
    @Override public Iterable<? extends Row> getUpperRows(int w, Holder<List<String>> hoveringText, ToolStats defaultValue) {
      List<Row> ret = Lists.newArrayList();

      ret.add(new IntSliderRow(defaultValue.harvestLevel) {
        final Icon<?> icon = new ItemIcon(Items.diamond, Icon.PHIM1).overlay(Items.iron_pickaxe);

        @Override protected Icon<?> icon() {
          return icon;
        }
        @Override protected Supplier<String> title() {
          return LangData.HARVEST_LEVEL;
        }

        @Override protected int getValue() {
          return harvestLevel;
        }
        @Override protected void setValue(int value) {
          harvestLevel = value;
        }
      });

      ret.add(new IntSliderRow(defaultValue.maxUses) {
        final Icon<?> icon = new SpriteIcon(Gui.statIcons, 72, 18, 128);

        @Override protected Icon<?> icon() {
          return icon;
        }
        @Override protected Supplier<String> title() {
          return LangData.MAX_USES;
        }

        @Override protected int getValue() {
          return maxUses;
        }
        @Override protected void setValue(int value) {
          maxUses = value;
        }
      });

      ret.add(new FloatSliderRow(defaultValue.efficiency, 1) {
        final Icon<?> icon = new SpriteIcon(inventoryBackground, 36, 198, 256);

        @Override protected Icon<?> icon() {
          return icon;
        }
        @Override protected Supplier<String> title() {
          return LangData.EFFICIENCY;
        }

        @Override protected float getValue() {
          return efficiency;
        }
        @Override protected void setValue(float value) {
          efficiency = value;
        }
      });

      ret.add(new FloatSliderRow(defaultValue.damage, 0.5) {
        final Icon<?> icon = new SpriteIcon(inventoryBackground, 72, 198, 256);

        @Override protected Icon<?> icon() {
          return icon;
        }
        @Override protected Supplier<String> title() {
          return LangData.DAMAGE;
        }

        @Override protected float getValue() {
          return damage;
        }
        @Override protected void setValue(float value) {
          damage = value;
        }
      });

      ret.add(new IntSliderRow(defaultValue.enchantability) {
        final Icon<?> icon = new ItemIcon(Blocks.enchanting_table);

        @Override protected Icon<?> icon() {
          return icon;
        }
        @Override protected Supplier<String> title() {
          return LangData.ENCHANTABILITY;
        }

        @Override protected int getValue() {
          return enchantability;
        }
        @Override protected void setValue(int value) {
          enchantability = value;
        }
      });

      return ret;
    }
  }

  private abstract class ArmorStatsRow extends EditorRow<ArmorStats> {
    private int durability;
    private int[] reductionAmounts;
    private int enchantability;

    // value
    @Override protected void injectValue(ArmorStats v) {
      durability = v.durability;
      reductionAmounts = Ints.toArray(v.reductionAmounts);
      enchantability = v.enchantability;
    }
    @Override protected ArmorStats toImmutable() {
      int[] r = reductionAmounts;
      return new ArmorStats(durability, r[0], r[1], r[2], r[3], enchantability);
    }

    // screen
    @Override public Iterable<? extends Row> getUpperRows(int w, Holder<List<String>> hoveringText, ArmorStats defaultValue) {
      List<Row> ret = Lists.newArrayList();

      ret.add(new IntSliderRow(defaultValue.durability) {
        final Icon<?> icon = new SpriteIcon(Gui.statIcons, 72, 18, 128);

        @Override protected Icon<?> icon() {
          return icon;
        }
        @Override protected Supplier<String> title() {
          return LangData.DURABILITY;
        }

        @Override protected int getValue() {
          return durability;
        }
        @Override protected void setValue(int value) {
          durability = value;
        }
      });

      ret.add(new IntSliderRow(defaultValue.reductionAmounts.get(0)) {
        final Icon<?> icon = new ItemIcon(Items.chainmail_helmet);

        @Override protected Icon<?> icon() {
          return icon;
        }
        @Override protected Supplier<String> title() {
          return LangData.HELMET_DEFENSE_POINT;
        }

        @Override protected int getValue() {
          return reductionAmounts[0];
        }
        @Override protected void setValue(int value) {
          reductionAmounts[0] = value;
        }
      });

      ret.add(new IntSliderRow(defaultValue.reductionAmounts.get(1)) {
        final Icon<?> icon = new ItemIcon(Items.chainmail_chestplate);

        @Override protected Icon<?> icon() {
          return icon;
        }
        @Override protected Supplier<String> title() {
          return LangData.CHESTPLATE_DEFENSE_POINT;
        }

        @Override protected int getValue() {
          return reductionAmounts[1];
        }
        @Override protected void setValue(int value) {
          reductionAmounts[1] = value;
        }
      });

      ret.add(new IntSliderRow(defaultValue.reductionAmounts.get(2)) {
        final Icon<?> icon = new ItemIcon(Items.chainmail_leggings);

        @Override protected Icon<?> icon() {
          return icon;
        }
        @Override protected Supplier<String> title() {
          return LangData.LEGGINGS_DEFENSE_POINT;
        }

        @Override protected int getValue() {
          return reductionAmounts[2];
        }
        @Override protected void setValue(int value) {
          reductionAmounts[2] = value;
        }
      });

      ret.add(new IntSliderRow(defaultValue.reductionAmounts.get(3)) {
        final Icon<?> icon = new ItemIcon(Items.chainmail_boots);

        @Override protected Icon<?> icon() {
          return icon;
        }
        @Override protected Supplier<String> title() {
          return LangData.BOOTS_DEFENSE_POINT;
        }

        @Override protected int getValue() {
          return reductionAmounts[3];
        }
        @Override protected void setValue(int value) {
          reductionAmounts[3] = value;
        }
      });

      ret.add(new IntSliderRow(defaultValue.enchantability) {
        final Icon<?> icon = new ItemIcon(Blocks.enchanting_table);

        @Override protected Icon<?> icon() {
          return icon;
        }
        @Override protected Supplier<String> title() {
          return LangData.ENCHANTABILITY;
        }

        @Override protected int getValue() {
          return enchantability;
        }
        @Override protected void setValue(int value) {
          enchantability = value;
        }
      });

      return ret;
    }
  }

  private abstract class FeatureSetRow extends EditorRow<Set<String>> {
    Set<String> disabled;

    @Override protected void injectValue(Set<String> v) {
      disabled = Sets.newLinkedHashSet(v);
    }
    @Override protected Set<String> toImmutable() {
      return ImmutableSet.copyOf(disabled);
    }

    @Override public int getRowHeight() {
      return IconButtonLayout.SLOT_SIZE;
    }
    @Override protected Iterable<Row> getLowerRows() {
      return ImmutableList.<Row>of(EmptyRow.INSTANCE);
    }

    @Override public Iterable<? extends Row> getUpperRows(int w, Holder<List<String>> hoveringText, Set<String> defaultValue) {
      List<IconButton> buttons = Lists.newArrayList();
      final Set<IconButton> accessible = Sets.newIdentityHashSet();

      for (ItemFeature i : ItemFeature.values())
        if (!i.important()) {
          IconButton button;
          if (i.exists()) button = new ItemIcon(i.get()).iconButton(enabled(i.getItemId()), hoveringText);
          else {
            button = new SpriteIcon(i.getGuiWatermark(), 0, 0, 16, 16, 16)
              .setAlpha(0x99)
              .iconButton(enabled(i.getItemId()), tooltip(i.getLocalization()), hoveringText);
          }
          if (i.enabled(null)) accessible.add(button);
          buttons.add(button);
        }

      Predicate<IconButton> p = new Predicate<IconButton>() { public boolean apply(IconButton input) {
        return !hide || accessible.contains(input);
      }};
      return new IconButtonLayout(Iterables.filter(buttons, p), w);
    }

    private Mutable<Boolean> enabled(final String key) {
      return new Mutable<Boolean>() {
        @Override public Boolean getValue() {
          return !disabled.contains(key);
        }
        @Override public void setValue(Boolean value) {
          if (value) disabled.remove(key);
          else disabled.add(key);
        }
      };
    }
    private Supplier<List<String>> tooltip(Supplier<String> name) {
      return Suppliers.ofInstance(ItemIcon.colorTooltip(Lists.newArrayList(name.get()), EnumChatFormatting.DARK_GRAY));
    }
  }

}
