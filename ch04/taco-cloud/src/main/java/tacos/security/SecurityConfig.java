package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web
                        .configuration.WebSecurityConfigurerAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation
             .authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web
             .builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  

  @Autowired
  private UserDetailsService userDetailsService;
  

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
        .antMatchers("/design", "/orders").access("hasRole('ROLE_USER')")
        .antMatchers("/", "/**").access("permitAll")
        
      .and()
        .formLogin()
          .loginPage("/login")
          
      .and()
        .logout()
          .logoutSuccessUrl("/")
          
      // Make H2-Console non-secured; for debug purposes
      .and()
        .csrf()
          .ignoringAntMatchers("/h2-console/**")

      // Allow pages to be loaded in frames from the same origin; needed for H2-Console
      .and()  
        .headers()
          .frameOptions()
            .sameOrigin()
            
      ;
  }

  /*
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {

    auth
      .userDetailsService(userDetailsService);
    
  }
  
   */

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }
  
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {

    auth
      .userDetailsService(userDetailsService)
      .passwordEncoder(encoder());
    
  }
  
//
// IN MEMORY AUTHENTICATION EXAMPLE
//
/*
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
    
    auth
      .inMemoryAuthentication()
        .withUser("buzz")
          .password("infinity")
          .authorities("ROLE_USER")
        .and()
        .withUser("woody")
          .password("bullseye")
          .authorities("ROLE_USER");
    
  }
*/

//
// JDBC Authentication example
//
/*
  @Autowired
  DataSource dataSource;
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
    
    auth
      .jdbcAuthentication()
        .dataSource(dataSource);
    
  }
*/

/*
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
    
    auth
      .jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery(
            "select username, password, enabled from Users " +
            "where username=?")
        .authoritiesByUsernameQuery(
            "select username, authority from UserAuthorities " +
            "where username=?");
    
  }
*/

/*
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
    
    auth
      .jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery(
            "select username, password, enabled from Users " +
            "where username=?")
        .authoritiesByUsernameQuery(
            "select username, authority from UserAuthorities " +
            "where username=?")
        .passwordEncoder(new BCryptPasswordEncoder());
    
  }
*/
  
  
//
// LDAP Authentication example
//
/*
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
    auth
      .ldapAuthentication()
        .userSearchFilter("(uid={0})")
        .groupSearchFilter("member={0}");
  }
*/
  
/*
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
    auth
      .ldapAuthentication()
        .userSearchBase("ou=people")
        .userSearchFilter("(uid={0})")
        .groupSearchBase("ou=groups")
        .groupSearchFilter("member={0}");
  }
*/
  
/*
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
    auth
      .ldapAuthentication()
        .userSearchBase("ou=people")
        .userSearchFilter("(uid={0})")
        .groupSearchBase("ou=groups")
        .groupSearchFilter("member={0}")
        .passwordCompare();
  }
*/
  
/*
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
    auth
      .ldapAuthentication()
        .userSearchBase("ou=people")
        .userSearchFilter("(uid={0})")
        .groupSearchBase("ou=groups")
        .groupSearchFilter("member={0}")
        .passwordCompare()
        .passwordEncoder(new BCryptPasswordEncoder())
        .passwordAttribute("passcode");
  }
*/
  
/*
@Override
protected void configure(AuthenticationManagerBuilder auth)
    throws Exception {
  auth
    .ldapAuthentication()
      .userSearchBase("ou=people")
      .userSearchFilter("(uid={0})")
      .groupSearchBase("ou=groups")
      .groupSearchFilter("member={0}")
      .passwordCompare()
      .passwordEncoder(new BCryptPasswordEncoder())
      .passwordAttribute("passcode")
      .and()
      .contextSource()
        .url("ldap://tacocloud.com:389/dc=tacocloud,dc=com");
}
*/
  
/*
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
    auth
      .ldapAuthentication()
        .userSearchBase("ou=people")
        .userSearchFilter("(uid={0})")
        .groupSearchBase("ou=groups")
        .groupSearchFilter("member={0}")
        .passwordCompare()
        .passwordEncoder(new BCryptPasswordEncoder())
        .passwordAttribute("passcode")
        .and()
        .contextSource()
          .root("dc=tacocloud,dc=com");
  }
*/
  
/*
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
    auth
      .ldapAuthentication()
        .userSearchBase("ou=people")
        .userSearchFilter("(uid={0})")
        .groupSearchBase("ou=groups")
        .groupSearchFilter("member={0}")
        .passwordCompare()
        .passwordEncoder(new BCryptPasswordEncoder())
        .passwordAttribute("passcode")
        .and()
        .contextSource()
          .root("dc=tacocloud,dc=com")
          .ldif("classpath:users.ldif");
  }
*/
  
}
