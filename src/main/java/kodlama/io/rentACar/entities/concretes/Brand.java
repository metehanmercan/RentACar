package kodlama.io.rentACar.entities.concretes;


import lombok.*;

import javax.persistence.*;
import java.util.List;


@Table(name="brands")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Brand {
    @Id // PK
    @Column(name ="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
   private int id;

    @Column(name="name")
    private String name;



    @OneToMany(mappedBy = "brand",cascade = CascadeType.ALL)
    private List<Model> models;

}
