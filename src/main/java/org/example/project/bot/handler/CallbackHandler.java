package org.example.project.bot.handler;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public class CallbackHandler {
    @SneakyThrows
    public static void handle(CallbackQuery callbackQuery, TelegramLongPollingBot bot) {
        SendMessage sendMessage = new SendMessage(callbackQuery.getMessage().getChatId().toString(), callbackQuery.getData());
        bot.execute(sendMessage);
    }
}
