package web.javaConfig.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(userBuilder.username("Den")
                        .password("den")
                        .roles("ADMIN","VIP"))
                .withUser((userBuilder.username("Sasha"))
                        .password("sasha")
                        .roles("VIP"))
                .withUser((userBuilder.username("Miron"))
                        .password("sasha")
                        .roles("USER"));

    }
}
