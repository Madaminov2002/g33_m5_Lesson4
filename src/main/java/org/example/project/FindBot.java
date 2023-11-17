package org.example.project;

import lombok.SneakyThrows;
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
        PersonDetails personDetails=new PersonDetails();
        if(update.hasMessage()){
            Message message=update.getMessage();
            if(message.hasText()){
                String text=message.getText();
                if (text.equals("/find")){

                    Maps.USER_STEPS.put(message.getChatId(),Steps.ID);
                    this.execute(new SendMessage(message.getChatId().toString(),"Enter ID: "));
                } else if (Maps.USER_STEPS.get(message.getChatId()) == Steps.ID) {
                    Long ID= Long.valueOf(message.getText());

                    personDetails.setId(ID);

                    if (Pattern.matches("[0-9]{1,4}",String.valueOf(ID))){
                        Maps.USER_STEPS.put(message.getChatId(),Steps.FIRST_NAME);
                        this.execute(new SendMessage(message.getChatId().toString(),"Enter FirstName: "));
                    }else {
                        this.execute(new SendMessage(message.getChatId().toString(),"re-enter the information!" + "\nexample:1/11/111/1111"));
                    }
                } else if (Maps.USER_STEPS.get(message.getChatId()) == Steps.FIRST_NAME) {
                    String firstName=message.getText();
                    personDetails.setFirstName(firstName);
                    if (Pattern.matches("[A-Z][a-z]+",firstName)){
                        Maps.USER_STEPS.put(message.getChatId(),Steps.LAST_NAME);
                        this.execute(new SendMessage(message.getChatId().toString(),"Enter LastName: "));
                    }else {
                        this.execute(new SendMessage(message.getChatId().toString(),"re-enter the information!"+ "\nexample: John"));
                    }
                } else if (Maps.USER_STEPS.get(message.getChatId()) == Steps.LAST_NAME) {
                    String lastName=message.getText();
                 personDetails.setLastName(lastName);
                    if (Pattern.matches("[A-Z][a-z]+",lastName)){
                        Maps.USER_STEPS.put(message.getChatId(),Steps.GENDER);
                        this.execute(new SendMessage(message.getChatId().toString()," Enter Gender:"));
                    }else {
                        this.execute(new SendMessage(message.getChatId().toString(),"re-enter the information!"+ "\nexample: Falonchiyev"));
                    }
                } else if (Maps.USER_STEPS.get(message.getChatId()) == Steps.GENDER) {
                    String gender=message.getText();
                    personDetails.setGender(gender);
                    if (Pattern.matches("[A-Za-z]+",gender)){
                        Maps.USER_STEPS.put(message.getChatId(),Steps.PHONE_NUMBER);
                        this.execute(new SendMessage(message.getChatId().toString(),"Enter PhoneNumber: ") );
                    }else {
                        this.execute(new SendMessage(message.getChatId().toString(),"re-enter the information!"+ "\nexample: Male/male or Female/female"));
                    }
                } else if (Maps.USER_STEPS.get(message.getChatId()) == Steps.PHONE_NUMBER) {
                    String phoneNum=message.getText();
                    personDetails.setPhoneNumber(phoneNum);
                    if (Pattern.matches("[0-9]{10}",phoneNum)){
                        Maps.USER_STEPS.put(message.getChatId(),Steps.CITY);
                        this.execute(new SendMessage(message.getChatId().toString(),"Enter City: "));
                    }else {
                        this.execute(new SendMessage(message.getChatId().toString(),"re-enter the information!"+ "\nexample:1234567891"));
                    }
                } else if (Maps.USER_STEPS.get(message.getChatId()) == Steps.CITY) {
                    String city=message.getText();
                   personDetails.setCity(city);
                    if (Pattern.matches("[A-Z][a-z]+",city)){
                        Maps.USER_STEPS.put(message.getChatId(),Steps.BIRTH_DATE);
                        this.execute(new SendMessage(message.getChatId().toString(),"Enter BirthDate: "));
                    }else {
                        this.execute(new SendMessage(message.getChatId().toString(),"re-enter the information!"+ "\nexample:London"));
                    }
                } else if (Maps.USER_STEPS.get(message.getChatId()) == Steps.BIRTH_DATE) {
                    String birthDate=message.getText();
                   personDetails.setBirthDate(birthDate);

                    String str = Manager.filterPerson(personDetails);
                    if (Pattern.matches("[0-9]{2}\\-[0-9]{2}\\-[0-9]{4}",birthDate)){
                            Maps.USER_STEPS.put(message.getChatId(),Steps.APP_FINISHED);
                            this.execute(new SendMessage(message.getChatId().toString(), str+" /find"));
                        }
                    }else {
                        this.execute(new SendMessage(message.getChatId().toString(),"re-enter the information!"+ "\nexample:12-12-2003"));
                    }
                }
            }
        }


    @Override
    public String getBotUsername() {
        return "tester_suhrob161_bot";
    }
}
