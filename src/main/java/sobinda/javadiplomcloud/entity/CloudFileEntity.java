package sobinda.javadiplomcloud.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.UUID;

@jakarta.persistence.Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "Cloud_File")
public class CloudFileEntity {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "file_name", length = 100, nullable = false)
    private String fileName;

    @Column(name = "file_size")
    private Long size;

    @Column(nullable = false, name = "upload_date")
    private Instant date;

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(unique = true, name = "file_key")
    private UUID key;

    @jakarta.persistence.ManyToOne
    @jakarta.persistence.JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;
}
