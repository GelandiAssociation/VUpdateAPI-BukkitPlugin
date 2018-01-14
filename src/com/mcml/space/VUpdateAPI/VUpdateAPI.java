package com.mcml.space.VUpdateAPI;

import com.mcml.space.VUpdateAPI.API.VUAPI;
import java.io.IOException;
import java.net.UnknownHostException;
import org.bukkit.plugin.java.JavaPlugin;

public class VUpdateAPI extends JavaPlugin {
    public static VUpdateAPI MainPlugin;

    @Override
    public void onEnable() {
        MainPlugin = this;
        this.connectToServer();
        VUAPI.auto("VUpdateAPI", this, this.getFile(), 102);
        this.getLogger().info("加载了！正在准备启用！");
        this.getLogger().info("作者乐乐和沐子|QQ1207223090");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("卸载了！正在准备禁用！");
        this.getLogger().info("作者乐乐和沐子|QQ1207223090");
    }

    public void connectToServer() {
        String ServerIP = "vuapi.relatev.com";
        int port=HTTPUtils.getServerPort();
        try {
            this.getLogger().info("正在尝试连接到云端更新服务器:" + ServerIP + ":" + port);
            TCPUtils.Connect(ServerIP, port);
        } catch (UnknownHostException ex) {
            this.getLogger().info("无法连接到更新服务器，请联系作者乐乐：QQ1207223090！");
            this.getLogger().info("无法解析到HOST域名，域名不存在?");
            return;
        } catch (IOException ex) {
            this.getLogger().info("无法连接到更新服务器，请联系作者乐乐：QQ1207223090！");
            this.getLogger().info("无法连接到服务器，服务器离线?");
            return;
        }
    }
}
