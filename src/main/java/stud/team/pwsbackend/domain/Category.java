package stud.team.pwsbackend.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Data
public class Category {

    @Id
    @Column(name = "id_category")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "categories")
    private List<Stream> streams;
}
