//tag::securityConfigOuterClass[]
package tacos.security;

import org.springframework.context.annotation.Bean;
//end::securityConfigOuterClass[]
//tag::baseBonesImports[]
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web
                        .configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web
                        .configuration.WebSecurityConfigurerAdapter;
//end::baseBonesImports[]

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation
             .authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web
             .builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

//tag::securityConfigOuterClass[]

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
//end::securityConfigOuterClass[]

//tag::customUserDetailsService[]
  @Autowired
  private UserDetailsService userDetailsService;
  
//end::customUserDetailsService[]

  //tag::configureHttpSecurity[]
  //tag::authorizeRequests[]
  //tag::customLoginPage[]
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
        .antMatchers("/design", "/orders")
          .access("hasRole('ROLE_USER')")
        .antMatchers("/", "/**").access("permitAll")
        //end::authorizeRequests[]
        
      .and()
        .formLogin()
          .loginPage("/login")
        //end::customLoginPage[]
          
      // tag::enableLogout[]
      .and()
        .logout()
          .logoutSuccessUrl("/")
      // end::enableLogout[]
          
      // Make H2-Console non-secured; for debug purposes
      // tag::csrfIgnore[]
      .and()
        .csrf()
          .ignoringAntMatchers("/h2-console/**")
      // end::csrfIgnore[]

      // Allow pages to be loaded in frames from the same origin; needed for H2-Console
      // tag::frameOptionsSameOrigin[]
      .and()  
        .headers()
          .frameOptions()
            .sameOrigin()
      // end::frameOptionsSameOrigin[]
            
      //tag::authorizeRequests[]
      //tag::customLoginPage[]
      ;
  }
//end::configureHttpSecurity[]
//end::authorizeRequests[]
//end::customLoginPage[]

  /*
  //tag::customUserDetailsService[]
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {

    auth
      .userDetailsService(userDetailsService);
    
  }
  //end::customUserDetailsService[]
  
   */

  //tag::customUserDetailsService_withPasswordEncoder[]
  @Bean
  public PasswordEncoder encoder() {
    return new StandardPasswordEncoder("53cr3t");
  }
  
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {

    auth
      .userDetailsService(userDetailsService)
      .passwordEncoder(encoder());
    
  }
  //end::customUserDetailsService_withPasswordEncoder[]
  
//
// IN MEMORY AUTHENTICATION EXAMPLE
//
/*
//tag::configureAuthentication_inMemory[]
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
//end::configureAuthentication_inMemory[]
*/

//
// JDBC Authentication example
//
/*
//tag::configureAuthentication_jdbc[]
  @Autowired
  DataSource dataSource;
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
    
    auth
      .jdbcAuthentication()
        .dataSource(dataSource);
    
  }
//end::configureAuthentication_jdbc[]
*/

/*
//tag::configureAuthentication_jdbc_withQueries[]
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
//end::configureAuthentication_jdbc_withQueries[]
*/

/*
//tag::configureAuthentication_jdbc_passwordEncoder[]
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
        .passwordEncoder(new StandardPasswordEncoder("53cr3t");
    
  }
//end::configureAuthentication_jdbc_passwordEncoder[]
*/
  
  
//
// LDAP Authentication example
//
/*
//tag::configureAuthentication_ldap[]
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
    auth
      .ldapAuthentication()
        .userSearchFilter("(uid={0})")
        .groupSearchFilter("member={0}");
  }
//end::configureAuthentication_ldap[]
*/
  
//tag::securityConfigOuterClass[]

}
//end::securityConfigOuterClass[]
