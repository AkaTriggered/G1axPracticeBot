# G1ax Crystal Practice Bot

[![Minecraft Version](https://img.shields.io/badge/Minecraft-1.20+-brightgreen.svg)](https://minecraft.net)
[![Paper](https://img.shields.io/badge/Paper-Required-orange.svg)](https://papermc.io)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Downloads](https://img.shields.io/badge/Downloads-0-green.svg)](https://github.com/G1axCustoms/G1axCrystalPracticeBot/releases)

A powerful Minecraft plugin that spawns customizable practice bots for crystal PvP training. Perfect for servers wanting to provide players with AI opponents to practice their combat skills.

## 🎯 Features

### Core Functionality
- **Spawn Practice Bots**: Create AI-controlled zombie bots for combat practice
- **Customizable Behavior**: Configure bot AI patterns and responses
- **Equipment Management**: Full control over bot armor, weapons, and items
- **Effect System**: Apply potion effects to bots for varied training scenarios
- **GUI Interface**: User-friendly menus for all bot configurations

### Bot Behaviors
- **Aggressive Mode**: Bot actively attacks the player
- **Defensive Mode**: Bot only fights back when player health is low
- **Stand Still Mode**: Bot remains stationary for target practice
- **Pearl to Owner**: Bot teleports to player when too far away
- **Auto Totem**: Automatically equips totems of undying
- **Shield Usage**: Smart shield blocking mechanics

### Equipment Customization
- **Armor Sets**: Configure full armor sets with custom enchantments
- **Weapon Selection**: Choose from various weapons and tools
- **Enchantment System**: Apply any enchantment with custom levels
- **Item Management**: Full inventory control for practice scenarios

## 📋 Requirements

- **Minecraft Version**: 1.20+
- **Server Software**: Paper, Purpur, or other Paper-based forks
- **Java Version**: 17+
- **Permissions**: Op or `practicebot.admin` for admin commands

## 🚀 Installation

1. Download the latest release from [Releases](https://github.com/G1axCustoms/G1axCrystalPracticeBot/releases)
2. Place the `.jar` file in your server's `plugins` folder
3. Restart your server
4. Configure permissions if needed

## 🎮 Commands

| Command | Description | Permission | Usage |
|---------|-------------|------------|-------|
| `/spawnbot` | Spawn a practice bot | Default | `/spawnbot` |
| `/botmenu` | Open bot configuration menu | Default | `/botmenu` |
| `/removebot` | Remove your practice bot | Default | `/removebot` |
| `/removeallbots` | Remove all practice bots (Admin) | `practicebot.admin` | `/removeallbots` |

## 🔧 Permissions

```yaml
practicebot.admin:
  description: Allows access to admin commands
  default: op
```

## 📖 Usage Guide

### Basic Usage
1. Use `/spawnbot` to create your first practice bot
2. Use `/botmenu` to open the configuration interface
3. Customize your bot's behavior, equipment, and effects
4. Practice your PvP skills!
5. Use `/removebot` when finished

### Configuration Options

#### Behavior Settings
- **Aggressive**: Bot will actively pursue and attack you
- **Defensive**: Bot only fights when you're at low health
- **Stand Still**: Bot remains motionless for target practice
- **Pearl to Owner**: Bot teleports to you if you get too far away
- **Auto Totem**: Bot automatically equips totems of undying

#### Equipment Management
- **Armor Configuration**: Set up full armor sets with custom enchantments
- **Weapon Selection**: Choose weapons and set enchantment levels
- **Effect Application**: Apply potion effects for varied training scenarios

## 🛠️ For Developers

### Building from Source
```bash
git clone https://github.com/G1axCustoms/G1axCrystalPracticeBot.git
cd G1axCrystalPracticeBot
mvn clean package
```

### Project Structure
```
src/main/java/dev/akatriggered/practicebot/
├── PracticeBotPlugin.java          # Main plugin class
├── bot/
│   ├── BotManager.java             # Bot management system
│   ├── PracticeBot.java            # Individual bot instance
│   ├── BotAI.java                  # AI behavior logic
│   ├── BotBehaviour.java           # Behavior configuration
│   ├── BotEffects.java             # Potion effects management
│   └── BotEquipment.java           # Equipment handling
├── commands/
│   ├── SpawnBotCommand.java        # Bot spawning command
│   ├── BotMenuCommand.java         # Menu opening command
│   ├── RemoveBotCommand.java       # Bot removal command
│   └── RemoveAllBotsCommand.java   # Admin removal command
├── gui/
│   ├── BotMenu.java                # Main configuration GUI
│   ├── ArmorGUI.java               # Armor configuration interface
│   ├── BehaviourGUI.java           # Behavior settings interface
│   ├── EffectGUI.java              # Effect management interface
│   └── EnchantGUI.java             # Enchantment configuration
└── util/
    ├── PlayerData.java             # Player data management
    ├── ItemBuilder.java            # Item creation utility
    ├── ArmorFactory.java           # Armor set creation
    ├── NBTUtil.java                # NBT data handling
    └── UsageStats.java             # Usage statistics tracking
```

## 🐛 Bug Reports & Feature Requests

Found a bug or have a feature request? Please create an issue on our [GitHub Issues](https://github.com/G1axCustoms/G1axCrystalPracticeBot/issues) page.

When reporting bugs, please include:
- Minecraft version
- Server software and version
- Plugin version
- Steps to reproduce the issue
- Any error messages from console

## 🤝 Contributing

We welcome contributions! Please feel free to submit pull requests or open issues for discussion.

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Built for the Minecraft PvP community
- Inspired by the need for better practice tools
- Thanks to all contributors and testers

## 📊 Statistics

This plugin includes anonymous usage statistics to help improve development. No personal data is collected. Statistics include:
- Server count using the plugin
- Plugin version distribution
- Basic server information (player count, etc.)

You can disable statistics by setting `enable-stats: false` in the plugin configuration.

---

**Made with ❤️ by AkaTriggered for the G1ax Customs community**

*For support, join our Discord or create an issue on GitHub!*
