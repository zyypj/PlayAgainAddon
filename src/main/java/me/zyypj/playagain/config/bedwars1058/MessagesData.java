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
                    yml.addDefault(NO_PERM, "&cVocê não tem permissão para isso!");
                    yml.addDefault(PLAYER_NOT_IN_ARENA, "&cVocê não está em uma arena!");
                    yml.addDefault(PLAYER_IS_IN_ARENA, "&cVocê já está nessa arena!");
                    yml.addDefault(COMMAND_NOT_FOUND, "&cComando não encontrado!");
                    yml.addDefault(PLAY_AGAIN_ITEM_NAME, "&aJogar Novamente");
                    yml.addDefault(PLAY_AGAIN_ITEM_LORE, Arrays.asList(" ", "&eClique para jogar novamente"));
                    yml.addDefault(LEAVE_ITEM_NAME, "&aVoltar ao Lobby");
                    yml.addDefault(LEAVE_ITEM_LORE, Arrays.asList(" ", "&eClique para voltar ao lobby"));
                    yml.addDefault(GROUP_BLOCKED, "&cPlayAgain está bloqueado nesse modo!");
                    yml.addDefault(COMMAND_COOLDOWN, "&cVocê deve esperar {TIME}s antes de usar isto novamente!");
                    yml.addDefault(NOT_PARTY_OWNER, "&cVocê não é dono de nenhuma party!");
                    break;
                default:
                    yml.addDefault(NO_PERM, "&cYou do not have permission to use this command");
                    yml.addDefault(PLAYER_NOT_IN_ARENA, "&cYou are not in an arena!");
                    yml.addDefault(PLAYER_IS_IN_ARENA, "&cYou are already in this arena!");
                    yml.addDefault(COMMAND_NOT_FOUND, "&cCommand not found!");
                    yml.addDefault(PLAY_AGAIN_ITEM_NAME, "&aPlay Again");
                    yml.addDefault(PLAY_AGAIN_ITEM_LORE, Arrays.asList(" ", "&eClick to play again"));
                    yml.addDefault(LEAVE_ITEM_NAME, "&aReturn to Lobby");
                    yml.addDefault(LEAVE_ITEM_LORE, Arrays.asList(" ", "&eClick to return to lobby"));
                    yml.addDefault(GROUP_BLOCKED, "&cPlayAgain is blocked in this mode!");
                    yml.addDefault(COMMAND_COOLDOWN, "&cYou must wait {TIME}s before using that again!");
                    yml.addDefault(NOT_PARTY_OWNER, "&cYou are not the owner of any party!");
                    break;
            }
            yml.options().copyDefaults(true);
            l.save();
        }
    }
}
