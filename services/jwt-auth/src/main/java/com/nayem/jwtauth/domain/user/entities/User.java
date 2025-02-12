package com.nayem.jwtauth.domain.user.entities;

import com.nayem.jwtauth.common.base.BaseEntity;
import com.nayem.jwtauth.domain.role.entities.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User extends BaseEntity implements UserDetails {
    @Column(name = "account_non_expired", nullable = false)
    private Boolean accountNonExpired = true;

    @Column(name = "account_non_locked", nullable = false)
    private Boolean accountNonLocked = true;

    @Column(name = "credentials_non_expired", nullable = false)
    private Boolean credentialsNonExpired = true;

    @Column(name = "email")
    private String email;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = true;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password", nullable = false, length = 512)
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /*Fetch Type:
      EAGER: Associated entities are immediately fetched along with the owning entity.
      This can lead to performance issues if the relationship involves a large number of entities.
      LAZY: Associated entities are fetched only when explicitly accessed.*/
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        for (Role role : roles) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
            grantedAuthorityList.add(grantedAuthority);
        }
        return grantedAuthorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
