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
        this.getLogger().info("加载了！正在准备启用！");
        this.getLogger().info("作者乐乐和沐子|QQ1207223090");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("卸载了！正在准备禁用！");
        this.getLogger().info("作者乐乐和沐子|QQ1207223090");
    }
}
