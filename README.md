# CobbleNumberModify

## 📋 项目简介

**PokemonMaxLevel** 是一个 Minecraft 模组（适用于 Fabric
环境），旨在突破原版宝可梦的数值限制。通过该模组，可以自定义宝可梦的最大等级、个体值（IV）和努力值（EV），从而获得更高的战斗力和更自由的游戏体验。

模组通过 Mixin 技术修改宝可梦等级上限，并提供配置文件 `CobbleNumberModify.json`
来控制是否允许通过常规方式修改宝可梦的等级、个体值和努力值。如果配置为禁用，则必须通过 `PokemonDataEditor`
类提供的方法进行修改。

---

## 🔧 配置说明

模组使用 `config/PokemonMaxLevel.json` 作为配置文件，用于控制是否允许通过常规方式修改宝可梦数值。默认配置如下：

```json
{
  "level": true,
  "iv": true,
  "ev": true
}
```

### 配置项说明

| 配置项       | 默认值    | 作用说明                                                                                  |
|-----------|--------|---------------------------------------------------------------------------------------|
| `"level"` | `true` | 控制是否允许通过常规方式修改宝可梦等级。若设为 `false`，则必须使用 `PokemonDataEditor.setPokemonLevel()` 方法进行修改。   |
| `"iv"`    | `true` | 控制是否允许通过常规方式修改宝可梦个体值（IV）。若设为 `false`，则必须使用 `PokemonDataEditor.setPokemonIV()` 方法进行修改。 |
| `"ev"`    | `true` | 控制是否允许通过常规方式修改宝可梦努力值（EV）。若设为 `false`，则必须使用 `PokemonDataEditor.setPokemonEV()` 方法进行修改。 |

---

## 🛠️ PokemonDataEditor 使用示例

如果配置中 `"level"`、`"iv"` 或 `"ev"` 被设置为 `false`，则需要通过 `PokemonDataEditor` 类的方法来修改宝可梦的相应数值。以下是一个使用示例代码：

```java
public void test(Pokemon pokemon) {
    if (pokemon == null) return;

    // 修改宝可梦等级
    PokemonDataEditor.setPokemonLevel(pokemon, 200);

    // 修改宝可梦个体值
    PokemonDataEditor.setPokemonIV(pokemon, 300);
    pokemon.setIV(Stats.HP, 300);
    pokemon.setIV(Stats.ATTACK, 300);
    pokemon.setIV(Stats.DEFENCE, 300);
    pokemon.setIV(Stats.SPECIAL_ATTACK, 300);
    pokemon.setIV(Stats.SPECIAL_DEFENCE, 300);
    pokemon.setIV(Stats.SPEED, 300);

    // 修改宝可梦努力值
    PokemonDataEditor.setPokemonEV(pokemon, 400);
    pokemon.setEV(Stats.HP, 400);
    pokemon.setEV(Stats.ATTACK, 300);
    pokemon.setEV(Stats.DEFENCE, 300);
    pokemon.setEV(Stats.SPECIAL_ATTACK, 300);
    pokemon.setEV(Stats.SPECIAL_DEFENCE, 300);
    pokemon.setEV(Stats.SPEED, 300);
}
```

### 方法说明

- `PokemonDataEditor.setPokemonLevel(pokemon, level)`：设置宝可梦等级，即使配置中 `"level": false`。
- `PokemonDataEditor.setPokemonIV(pokemon, iv)`：设置宝可梦个体值，即使配置中 `"iv": false`。
- `PokemonDataEditor.setPokemonEV(pokemon, ev)`：设置宝可梦努力值，即使配置中 `"ev": false`。

> 💡 所有 `PokemonDataEditor` 方法均在 [PokemonDataEditor.java](src/main/java/lg/minecraft/mod/cobblenumermodify/util/PokemonDataEditor.java)

---

## 📌 总结

通过 **PokemonMaxLevel** 模组，开发者可以灵活地控制宝可梦数值的修改权限：

- 默认允许通过常规方式修改宝可梦等级、个体值和努力值。
- 通过配置文件 `PokemonMaxLevel.json` 可以禁止某些修改行为。
- 当配置项被设为 `false` 时，必须通过 PokemonDataEditor 提供的方法进行数值修改。

这种设计在保证灵活性的同时，也提供了良好的安全控制机制。

---

## 📦 开源地址

欢迎访问本项目的 Gitee 仓库获取完整源码和最新更新：

🔗 [https://gitee.com/wolf-music/CobbleNumberModify](https://gitee.com/wolf-music/CobbleNumberModify)
