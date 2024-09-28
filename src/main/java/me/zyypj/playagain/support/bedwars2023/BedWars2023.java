package me.zyypj.playagain.support.bedwars2023;

import com.tomkeuper.bedwars.api.BedWars;
import me.zyypj.playagain.utils.Support;
import org.bukkit.Bukkit;

import static me.zyypj.playagain.PlayAgainAddon.bw2023Api;
import static me.zyypj.playagain.PlayAgainAddon.support;

public class BedWars2023 {

    public BedWars2023() {
        start();
    }

    public void start() {
        support = Support.BEDWARS2023;
        bw2023Api = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();

        bw2023Api.getAddonsUtil().registerAddon(new BedWarsAddon());
    }
}
