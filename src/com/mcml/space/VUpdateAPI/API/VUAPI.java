package com.mcml.space.VUpdateAPI.API;

import com.mcml.space.VUpdateAPI.HTTPUtils;
import com.mcml.space.VUpdateAPI.TCPUtils;
import com.mcml.space.VUpdateAPI.UpdateMode;
import com.mcml.space.VUpdateAPI.VUpdateAPI;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class VUAPI {

    public static void auto(final String projectname, final Plugin plugin, final File saveFile, int NowVersion) {
        if (VUAPI.hasConnectedSuccessful() == true) {
            try {
                VUpdateAPI.MainPlugin.getLogger().info("正在为" + plugin.getName() + "准备自动更新系统...");
                int lastestVersion = VUAPI.getLastestVersion(projectname);
                if (lastestVersion > NowVersion) {
                    VUpdateAPI.MainPlugin.getLogger().info("检测到最新版本！正在下载最新版本插件来更新" + plugin.getName() + "！");
                    Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {

                        @Override
                        public void run() {
                            try {
                                VUAPI.downloadLastestVersion(saveFile, projectname, UpdateMode.OverWriteNextRestart);
                            } catch (IOException ex) {
                                Logger.getLogger(VUAPI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            VUpdateAPI.MainPlugin.getLogger().info(plugin.getName() + "插件更新下载完毕！将于下次重启使得更新生效！");
                        }
                    });
                } else {
                    VUpdateAPI.MainPlugin.getLogger().info(plugin.getName() + "插件已经是最新版本了！");
                }
            } catch (IOException ex) {
                plugin.getLogger().info("错误！无法连接到更新服务器，自动更新终止.");
            }
        } else {
            VUpdateAPI.MainPlugin.getLogger().info("无法进行插件的自动更新，因为服务器断线.");
        }
    }

    @Deprecated
    public static int getLastestVersion(String projectname, File saveFile) throws IOException {
        TCPUtils.sendOut("getVersion-" + projectname);
        String RecIn = TCPUtils.RecIn();
        return Integer.parseInt(RecIn.substring(14, RecIn.length()));
    }

    public static int getLastestVersion(String projectname) throws IOException {
        TCPUtils.sendOut("getVersion-" + projectname);
        String RecIn = TCPUtils.RecIn();
        return Integer.parseInt(RecIn.substring(14, RecIn.length()));
    }

    public static void downloadLastestVersion(File saveFile, String projectname, UpdateMode um) throws IOException {
        String downloadRoad = VUAPI.getDownloadRoad(projectname);
        if (um == UpdateMode.OverWriteFileandRestart) {
            HTTPUtils.DowloadFile(downloadRoad, saveFile);
        }
        if (um == UpdateMode.OverWriteNextRestart) {
            if (Bukkit.getUpdateFolderFile().exists() == false) {
                Bukkit.getUpdateFolderFile().mkdir();
            }
            HTTPUtils.DowloadFile(downloadRoad, new File(Bukkit.getUpdateFolderFile(), saveFile.getName()));
        }
    }

    public static String getDownloadRoad(String projectname) throws IOException {
        TCPUtils.sendOut("getDownloadRoad-" + projectname);
        String RecIn = TCPUtils.RecIn();
        String downloadRoad = RecIn.substring(19, RecIn.length());
        return downloadRoad;
    }

    public static boolean hasConnectedSuccessful() {
        if (TCPUtils.out == null) {
            return false;
        } else {
            return true;
        }
    }

    public static void connectToServer() {
        String ServerIP = "vuapi.relatev.com";
        int port = HTTPUtils.getServerPort();
        try {
            VUpdateAPI.MainPlugin.getLogger().info("正在尝试连接到云端更新服务器:" + ServerIP + ":" + port);
            TCPUtils.Connect(ServerIP, port);
        } catch (UnknownHostException ex) {
            VUpdateAPI.MainPlugin.getLogger().info("无法连接到更新服务器，请联系作者乐乐：QQ1207223090！");
            VUpdateAPI.MainPlugin.getLogger().info("无法解析到HOST域名，域名不存在?");
            return;
        } catch (IOException ex) {
            VUpdateAPI.MainPlugin.getLogger().info("无法连接到更新服务器，请联系作者乐乐：QQ1207223090！");
            VUpdateAPI.MainPlugin.getLogger().info("无法连接到服务器，服务器离线?");
            return;
        }
    }

    public static void connectToServer(String serverip, int port) {
        try {
            VUpdateAPI.MainPlugin.getLogger().info("正在尝试连接到云端更新服务器:" + serverip + ":" + port);
            TCPUtils.Connect(serverip, port);
        } catch (UnknownHostException ex) {
            VUpdateAPI.MainPlugin.getLogger().info("无法连接到更新服务器，请联系作者乐乐：QQ1207223090！");
            VUpdateAPI.MainPlugin.getLogger().info("无法解析到HOST域名，域名不存在?");
            return;
        } catch (IOException ex) {
            VUpdateAPI.MainPlugin.getLogger().info("无法连接到更新服务器，请联系作者乐乐：QQ1207223090！");
            VUpdateAPI.MainPlugin.getLogger().info("无法连接到服务器，服务器离线?");
            return;
        }
    }
}
