package application.model.users;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "telegram_id")
    private Integer telegramId;
    @Column(name = "language_code")
    private String languageCode;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "updated")
    private LocalDateTime updated;

    @OneToMany(mappedBy = "customer")
    private List<UserRequest> userRequests = new ArrayList<>();

    @PrePersist
    public void  toCreate(){
        if (created == null){
            setCreated(LocalDateTime.now());
        }
    }
    @PreUpdate
    public void toUpdate(){
        setUpdated(LocalDateTime.now());
    }
    public Customer(){}
    public Customer(String firstName, String lastName, Integer telegramId, String languageCode, String userName, Long chatId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telegramId = telegramId;
        this.languageCode = languageCode;
        this.userName = userName;
        this.chatId = chatId;
    }
}
