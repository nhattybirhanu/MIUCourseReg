package miu.edu.com.courseregistrationsystem.config.security;

import miu.edu.com.courseregistrationsystem.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Account> account = accountRepository.findByUsername(username);
//        account.orElseThrow(() -> new UsernameNotFoundException("NOT FOUND"));
//        return new CustomUserDetails(account.get());
        return  null;
    }
}
