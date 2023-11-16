package org.example.project;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class FindBot extends TelegramLongPollingBot {
    public FindBot() {
        super("6831197809:AAFMzMfWDVSYugTEfDlGij92lCvHiOMxP2k");
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            Message message=update.getMessage();
            if(message.hasText()){
                String text=message.getText();
                if (text.equals("/find")){
                    Maps.USER_STEPS.put(message.getChatId(),Steps.ID);
                    this.execute(new SendMessage(message.getChatId().toString(),"Enter ID: "));
                } else if (Maps.USER_STEPS.get(message.getChatId()) == Steps.ID) {
                    Long ID= Long.valueOf(message.getText());
                    PersonDetails personDetails = new PersonDetails();
                    personDetails.setId(ID);
                    Maps.USER_INFORMATION.put(message.getChatId(),personDetails);
                    if (Pattern.matches("[0-9]{1,4}",String.valueOf(ID))){
                        Maps.USER_STEPS.put(message.getChatId(),Steps.FIRST_NAME);
                        this.execute(new SendMessage(message.getChatId().toString(),"Enter FirstName: "));
                    }else {
                        this.execute(new SendMessage(message.getChatId().toString(),"re-enter the information!" + "\nexample:1/11/111/1111"));
                    }
                } else if (Maps.USER_STEPS.get(message.getChatId()) == Steps.FIRST_NAME) {
                    String firstName=message.getText();
                    Maps.USER_INFORMATION.get(message.getChatId()).setFirstName(firstName);
                    if (Pattern.matches("[A-Z][a-z]+",firstName)){
                        Maps.USER_STEPS.put(message.getChatId(),Steps.LAST_NAME);
                        this.execute(new SendMessage(message.getChatId().toString(),"Enter LastName: "));
                    }else {
                        this.execute(new SendMessage(message.getChatId().toString(),"re-enter the information!"+ "\nexample: John"));
                    }
                } else if (Maps.USER_STEPS.get(message.getChatId()) == Steps.LAST_NAME) {
                    String lastName=message.getText();
                    Maps.USER_INFORMATION.get(message.getChatId()).setLastName(lastName);
                    if (Pattern.matches("[A-Z][a-z]+",lastName)){
                        Maps.USER_STEPS.put(message.getChatId(),Steps.GENDER);
                        this.execute(new SendMessage(message.getChatId().toString()," Enter Gender:"));
                    }else {
                        this.execute(new SendMessage(message.getChatId().toString(),"re-enter the information!"+ "\nexample: Falonchiyev"));
                    }
                } else if (Maps.USER_STEPS.get(message.getChatId()) == Steps.GENDER) {
                    String gender=message.getText();
                    Maps.USER_INFORMATION.get(message.getChatId()).setGender(gender);
                    if (Pattern.matches("[A-Za-z]+",gender)){
                        Maps.USER_STEPS.put(message.getChatId(),Steps.PHONE_NUMBER);
                        this.execute(new SendMessage(message.getChatId().toString(),"Enter PhoneNumber: ") );
                    }else {
                        this.execute(new SendMessage(message.getChatId().toString(),"re-enter the information!"+ "\nexample: Male/male or Female/female"));
                    }
                } else if (Maps.USER_STEPS.get(message.getChatId()) == Steps.PHONE_NUMBER) {
                    String phoneNum=message.getText();
                    Maps.USER_INFORMATION.get(message.getChatId()).setPhoneNumber(phoneNum);
                    if (Pattern.matches("[0-9]{10}",phoneNum)){
                        Maps.USER_STEPS.put(message.getChatId(),Steps.CITY);
                        this.execute(new SendMessage(message.getChatId().toString(),"Enter City: "));
                    }else {
                        this.execute(new SendMessage(message.getChatId().toString(),"re-enter the information!"+ "\nexample:1234567891"));
                    }
                } else if (Maps.USER_STEPS.get(message.getChatId()) == Steps.CITY) {
                    String city=message.getText();
                    Maps.USER_INFORMATION.get(message.getChatId()).setCity(city);
                    if (Pattern.matches("[A-Z][a-z]+",city)){
                        Maps.USER_STEPS.put(message.getChatId(),Steps.BIRTH_DATE);
                        this.execute(new SendMessage(message.getChatId().toString(),"Enter BirthDate: "));
                    }else {
                        this.execute(new SendMessage(message.getChatId().toString(),"re-enter the information!"+ "\nexample:London"));
                    }
                } else if (Maps.USER_STEPS.get(message.getChatId()) == Steps.BIRTH_DATE) {
                    String birthDate=message.getText();
                    Maps.USER_INFORMATION.get(message.getChatId()).setBirthDate(birthDate);

                    String data= Files.readString(Path.of("C:\\Users\\User\\IdeaProjects\\g33_m5_Lesson4\\src\\main\\java\\recourse\\MOCK_DATA (3).json"));
                    Type type = new TypeToken<List<ModelPerson>>() {}.getType();
                    List<ModelPerson> list = new GsonBuilder().create().fromJson(data, type);
                    List<ModelPerson> collect = list.stream()
                            .filter(modelPerson -> modelPerson.getId() == Maps.USER_INFORMATION.get(message.getChatId()).getId() &&
                                    modelPerson.getFirstName().equals(Maps.USER_INFORMATION.get(message.getChatId()).getFirstName()) &&
                                    modelPerson.getLastName().equals(Maps.USER_INFORMATION.get(message.getChatId()).getLastName()) &&
                                    modelPerson.getGender().equals(Maps.USER_INFORMATION.get(message.getChatId()).getGender()) &&
                                    modelPerson.getPhoneNumber().equals(Maps.USER_INFORMATION.get(message.getChatId()).getPhoneNumber()) &&
                                    modelPerson.getCity().equals(Maps.USER_INFORMATION.get(message.getChatId()).getCity()) &&
                                    modelPerson.getBirthDate().equals(Maps.USER_INFORMATION.get(message.getChatId()).getBirthDate())).collect(Collectors.toList());
                    if (Pattern.matches("[0-9]{2}\\-[0-9]{2}\\-[0-9]{4}",birthDate)){
                        if (!collect.isEmpty()){
                            Maps.USER_STEPS.put(message.getChatId(),Steps.APP_FINISHED);
                            this.execute(new SendMessage(message.getChatId().toString(), collect.get(0).toString()));
                        }else {
                            this.execute(new SendMessage(message.getChatId().toString(),"Data not found! /find"));
                        }
                    }else {
                        this.execute(new SendMessage(message.getChatId().toString(),"re-enter the information!"+ "\nexample:12-12-2003"));
                    }
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "tester_suhrob161_bot";
    }
}
