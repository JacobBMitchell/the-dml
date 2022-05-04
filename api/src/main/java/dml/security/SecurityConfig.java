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
        //TODO: use http to set up antMatchers to control
        //  access per endpoint
        http.csrf().disable();
        http.cors();
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/security/login").permitAll()
                .antMatchers( HttpMethod.GET, "/api/character/*" ).authenticated()
                .antMatchers( HttpMethod.POST, "/api/character/*" ).authenticated()
                .antMatchers( HttpMethod.PUT, "/api/character/*" ).authenticated()
                .antMatchers( HttpMethod.DELETE, "/api/character/*" ).authenticated()
                .antMatchers( HttpMethod.GET, "/api/character/user/*" ).authenticated()
                .antMatchers( HttpMethod.GET, "/api/character/campaign/*" ).authenticated()
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
