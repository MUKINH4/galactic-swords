package com.galaxy_swords.mvc.model;

import com.galaxy_swords.mvc.model.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Adventurer extends DefaultOAuth2User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Email
    private String email;

    private String password;

    private String nome;

    @Column(name = "picture_url")
    private String picture;

    @Enumerated(EnumType.STRING)
    private Roles role = Roles.ROLE_USER;

    private double gold = 100.0;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sword> swords;

    public Adventurer(OAuth2User principal) {
        super(
            List.of(new SimpleGrantedAuthority(Roles.ROLE_USER.name())),
            principal.getAttributes(),
            "name"
        );
            this.nome = principal.getAttribute("name");
            this.email = principal.getAttribute("email");
            this.picture = principal.getAttribute("picture");
            this.gold = 100.0;
            this.role = Roles.ROLE_USER;
    }

    public Adventurer() {
        super(
            List.of(new SimpleGrantedAuthority(Roles.ROLE_USER.name())),
            Map.of("name", "unknown"),
            "name"
        );
    }
}
