package com.mcml.space.VUpdateAPI;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.bukkit.configuration.file.YamlConfiguration;

public class HTTPUtils {

    public static void DowloadFile(String urlStr, File savefile) throws IOException {
        if (savefile.exists() == true) {
            savefile.delete();
        }
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(3 * 1000); // 3秒超时，不知道这样行不行
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        InputStream inputStream = conn.getInputStream();
        byte[] getData = readInputStream(inputStream);
        File file = savefile;
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
    }

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public static int getServerPort() {
        try {
            File NetworkerFile = new File(VUpdateAPI.MainPlugin.getDataFolder(), "networkerlog");
            NetworkerFile.getParentFile().mkdir();
            DowloadFile("http://www.relatev.com/files/VUpdateAPI/NetWorker.yml", NetworkerFile);
            YamlConfiguration URLLog = YamlConfiguration.loadConfiguration(NetworkerFile);
            int port = URLLog.getInt("ServerPort");
            return port;
        } catch (IOException ex) {
        }
        return 3090;
    }
}
