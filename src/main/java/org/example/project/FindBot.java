package org.example.project;

import lombok.SneakyThrows;
import org.example.project.bot.handler.HandlerManager;
import org.example.project.bot.handler.MessageHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.regex.Pattern;


public class FindBot extends TelegramLongPollingBot {
    public FindBot() {
        super("6831197809:AAFMzMfWDVSYugTEfDlGij92lCvHiOMxP2k");
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
            HandlerManager.handle(update,this);
    }

        @Override
        public String getBotUsername () {
            return "tester_suhrob161_bot";
        }
    }
