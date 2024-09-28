# PlayAgainAddon

**PlayAgain** is an addon for the **BedWars2023** plugin that provides convenient features for players to quickly play again or return to the lobby after a match ends. This addon is designed to enhance the player's experience by adding configurable items such as a "Play Again" button and a "Return to Lobby" button.

## Features
- Automatically adds "Play Again" and "Return to Lobby" items to the player's inventory after a BedWars game ends.
- Supports multiple configurations for item types, names, and commands.
  
## Installation

1. **Download the PlayAgain addon**: 
   - Get the latest version of the PlayAgain addon from the [Releases page](https://github.com/zyypj/PlayAgainAddon/releases).

2. **Add to your BedWars2023 server**:
   - Place the downloaded `.jar` file in your `plugins` folder.

3. **Start your server**:
   - Launch your Minecraft server. The plugin will automatically detect and hook into BedWars2023.

4. **Configuration**:
   - After the first startup, a config file will be generated. You can find it at `plugins/BedWars2023/Addons/PlayAgain/config.yml`. Modify it to suit your needs.
  
## Configuration

The `config.yml` allows customization of the items that appear after a BedWars match. Below is a list of configuration options:

```yaml
play-again-item:
  type: "PAPER"
  command: "internal" # Use "internal" for BedWars group-based random join or specify a custom command
  slot: 1

leave-item:
  type: "BED"
  command: "lobby"
  slot: 9
```

- `play-again-item`: Configuration for the "Play Again" item.
  - `type`: The item type (e.g., `DIAMOND_SWORD` or `IRON_SWORD`).
  - `command`: Either use `"internal"` to join a random match or specify a custom command to be executed.
  - `slot`: The inventory slot where the item will appear (1-9).

- `leave-item`: Configuration for the "Return to Lobby" item.
  - `type`: The item type.
  - `command`: Command executed when the player clicks this item.
  - `slot`: The inventory slot where this item appears.

## Compatibility
- **Minecraft Version**: 1.8.x and above
- **Required Plugin**: [BedWars2023](https://github.com/tomkeuper/bedwars2023)

## Support

If you encounter any issues, feel free to open an issue on the [GitHub Issues page](https://github.com/zyypj/PlayAgainAddon/issues) or reach out via Discord.

## Credits

Developed by **tadeu** ([@zyypj](https://github.com/zyypj)).
