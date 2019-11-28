package hu.bme.sch.bss.webcentral.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

@Configuration
@EnableWebSecurity
public class MvcConfiguration extends WebSecurityConfigurerAdapter {

    private static final String PASSWORD_ATTRIBUTE = "password";
    private static final Integer B_CRYPT_STRENGTH = 31;

    //@Value("hu.bme.sch.bss.webcentral.userDnPatterns")
    private String userDnPatterns;
    //@Value("hu.bme.sch.bss.webcentral.userSearchBase")
    private String userSearchBase;
    //7@Value("hu.bme.sch.bss.webcentral.ldap_url")
    private String ldap_url;


    @Override
    protected final void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build()
                );
        auth.ldapAuthentication()
                .userDnPatterns(userDnPatterns)
                .userSearchBase(userSearchBase)
                .contextSource()
                .url(ldap_url)
                .and()
                .passwordCompare()
                .passwordEncoder(new BCryptPasswordEncoder(B_CRYPT_STRENGTH))
                .passwordAttribute(PASSWORD_ATTRIBUTE);
    }

        private void example(final AuthenticationManagerBuilder auth) throws Exception {
            auth.ldapAuthentication()
                    .authoritiesMapper(authorities -> {
                        return authorities;
                    })
                    .contextSource()
                    .url("")
                    .managerDn("")
                    .managerPassword("")
                    .root("")
                    .port(1)
                    .and()
                .groupRoleAttribute("")
                .groupSearchBase("")
                .groupSearchFilter("")
                .ldapAuthoritiesPopulator(new DefaultLdapAuthoritiesPopulator(new DefaultSpringSecurityContextSource(""), ""))
                .passwordCompare()
                    .passwordEncoder(new BCryptPasswordEncoder())
                    .passwordAttribute("")
                    .and()
                .rolePrefix("")
                .userDetailsContextMapper(new LdapUserDetailsMapper())
                .userDnPatterns()
                .userSearchBase("")
                .userSearchFilter("")
                .withObjectPostProcessor(null);
    }

}
