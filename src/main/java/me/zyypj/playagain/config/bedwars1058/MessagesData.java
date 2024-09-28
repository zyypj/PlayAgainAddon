package me.zyypj.playagain.config.bedwars1058;

import com.andrei1058.bedwars.api.language.Language;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Arrays;

import static me.zyypj.playagain.config.ConfigPaths.*;

public class MessagesData {

    public MessagesData() {
        setup();
    }

    private void setup() {
        for (Language l : Language.getLanguages()) {
            YamlConfiguration yml = l.getYml();
            switch (l.getIso()) {
                case "pt":
                    yml.addDefault(PLAY_AGAIN_ITEM_NAME, "&aJogar Novamente");
                    yml.addDefault(PLAY_AGAIN_ITEM_LORE, Arrays.asList(" ", "&eClique para jogar novamente"));
                    yml.addDefault(LEAVE_ITEM_NAME, "&aVoltar ao Lobby");
                    yml.addDefault(LEAVE_ITEM_LORE, Arrays.asList(" ", "&eClique para voltar ao lobby"));
                    break;
                default:
                    yml.addDefault(PLAY_AGAIN_ITEM_NAME, "&aPlay Again");
                    yml.addDefault(PLAY_AGAIN_ITEM_LORE, Arrays.asList(" ", "&eClick to play again"));
                    yml.addDefault(LEAVE_ITEM_NAME, "&aReturn to Lobby");
                    yml.addDefault(LEAVE_ITEM_LORE, Arrays.asList(" ", "&eClick to return to lobby"));
                    break;
            }
            yml.options().copyDefaults(true);
            l.save();
        }
    }
}
