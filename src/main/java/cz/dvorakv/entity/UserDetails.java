package cz.dvorakv.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface UserDetails {

    String getUsername();

    String getPassword();

    Collection<? extends GrantedAuthority> getAuthorities();

    public boolean isAccountNonExpired();

    public boolean isAccountNonLocked();

    public boolean isCredentialsNonExpired();

    public boolean isEnabled();


}
