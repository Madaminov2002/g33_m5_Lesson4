package org.example.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

public class HttpRequestExample {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
//        HttpClient httpClient=HttpClient.newHttpClient();
//        HttpRequest request=HttpRequest.newBuilder()
//                .uri(new URI("https://github.com/"))
//                .GET()
//                .build();
//        HttpResponse<String> response=httpClient.send(request,HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.headers());
//        System.out.println(response.statusCode());
//        System.out.println(response.body());

        Book book = new Book("Java", "Men", 30d, new Date());
//        Gson gson=new Gson();
//        String json = gson.toJson(book);
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
//                .setDateFormat(DateFormat.SHORT)
//                .setVersion(1.2)
//                .excludeFieldsWithModifiers(Modifier.PROTECTED)
                .create();
        String json=gson.toJson(book);
        System.out.println("json = " + json);

        String jsonString="{\"title\":\"Java\",\"description\":\"Men\",\"price\":30.0,\"published\":\"Nov 20, 2023, 5:09:11 PM\"}";
        Book bookJson=gson.fromJson(jsonString, Book.class);
        System.out.println(bookJson);


    }
}
