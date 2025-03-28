package func.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.json.JSONObject;

public class XImage {

    public static String save(File src) {
        if (src == null) {
            return null;
        }
        File dst = new File("image", src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
        }
        try {
            Files.copy(src.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return uploadFile(dst);
        } catch (Exception ex) {
            throw new RuntimeException("Lỗi khi lưu ảnh: " + ex.getMessage(), ex);
        }
    }

    public static String uploadFile(File file) {
        String uploadURL = "http://103.118.28.181/upload/" + file.getName().replace("\\", "/");
        System.out.println("Uploading to: " + uploadURL);
        String contentType;

        try {
            contentType = Files.probeContentType(file.toPath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
        } catch (IOException e) {
            contentType = "application/octet-stream";
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(uploadURL).openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", contentType);

            OutputStream outputStream = conn.getOutputStream();
            Files.copy(file.toPath(), outputStream);
            outputStream.flush();
            outputStream.close();

            int responseCode = conn.getResponseCode();
            BufferedReader reader;
            if (responseCode >= 200 && responseCode < 300) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println("Upload response: " + response);
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Lỗi khi upload: " + e.getMessage();
        }
    }

    public static ImageIcon read(String fileName) {
        File path = new File("image", fileName);
        if (path.exists()) {
            return new ImageIcon(path.getAbsolutePath());
        } else {
            return downloadImageFromServer("http://103.118.28.181/upload/" + fileName, path);
        }
    }

    private static ImageIcon downloadImageFromServer(String imageUrl, File saveFile) {
        try {
            URL url = new URL(imageUrl);
            BufferedImage image = ImageIO.read(url);
            saveFile.getParentFile().mkdirs();
            ImageIO.write(image, "jpg", saveFile);
            System.out.println("Ảnh đã tải về: " + saveFile.getAbsolutePath());
            return new ImageIcon(saveFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
