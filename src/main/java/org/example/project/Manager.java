package org.example.project;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Manager {
    public static String filterPerson( PersonDetails personDetails) throws IOException {
        String data= Files.readString(Path.of("C:\\Users\\User\\IdeaProjects\\g33_m5_Lesson4\\src\\main\\java\\recourse\\MOCK_DATA (3).json"));
        Type type = new TypeToken<List<ModelPerson>>() {}.getType();
        List<ModelPerson> list = new GsonBuilder().create().fromJson(data, type);
        List<ModelPerson> collect = list.stream()
                .filter(modelPerson1 -> modelPerson1.getId() == personDetails.getId() &&
                        modelPerson1.getFirstName().equals(personDetails.getFirstName()) &&
                        modelPerson1.getLastName().equals(personDetails.getLastName()) &&
                        modelPerson1.getGender().equals(personDetails.getGender()) &&
                        modelPerson1.getPhoneNumber().equals(personDetails.getPhoneNumber()) &&
                        modelPerson1.getCity().equals(personDetails.getCity()) &&
                        modelPerson1.getBirthDate().equals(personDetails.getBirthDate())).collect(Collectors.toList());
        if (!collect.isEmpty()){
            return collect.get(0).toString();
        }
        return "Data not found!";
    }
}
