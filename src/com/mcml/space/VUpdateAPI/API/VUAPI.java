package com.mcml.space.VUpdateAPI.API;

import com.mcml.space.VUpdateAPI.HTTPUtils;
import com.mcml.space.VUpdateAPI.TCPUtils;
import com.mcml.space.VUpdateAPI.UpdateMode;
import com.mcml.space.VUpdateAPI.VUpdateAPI;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class VUAPI {

    public static void auto(final String projectname,final Plugin plugin,final File saveFile,int NowVersion) {
        try {
            VUpdateAPI.MainPlugin.getLogger().info("����Ϊ" + plugin.getName() + "׼���Զ�����ϵͳ...");
            int lastestVersion = VUAPI.getLastestVersion(projectname, saveFile);
            if(lastestVersion > NowVersion){
                VUpdateAPI.MainPlugin.getLogger().info("��⵽���°汾�������������°汾���������" + plugin.getName() + "��");
                Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {

                    @Override
                    public void run() {
                        try {
                            VUAPI.downloadLastestVersion(saveFile, projectname, UpdateMode.OverWriteNextRestart);
                        } catch (IOException ex) {
                            Logger.getLogger(VUAPI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        VUpdateAPI.MainPlugin.getLogger().info(plugin.getName() + "�������������ϣ������´�����ʹ�ø�����Ч��");
                    }
                });
            }else{
                VUpdateAPI.MainPlugin.getLogger().info(plugin.getName() + "����Ѿ������°汾�ˣ�");
            }
        } catch (IOException ex) {
            plugin.getLogger().info("�����޷����ӵ����·��������Զ�������ֹ.");
        }
    }

    public static int getLastestVersion(String projectname,File saveFile) throws IOException {
        TCPUtils.sendOut("getVersion-" + projectname);
        String RecIn = TCPUtils.RecIn();
        return Integer.parseInt(RecIn.substring(14, RecIn.length()));
    }
    
    public static void downloadLastestVersion(File saveFile,String projectname,UpdateMode um) throws IOException {
        TCPUtils.sendOut("getDownloadRoad-" + projectname);
        String RecIn = TCPUtils.RecIn();
        String downloadRoad = RecIn.substring(19, RecIn.length());
        if(um == UpdateMode.OverWriteFileandRestart){
            HTTPUtils.DowloadFile(downloadRoad, saveFile);
        }
        if(um == UpdateMode.OverWriteNextRestart){
            if(Bukkit.getUpdateFolderFile().exists() == false){
                Bukkit.getUpdateFolderFile().mkdir();
            }
            HTTPUtils.DowloadFile(downloadRoad, new File(Bukkit.getUpdateFolderFile(),saveFile.getName()));
        }
    }
}
