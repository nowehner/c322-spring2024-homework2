package com.example.c322spring2024homework2.security;



import com.example.c322spring2024homework2.model.Customer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.example.c322spring2024homework2.repository.CustomerRepository;

@Component
public class UserDetailsSecurityService implements UserDetailsService {
    CustomerRepository customerRepository;


    public UserDetailsSecurityService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        try{
            Customer customer = customerRepository.findByUsername(username);
            if(customer==null){
                throw new UsernameNotFoundException("");
            }
            return User
                    .withUsername(username)
                    .password(customer.password())
                    .build();

        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
