package com.company.security;

import com.company.security.jwt.JwtTokenProvider;
import com.company.security.jwt.JwtSecurityConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET, "/sales").permitAll()
                .antMatchers(HttpMethod.GET, "/goods").permitAll()
                .antMatchers(HttpMethod.GET, "/warehouse1").permitAll()
                .antMatchers(HttpMethod.GET, "/warehouse2").permitAll()
                .antMatchers(HttpMethod.GET, "/sales/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/goods/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/warehouse1/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/warehouse2/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/sales/new").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/goods/new").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/warehouse1/new").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/warehouse2/new").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/goods/{id}/edit").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/sales/{id}/edit").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/warehouse1/{id}/edit").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/warehouse2/{id}/edit").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/goods/{id}/delete").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/sales/{id}/delete").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/warehouse1/{id}/delete").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/warehouse2/{id}/delete").hasRole("ADMIN")
                /*.anyRequest().authenticated()*/
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .apply(new JwtSecurityConfigurer(jwtTokenProvider));
    }

}
