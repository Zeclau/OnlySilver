#!/bin/bash

# scripts are from mod_utils project on my GitHub.

ID='onlysilver'
NAME='OnlySilver'
TOPDIR=`pwd`
PROJNAME=`basename $TOPDIR`
if [ $PROJNAME != $NAME ]; then
    echo "Running in wrong directory!"
    exit 1
fi


# recipes
TARGETDIR=${TOPDIR}/src/main/resources/data/${ID}/recipes
if [ ! -d $TARGETDIR ]; then
    mkdir -p $TARGETDIR
fi
cd $TARGETDIR

# Silent's Mechanisms recipes
# crusher -- ingots
make_silents_recipes.py --type=crusher --ingredient="${ID}:silver_ingot" \
    --result="${ID}:silver_dust" silver_dust_from_ingot

# crusher -- ore
make_silents_recipes.py --type=crusher --ticks=400 --ingredient="${ID}:silver_ore" \
    --result "${ID}:crushed_silver_ore,2" "minecraft:cobblestone,0.1" \
    -- crushed_silver_ore

# crusher -- ore chunks
make_silents_recipes.py --type=crusher --ingredient="${ID}:crushed_silver_ore" \
    --result "${ID}:silver_dust" "${ID}:silver_dust,0.1" \
    -- silver_dust

