package org.example.project.bot.handler;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import org.example.project.Maps;
import org.example.project.ModelPerson;
import org.example.project.Steps;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class CallbackHandler {
    @SneakyThrows
    public static void handle(CallbackQuery callbackQuery, TelegramLongPollingBot bot) {

        if (callbackQuery.getData().equals("name")) {
            Maps.USER_STEPS.put(callbackQuery.getMessage().getChatId(),Steps.FIRST_NAME);
            bot.execute(new SendMessage(callbackQuery.getMessage().getChatId().toString(), "Enter name:"));
        } else if (callbackQuery.getData().equals("last")) {
            Maps.USER_STEPS.put(callbackQuery.getMessage().getChatId(),Steps.LAST_NAME);
            bot.execute(new SendMessage(callbackQuery.getMessage().getChatId().toString(), "Enter last name:"));
        } else if (callbackQuery.getData().equals("ID")) {
            Maps.USER_STEPS.put(callbackQuery.getMessage().getChatId(),Steps.ID);
            bot.execute(new SendMessage(callbackQuery.getMessage().getChatId().toString(),"Enter ID: "));
        } else if (callbackQuery.getData().equals("number")) {
            Maps.USER_STEPS.put(callbackQuery.getMessage().getChatId(),Steps.PHONE_NUMBER);
            bot.execute(new SendMessage(callbackQuery.getMessage().getChatId().toString(),"Enter phoneNumber: "));
        } else if (callbackQuery.getData().equals("city")) {
            Maps.USER_STEPS.put(callbackQuery.getMessage().getChatId(),Steps.CITY);
            bot.execute(new SendMessage(callbackQuery.getMessage().getChatId().toString(),"Enter city:"));
        }


    }
}
