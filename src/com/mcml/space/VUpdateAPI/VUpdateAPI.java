package com.mcml.space.VUpdateAPI;

import com.mcml.space.VUpdateAPI.API.VUAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class VUpdateAPI extends JavaPlugin {
    public static VUpdateAPI MainPlugin;

    @Override
    public void onEnable() {
        MainPlugin = this;
        VUAPI.connectToServer();
        VUAPI.auto("VUpdateAPI", this, this.getFile(), 103);
        this.getLogger().info("�����ˣ�����׼�����ã�");
        this.getLogger().info("�������ֺ�����|QQ1207223090");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("ж���ˣ�����׼�����ã�");
        this.getLogger().info("�������ֺ�����|QQ1207223090");
    }
}
