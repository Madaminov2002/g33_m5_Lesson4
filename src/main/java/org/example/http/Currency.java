package org.example.http;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Currency {
    private String title;
    private String code;
    @SerializedName("cb_price")
    private String cbPrice;
    @SerializedName("nbu_buy_price")
    private String nbuBuyPrice;
   @SerializedName("nbu_cell_price")
    private String nbuCellPrice;
    private String date;
}
