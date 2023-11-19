package org.example.project.bot.handler;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;


public class MessageHandler {


    @SneakyThrows
    public static void handle(Message message, TelegramLongPollingBot bot) {
        if (message.isCommand()) {
            CommandHandler.handle(message, bot);
        }
    }
}
