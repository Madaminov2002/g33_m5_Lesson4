package org.example.project.bot.handler;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {
    public static void handle(final Message message, final TelegramLongPollingBot bot) {
        switch (CommadEnum.of(message.getText())) {
            case START -> handleCommandStart(message, bot);
        }
    }

    @SneakyThrows
    private static void handleCommandStart(final Message message, final TelegramLongPollingBot bot) {
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//
//        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
//
//        buttons.add(List.of(
//                InlineKeyboardButton.builder().text("Button-1").callbackData("btn1").build(),
//                InlineKeyboardButton.builder().text("Button-2").callbackData("btn2").build()));
//
//        buttons.add(List.of(InlineKeyboardButton.builder().text("Button-3").callbackData("btn3").build()));
//
//        buttons.add(List.of(
//                InlineKeyboardButton.builder().text("Button-4").callbackData("btn4").build(),
//                InlineKeyboardButton.builder().text("Button-5").callbackData("btn5").build()));
//
//
//        inlineKeyboardMarkup.setKeyboard(buttons);

        InlineKeyboardMarkup keyboardMarkup = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(
                        InlineKeyboardButton.builder().text("With firstName").callbackData("name").build(),
                        InlineKeyboardButton.builder().text("With lastName").callbackData("last").build()))
                .keyboardRow(List.of(InlineKeyboardButton.builder().text("With ID").callbackData("ID").build()))
                .keyboardRow(List.of(
                        InlineKeyboardButton.builder().text("With phoneNumber").callbackData("number").build(),
                        InlineKeyboardButton.builder().text("With city").callbackData("city").build()))
                .build();

        SendMessage sendMessage = new SendMessage(message.getChatId().toString(), "Search");
        sendMessage.setReplyMarkup(keyboardMarkup);
        bot.execute(sendMessage);
    }
}
