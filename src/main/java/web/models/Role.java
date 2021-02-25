package web.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @author Pavel Peskov
 */
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Role() {
    }

    public Role(String name) {
        if (name.contains("ADMIN")) {
            this.id = 1L;
        } else if (name.contains("USER")) {
            this.id = 2L;
        }
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }

}
