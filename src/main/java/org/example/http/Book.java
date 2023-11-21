package org.example.http;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Book {
    @Expose
    private String title;
    @Expose
    private String description;
    @Expose
    protected double price;
    @Expose
    @SerializedName("published_name")
    private Date published;
}
