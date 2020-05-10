package application.commands.valute;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
@Data
public class ValuteList {


    @JsonProperty("Date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;

    @JsonProperty("PreviousDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate previosDate;

    @JsonProperty("PreviousURL")
    private String previousURL;

    @JsonProperty("Timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate timeStamp;

    @JsonProperty("Valute")
    private Map<String,Valute> valutes = new HashMap<>();
}
