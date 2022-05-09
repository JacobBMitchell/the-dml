package dml.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtConverter converter;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.cors();
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/security/login").permitAll() //login
                .antMatchers(HttpMethod.POST, "/api/user").permitAll() //register
                .antMatchers( HttpMethod.GET, "/api/character" ).hasAuthority("ROLE_ADMIN")
                .antMatchers( HttpMethod.GET, "/api/character/*" ).hasAnyAuthority("ROLE_PLAYER","ROLE_ADMIN")
                .antMatchers( HttpMethod.POST, "/api/character" ).hasAnyAuthority("ROLE_PLAYER")
                .antMatchers( HttpMethod.PUT, "/api/character/*" ).hasAnyAuthority("ROLE_PLAYER","ROLE_ADMIN")
                .antMatchers( HttpMethod.DELETE, "/api/character/*" ).hasAnyAuthority("ROLE_PLAYER","ROLE_ADMIN")
                .antMatchers( HttpMethod.GET, "/api/character/user/*" ).hasAnyAuthority("ROLE_PLAYER","ROLE_ADMIN")
                .antMatchers( HttpMethod.GET, "/api/character/campaign/*" ).hasAnyAuthority("ROLE_DM","ROLE_ADMIN")
                .antMatchers( HttpMethod.GET, "/api/campaign" ).authenticated()
                .antMatchers(HttpMethod.GET,"/api/campaign/*").hasAnyAuthority("ROLE_DM","ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/api/campaign/user/*").hasAnyAuthority("ROLE_DM","ROLE_ADMIN")
                .antMatchers(HttpMethod.POST,"/api/campaign").hasAnyAuthority("ROLE_DM","ROLE_ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/campaign/*").hasAnyAuthority("ROLE_DM","ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/campaign/*").hasAnyAuthority("ROLE_DM","ROLE_ADMIN")
                .antMatchers( HttpMethod.GET, "/api/user/*").authenticated()
                .antMatchers("/**").denyAll()
                .and()
                .addFilter(new JwtRequestFilter(authenticationManager(), converter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
