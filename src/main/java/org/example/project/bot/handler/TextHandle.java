package org.example.project.bot.handler;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.project.Maps;
import org.example.project.ModelPerson;
import org.example.project.Steps;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TextHandle {
    public static void handle(Message message, TelegramLongPollingBot bot) throws IOException, TelegramApiException {
        String data = Files.readString(Path.of("C:\\Users\\User\\IdeaProjects\\g33_m5_Lesson4\\src\\main\\java\\recourse\\MOCK_DATA (3).json"));
        Type type = new TypeToken<List<ModelPerson>>() {
        }.getType();
        List<ModelPerson> list = new GsonBuilder().create().fromJson(data, type);

        String text = message.getText();
        Steps steps = Maps.USER_STEPS.get(message.getChatId());

        if (steps == Steps.FIRST_NAME) {
            List<ModelPerson> collect = list.stream().filter(modelPerson -> modelPerson.getFirstName().equals(text))
                    .collect(Collectors.toList());
            bot.execute(new SendMessage(message.getChatId().toString(), collect.get(0).toString()));
        } else if (steps == Steps.LAST_NAME) {
            List<ModelPerson> collect = list.stream().filter(modelPerson -> modelPerson.getLastName().equals(text))
                    .collect(Collectors.toList());
            bot.execute(new SendMessage(message.getChatId().toString(), collect.get(0).toString()));
        } else if (steps == Steps.ID) {
            List<ModelPerson> collect = list.stream().filter(modelPerson -> Objects.equals(modelPerson.getId(),Integer.parseInt(text)))
                    .collect(Collectors.toList());
            bot.execute(new SendMessage(message.getChatId().toString(), collect.get(0).toString()));
        } else if (steps == Steps.PHONE_NUMBER) {
            List<ModelPerson> collect = list.stream().filter(modelPerson -> modelPerson.getPhoneNumber().equals(text))
                    .collect(Collectors.toList());
            bot.execute(new SendMessage(message.getChatId().toString(), collect.get(0).toString()));
        } else if (steps == Steps.CITY) {
            List<ModelPerson> collect = list.stream().filter(modelPerson -> modelPerson.getCity().equals(text))
                    .collect(Collectors.toList());
            bot.execute(new SendMessage(message.getChatId().toString(), collect.get(0).toString()));
        }
    }
}
