package net.riezebos.graphql.security;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class SampleAuthenticationProvider implements AuthenticationProvider {

  public SampleAuthenticationProvider() {
  }

  @Override
  public Authentication authenticate(Authentication auth) {
    // find user by token in auth.getCredentials().toString() or throw if not found

    return new UsernamePasswordAuthenticationToken("hardcodedUser", auth.getCredentials().toString(), getAuthorities("admin"));
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }

  private Collection<? extends GrantedAuthority> getAuthorities(String userRole) {
    var authorities = new ArrayList<GrantedAuthority>();

    if (StringUtils.isNotBlank(userRole)) {
      authorities.add(new SimpleGrantedAuthority(userRole));
    }

    return authorities;
  }

}
