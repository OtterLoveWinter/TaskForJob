package org.mantisbt;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Captcha {
    private static volatile Captcha instance;

    public static Captcha getInstance() {
        Captcha localInstance = instance;
        if (localInstance == null) {
            synchronized (Captcha.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Captcha();
                }
            }
        }
        return localInstance;
    }

    public static String send(String image) {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Формируем запрос
            HttpPost postRequest = new HttpPost("https://rucaptcha.com/in.php");
            JSONObject body = new JSONObject();
            body.put("key", ConfProperties.getProperty("token"));
            body.put("method", "base64");
            body.put("body", image);
            body.put("json", 1);
            body.put("phrase", 0);
            body.put("regsense", 1);
            body.put("numeric", 4);
            body.put("language", 2);
            body.put("min_len", 6);
            body.put("max_len", 6);
            StringEntity se = new StringEntity(body.toJSONString());
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            postRequest.setEntity(se);

            // Выполняем запрос
            HttpResponse response = httpClient.execute(postRequest);

            // Результат
            return processingRequest(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String get(String id) {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Формируем запрос
            URIBuilder builder = new URIBuilder("https://rucaptcha.com/res.php");
            builder.setParameter("key", ConfProperties.getProperty("token"))
                    .setParameter("action", "get")
                    .setParameter("id", id)
                    .setParameter("json", "1");
            HttpGet getRequest = new HttpGet(builder.build());

            // Выполняем запрос
            HttpResponse response = httpClient.execute(getRequest);

            // Результат
            return processingRequest(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Проверка результата
     */
    private static String processingRequest(HttpResponse response)
            throws Exception{
        if (response.getStatusLine().getStatusCode() == 200) {
            // Парсим ответ
            InputStreamReader inputStream = new InputStreamReader((response.getEntity().getContent()), "UTF-8");
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(inputStream);

            // если успешно, то возвращаем ID запроса
            if (jsonObject.get("status").equals(1L)) {
                return jsonObject.get("request").toString();
            }
        }
        return null;
    }


    /**
     * Картинка в base64
     */
    public static String encodeToString(BufferedImage image)
            throws IOException {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        byte[] imageBytes = bos.toByteArray();
        byte[] encoded = Base64.encodeBase64(imageBytes);
        imageString = new String(encoded, StandardCharsets.US_ASCII);

        bos.close();
        return imageString;
    }
}
