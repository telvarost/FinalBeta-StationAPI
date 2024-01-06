# FinalBeta StationAPI Edition for Minecraft Beta 1.7.3

A StationAPI mod for Minecraft Beta 1.7.3 that adds some quality of life changes and fixes some graphical issues.

**If you're looking for the Cursed Legacy edition, see this repository:** https://git.pixelatedw.xyz/wynd/finalbeta

**If you're looking for skin fixes and inventory fixes:** https://modrinth.com/mod/mojangfix/versions

**If you're looking for vanilla Minecraft block/entity bug fixes:** https://github.com/telvarost/AnnoyanceFix-StationAPI

# Final Beta

**There's a very high chance this mod will not work in multiplayer, as of right now it is purely for singleplayer**

## List of tweaks from original mod

The following features were not ported since the fix already exists in AnnoyanceFix mod

<details><summary>Updated list of blocks that pickaxes and axes can mine quicker</summary>

**Extra pickaxe blocks:**
- Cobblestone stairs
- Redstone ore
- Iron door
- Bricks
- Furnaces
- Dispensers
- Stone pressure plates
- Rails
- Detector rails
- Powered rails
- Pistons
- Sticky pistons

**Extra axe blocks:**
- Wooden stairs
- Door
- Pressure plates
- Jukebox
- Note blocks
- Pumpkins
- Signs
- Trapdoors
- Ladders
- Crafting tables
- Fences

</details>

<details><summary>Stairs will now drop themselves rather than cobble/planks</summary>

Before:<br>
<video controls src="https://i.imgur.com/QSq8E8m.mp4" />

After:<br>
<video controls src="https://i.imgur.com/l5arDtA.mp4" />

</details>

<details><summary>Fixes saddled pigs not dropping their saddles on death</summary>

Before:
<video controls src="https://i.imgur.com/PVLRNn5.mp4"/>

After:
<video controls src="https://i.imgur.com/0yHHfxB.mp4" />

</details>

## List of changes

### Quality of Life changes

<details><summary>Sugar canes can now be places on sand (planned to be removed since change is already in AnnoyanceFix)</summary>

![sugar cane on sand](https://i.imgur.com/N7WjSx8.png)

</details>

<details><summary>More sounds</summary>

- Opening / closing chests
- Minecarts
- Items breaking

**Note: The mod doesn't add any sounds by itself, all of these sounds are already present in your "resources" folder, they are automatically downloaded by Minecraft itself**

</details>

<details><summary>Adds Clouds toggle in Video Settings</summary>

<video controls src="https://i.imgur.com/MUmqtmM.mp4" />

</details>

<details><summary>Removes the id tags above entities while in F3 mode</summary>

Before:<br>
![bunch of animals with id tags above them](https://i.imgur.com/PchbLnx.png)

After:
![bunch of animals without any additional UI elements above them](https://i.imgur.com/TEKQyW2.png)

</details>

<details><summary>Added amount of ingame days and real life days of playtime in F3 overlay</summary>

![white text showingcasing the number of days spent in game](https://i.imgur.com/clje0xb.png)

**Format: ingame days (real life days)**
Both are calculated using the play time stat the player has, which means if the stats file gets corrupted or deleted these numbers will reset as well!

</details>

### Fixes

<details><summary>Fixes selected blocks being rendered under text in containers</summary>

Before:
![blocks being rendered under container text](https://i.imgur.com/jaGMYZy.png)

After:
![blocks being rendered above container text](https://i.imgur.com/giD9ZTm.png)

</details>

<details><summary>Fixes the death screen's &e0 message</summary>

Before:
![death screen displaying &e0](https://i.imgur.com/HHLeOhs.png)

After:
![death screen displaying a yellow score of 0](https://i.imgur.com/rTHeTOk.png)

**Note: Score will always be 0 as nothing gives score in this version**
</details>

<details><summary>Made bows bigger and facing the right direction</summary>

Before:
![player holding a bow](https://i.imgur.com/dRgyr7G.png)

After:
![player holding a bow](https://i.imgur.com/9dgxRej.png)

Also slightly update the skeleton's model to better hold the bow

Before:
![skeleton holding a bow](https://i.imgur.com/4Pqe3pk.png)

After:
![skeleton holding a bow](https://i.imgur.com/SIDJBYI.png)

</details>

<details><summary>Fixes leg armor not being updated while riding</summary>

Before:<br>
<video controls src="https://i.imgur.com/UX9nfs8.mp4" />

After:<br>
<video controls src="https://i.imgur.com/khlHpop.mp4" />

Before:
![player's leg armor not updating its rotation according to the player's legs](https://i.imgur.com/Vx8GAtV.png)

After:
![player's leg armor correctly updating its rotation according to the player's legs](https://i.imgur.com/eHppgk9.png)

</details>

<details><summary>Fixes fishes going above the player's head</summary>

Before:<br>
<video controls src="https://i.imgur.com/jrjL1tW.mp4" />

After:<br>
<video controls src="https://i.imgur.com/5JPT81N.mp4" />

</details>

<details><summary>Fixes Minecarts hardstopping when hitting arrows or dropped items on tracks</summary>

Before:<br>
<video controls src="https://i.imgur.com/5hICLc2.mp4" />

After:<br>
<video controls src="https://i.imgur.com/Hf9X8HM.mp4" />

</details>

<details><summary>Fixes Minecarts flickering while moving</summary>

Before:<br>
<video controls src="https://i.imgur.com/cBUIE5n.mp4" />

After:<br>
<video controls src="https://i.imgur.com/vZGhuos.mp4" />

</details>

<details><summary>Fixes torches (and redstone torches) not having a bottom texture</summary>

Before:
![torches without a bottom texture](https://i.imgur.com/pueAKg3.png)

After:
![torches with a bottom texture](https://i.imgur.com/pBmy6UX.png)

</details>

<details><summary>Fixes furnaces consuming the buckets as well when using lava buckets</summary>

Before:<br>
<video controls src="https://i.imgur.com/BY0t3iG.mp4"/>

After:
<video controls src="https://i.imgur.com/4O7Fo8V.mp4" />

</details>
<br>
As well as a bunch of other minor issues not worth having before/after images such as grass block items being rendered incorrectly or the useless 10mb array wasting resources.

## Install

### Vanilla Launcher
- Download the Vanilla Launcher installer of Cursed Legacy Fabric from [https://minecraft-cursed-legacy.github.io/downloads.html](https://minecraft-cursed-legacy.github.io/downloads.html)
- Run the installer and let it finish
- Download the latest version of the mod
- Navigate your .minecraft > b1.7.3 > mods folder and drop the mod .jar file there
- Start the launcher and make sure you pick the Cursed Fabric profile before starting the game

### MultiMC / Prism Launcher
- Download the MultiMC archive of Cursed Legacy Fabric from [https://minecraft-cursed-legacy.github.io/downloads.html](https://minecraft-cursed-legacy.github.io/downloads.html)
- Open your MultiMC / Prism Launcher
- Drag the .zip file into the launcher (you can also choose a different name for this profile now) and click Ok
- Download the latest version of the mod
- Navigate to the newly created instance's location (easier way to do this is from your launcher right click on the profile and select Folder)
- Go in the .minecraft > mods folder and drop the mod .jar file there
- Go back to your launcher and start the Cursed Fabric profile you've just installed

## Installation using Prism Launcher

1. Download an instance of Babric for Prism Launcher: https://github.com/babric/prism-instance
2. Install Java 17, set the instance to use it, and disable compatibility checks on the instance: https://adoptium.net/temurin/releases/
3. Add StationAPI to the mod folder for the instance: https://jenkins.glass-launcher.net/job/StationAPI/lastSuccessfulBuild/
4. (Optional) Add Mod Menu to the mod folder for the instance: https://github.com/calmilamsy/ModMenu/releases
5. (Optional) Add GlassConfigAPI 1.1.6+ to the mod folder for the instance: https://maven.glass-launcher.net/#/releases/net/glasslauncher/mods/GlassConfigAPI
6. Add this mod to the mod folder for the instance: https://github.com/telvarost/FinalBeta-StationAPI/releases
7. Run and enjoy! 👍

## Feedback

Got any suggestions on what should be added next? Feel free to share it by [creating an issue](https://github.com/telvarost/FinalBeta-StationAPI/issues/new). Know how to code and want to do it yourself? Then look below on how to get started.

## Contributing

Thanks for considering contributing! To get started fork this repository, make your changes, and create a PR. 

If you are new to StationAPI consider watching the following videos on Babric/StationAPI Minecraft modding: https://www.youtube.com/watch?v=9-sVGjnGJ5s&list=PLa2JWzyvH63wGcj5-i0P12VkJG7PDyo9T
