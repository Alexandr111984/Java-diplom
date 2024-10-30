package sobinda.javadiplomcloud.entity;

import jakarta.persistence.*;
import lombok.*;
import sobinda.javadiplomcloud.model.Role;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(nullable = false)
    private String login;


    @Column(name="password")
    private String password;


    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Set<Role> roles;

    @Transient
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<CloudFileEntity> cloudFileEntityList;

    public UserEntity() {
    }

//    @Override
//    public String toString() {
//        return "UserEntity{" +
//                "id=" + id +
//                ", login='" + login + '\'' +
//                ", password='" + password + '\'' +
//                ", roles=" + roles +
//                ", cloudFileEntityList=" + cloudFileEntityList +
//                '}';
//    }




    public void setRole(Role role) {
    }



    public void getRole(Role role) {

    }
}
