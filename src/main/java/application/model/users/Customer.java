package application.model.users;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "customer")
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    private Boolean bot;

    private Integer telegramId;

    private String languageCode;

    private String userName;

    private Long chatId;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "customer")
    private List<UserRequest> requests = new ArrayList<>();

    public Customer(String firstName, String lastName, Boolean bot, Integer telegramId, String languageCode, String userName, Long chatId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bot = bot;
        this.telegramId = telegramId;
        this.languageCode = languageCode;
        this.userName = userName;
        this.chatId = chatId;
    }
}
