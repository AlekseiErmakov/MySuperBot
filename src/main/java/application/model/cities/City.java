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
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Region region;

    private String name;

    @Column(name = "crt_date")
    private LocalDateTime crtDate;
}
