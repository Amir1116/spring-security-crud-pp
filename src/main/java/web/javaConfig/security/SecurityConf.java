package web.javaConfig.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    @Bean
    public UUserDetailsService uUserDetailsService(){
        return new UUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(uUserDetailsService());
//        daoAuthenticationProvider.setPasswordEncoder();
        return daoAuthenticationProvider;
    }

    @Autowired
    public SecurityConf(@Qualifier("getDataSource") DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").anonymous()
                .antMatchers("/{id}/delete").hasAuthority("ADMIN")
                .antMatchers("/{id}/edit").hasAuthority("ADMIN")
                .antMatchers("/new").hasAuthority("ADMIN")
                .antMatchers("/{id}/show").hasAuthority("USER")
                .and().formLogin().permitAll();

    }
}
