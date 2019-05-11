package hu.bme.sch.bss.webcentral.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

@Configuration
@EnableWebSecurity
public class MvcConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected final void configure(final HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().fullyAuthenticated()
            .and()
            .formLogin();
    }

    @Override
    protected final void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
            .ldapAuthentication()
                .userDnPatterns("OU=Budavári Schönherz Stúdió,OU=Simonyi Károly Szakkollégium,DC=sch,DC=bme,DC=hu")
                .groupSearchBase("OU=Users")
                .contextSource()
                    .url("")
                    .managerDn("olvasok@sch.bme.hu")
                    .managerPassword("")
                    .and()
                .passwordCompare()
                    .passwordEncoder(new LdapShaPasswordEncoder())
                    .passwordAttribute("userPassword");
    }
}
