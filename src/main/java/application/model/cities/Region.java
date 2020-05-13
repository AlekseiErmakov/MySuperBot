package application.model.cities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Country country;

    private String name;

    @Column(name = "crt_date")
    private LocalDateTime crtDate;

}
