package org.example.project.bot.handler;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class HandlerManager {
    public static void handle(Update update, TelegramLongPollingBot bot) {
        if (update.hasMessage()) {
            MessageHandler.handle(update.getMessage(), bot);
        } else if (update.hasCallbackQuery()) {
            CallbackHandler.handle(update.getCallbackQuery(), bot);
        }
    }
}
