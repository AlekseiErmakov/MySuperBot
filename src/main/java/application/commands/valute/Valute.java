package application.commands.valute;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Valute {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("NumCode")
    private int numCode;

    @JsonProperty("CharCode")
    private String charCode;

    @JsonProperty("Nominal")
    private int nominal;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Value")
    private Double value;

    @JsonProperty("Previous")
    private Double previous;
}
