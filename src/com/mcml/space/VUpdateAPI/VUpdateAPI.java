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
        this.getLogger().info("�����ˣ�����׼�����ã�");
        this.getLogger().info("�������ֺ�����|QQ1207223090");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("ж���ˣ�����׼�����ã�");
        this.getLogger().info("�������ֺ�����|QQ1207223090");
    }

    public void connectToServer() {
        String ServerIP = "vuapi.relatev.com";
        int port=HTTPUtils.getServerPort();
        try {
            this.getLogger().info("���ڳ������ӵ��ƶ˸��·�����:" + ServerIP + ":" + port);
            TCPUtils.Connect(ServerIP, port);
        } catch (UnknownHostException ex) {
            this.getLogger().info("�޷����ӵ����·�����������ϵ�������֣�QQ1207223090��");
            this.getLogger().info("�޷�������HOST����������������?");
            return;
        } catch (IOException ex) {
            this.getLogger().info("�޷����ӵ����·�����������ϵ�������֣�QQ1207223090��");
            this.getLogger().info("�޷����ӵ�������������������?");
            return;
        }
    }
}
