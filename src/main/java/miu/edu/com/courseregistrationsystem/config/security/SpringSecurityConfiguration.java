package miu.edu.com.courseregistrationsystem.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    CustomUserDetailsService customUserDetailsService;
//
//    @Autowired
//    private JwtAuthenticationEntryPoint unauthorizedHandler;

//    @Bean
    public CustomJwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new CustomJwtAuthenticationFilter();
    }

    //@Bean
    public RegistrationBean jwtAuthFilterRegister(CustomJwtAuthenticationFilter filter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     //   auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/h2-console/**");
//        web
//                .ignoring()
//                .antMatchers("/register");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
//        http.csrf().disable().authorizeRequests()
////                .authorizeRequests().antMatchers("/**").hasAnyAuthority("ADMIN")
////                TODO REMOVE AFTER AUTH ON REACT
//                .antMatchers("/**").permitAll()
//                .antMatchers("/authenticate").permitAll()
//                .antMatchers("/register").permitAll().anyRequest().authenticated()
//                .and().exceptionHandling()
//                .authenticationEntryPoint(unauthorizedHandler).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//               http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);



//        http.csrf().disable().authorizeRequests()
////                .authorizeRequests().antMatchers("/**").hasAnyAuthority("ADMIN")
////                TODO REMOVE AFTER AUTH ON REACT
//                .antMatchers("/**").permitAll()
//                .antMatchers("/authenticate").permitAll()
//                .antMatchers("/register").permitAll().anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/home/log_in")
//                .defaultSuccessUrl("/home/")
//                .failureUrl("/home/log_in?error")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/home/logout")
//                .logoutSuccessUrl("/home/log_in")
//                .permitAll()
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(unauthorizedHandler).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//       .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/home/log_in")
//                .defaultSuccessUrl("/home/")
//                .failureUrl("/home/log_in?error")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/home/logout")
//                .logoutSuccessUrl("/home/log_in")
//                .permitAll()
//                .and()
//                .exceptionHandling();
    }



    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
