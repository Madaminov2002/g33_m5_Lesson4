package org.example.project.bot.handler;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HandlerManager {
    public static void handle(Update update, TelegramLongPollingBot bot) throws TelegramApiException {
        if (update.hasMessage()) {
            MessageHandler.handle(update.getMessage(), bot);
        } else if (update.hasCallbackQuery()) {
            CallbackHandler.handle(update.getCallbackQuery(), bot);
        }

    }
}
