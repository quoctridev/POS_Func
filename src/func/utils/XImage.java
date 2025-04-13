package func.utils;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class XImage {

    public static String uploadFileWithOkHttp(File file) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Xác định MIME type dựa trên phần mở rộng file
        String fileName = file.getName();
        MediaType mediaType;

        if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
            mediaType = MediaType.parse("image/jpeg");
        } else if (fileName.toLowerCase().endsWith(".png")) {
            mediaType = MediaType.parse("image/png");
        } else {
            // Fallback, nhưng có thể server sẽ từ chối
            mediaType = MediaType.parse("application/octet-stream");
        }

        // Tạo request với MIME type chính xác
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", file.getName(),
                        RequestBody.create(mediaType, file))
                .build();
        System.out.println("Uploading file: " + file.getAbsolutePath());
        System.out.println("File name: " + file.getName());
        System.out.println("File size: " + file.length());
        System.out.println("MIME type: " + mediaType);
        Request request = new Request.Builder()
                .url("http://103.118.28.181:3000/upload")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();

            if (!response.isSuccessful()) {
                throw new IOException("Upload failed: " + response.code() + " - " + responseBody);
            }

            // Parse JSON response
            String fileNameFromServer = responseBody.split("\"file\":\"")[1].split("\"")[0];
            return fileNameFromServer;
        }
    }

    public static ImageIcon getImageIconFromServerWithOkHttp(String fileName) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://103.118.28.181:3000/images/" + fileName)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("Không thể tải ảnh: " + response.code());
                return null;
            }

            try (InputStream inputStream = response.body().byteStream()) {
                BufferedImage image = ImageIO.read(inputStream);
                return new ImageIcon(image); // Trả về ImageIcon
            } catch (IOException e) {
                System.out.println("Lỗi khi đọc dữ liệu ảnh: " + e.getMessage());
                e.printStackTrace();
                return null;
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi kết nối đến server: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteImage(String filename) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Tạo yêu cầu DELETE
        Request request = new Request.Builder()
                .url("http://103.118.28.181:3000" + "/delete/" + filename) // URL với tên file cần xóa
                .delete() // Phương thức DELETE
                .build();

        // Gửi yêu cầu và nhận phản hồi
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("File deleted successfully");
            } else {
                System.out.println("Failed to delete file: " + response.message());
            }
        }
    }

}
