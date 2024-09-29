# PlayAgainAddon

**PlayAgainAddon** is a simple but powerful addon for BedWars servers (supports both BedWars1058 and BedWars2023). It introduces a `/playagain` command that allows players to quickly join another game with their current team or find a match of the same mode they recently played, even from the lobby. The addon also includes customizable leave and play-again items and full control over configuration settings.

## Features

- Automatically adds "Play Again" and "Leave" items to players' inventories after a game ends.
- Join a new match with the `/playagain` command.
- Ability to reload the plugin with `/playagain reload`.
- Tracks match history for easy transition to the next game.
- Fully customizable via config file.

## Installation

1. Download the **PlayAgainAddon** plugin.
2. Place the plugin `.jar` file in your server's `/plugins/` folder.
3. Restart your server or reload the plugins with `/reload`.
4. Configure the plugin via `config.yml` (details below).

## Commands

- `/playagain` – Sends the player to the next match, either with the same team or in the same mode, depending on the recent game.
- `/playagain reload` – Reloads the plugin configuration and settings (requires permission `playagain.reload`).

## Permissions

- `playagain.reload` – Allows access to the `/playagain reload` command.

## Configuration (config.yml)

Here’s an example `config.yml` file for **PlayAgainAddon**:

```yaml
# PlayAgainAddon By tadeu (@zypj)

reload-permission: playagain.reload

leave:
  item:
    type: BED
    slot: 9
    command: bw leave

play-again:
  item:
    type: PAPER
    slot: 8
    command: internal

use-command: true
groups-blocked:
- "group1"
- "group2"
- "group3"
```

### Config Options

- `reload-permission`: The permission node required to use the `/playagain reload` command.
- `leave.item.type`: The item type for the "Leave" button (default: BED).
- `leave.item.slot`: The inventory slot where the "Leave" item will appear (default: 9).
- `leave.item.command`: The command executed when the player clicks the "Leave" item (default: `bw leave`).
- `play-again.item.type`: The item type for the "Play Again" button (default: PAPER).
- `play-again.item.slot`: The inventory slot where the "Play Again" item will appear (default: 8).
- `play-again.item.command`: The command executed when the player clicks the "Play Again" item (default: `internal`).
- `use-command`: Whether to enable the `/playagain` command (default: true).

## Bug Reports and Suggestions

For bug reports or feature requests, please create an issue at [GitHub Issues page](https://github.com/your-repo/issues) or DM me on Discord.
