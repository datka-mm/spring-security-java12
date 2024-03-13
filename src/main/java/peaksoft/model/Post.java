package peaksoft.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "posts")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(generator = "post_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "post_gen", sequenceName = "post_seq", allocationSize = 1)
    private Long id;
    private String description;

    @ElementCollection
    private List<String> images;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    @ManyToOne
    private User user;
}
