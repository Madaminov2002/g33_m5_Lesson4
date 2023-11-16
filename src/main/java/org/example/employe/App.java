package org.example.employe;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        String s = Files.readString(Path.of("C:\\Users\\User\\IdeaProjects\\g33_m5_Lesson4\\src\\main\\java\\recourse\\MOCK_DATA (2).json"));
        Type type = new TypeToken<List<Employe>>(){}.getType();
        List<Employe> employes=new GsonBuilder().create().fromJson(s,type);
        List<Employe> collect = employes.stream()
                .filter(employe -> employe.getAge() > 30 && employe.getAge() < 40)
                .filter(employe -> Pattern.matches("^[abc].+",employe.getEmail()))
                .limit(3).collect(Collectors.toList());
//        collect.forEach(System.out::println);

        List<EmployeDetails> female = employes.stream().filter(employe -> employe.getAge() == 18 && employe.getGender().equals("Female"))
                .map(employe -> new EmployeDetails(employe.getId(), employe.getEmail(), employe.getGender())).collect(Collectors.toList());
        System.out.println(female.get(0));

      //  female.forEach(System.out::println);


    }
}
