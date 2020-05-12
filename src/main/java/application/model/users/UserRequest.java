package application.model.users;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "user_request")
public class UserRequest {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    @Column(name = "is_command_request")
    private boolean isCommandRequest;
    private String command;
    private String body;
}
