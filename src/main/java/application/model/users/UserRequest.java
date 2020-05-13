package application.model.users;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "user_request")
public class UserRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="request_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String command;

    private String body;

    @Column(name = "created")
    private LocalDateTime created;

    @PrePersist
    public void  toCreate(){
        if (created == null){
            setCreated(LocalDateTime.now());
        }
    }
    public UserRequest(){}
}
