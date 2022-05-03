package tn.esprit.fundme.services;

import java.util.ArrayList;
import java.util.Objects;

import javax.transaction.Transactional;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import tn.esprit.fundme.entities.UserType;
import tn.esprit.fundme.repositories.UserTypeRepository;

@Service
public class UserTypeServiceImpl implements UserDetailsService{
	
  private final UserTypeRepository userRepository;

	    @Autowired
	    public UserTypeServiceImpl(UserTypeRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	  
	    @Override
		@Transactional
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    	 Objects.requireNonNull(username);
		        UserType user = userRepository.findUserWithName(username);

		        
			List<GrantedAuthority> authorities = getUserAuthority(user);
			return new User(user.getEmail(), user.getPassword(), user.getActive(), true, true, true, authorities);
					
		}
	   
	    private List<GrantedAuthority> getUserAuthority(UserType log){
	        List<GrantedAuthority> authorities = new ArrayList<>();
	        authorities.add(new SimpleGrantedAuthority(log.getRole().getAuthority()));
	        return authorities;
	    }
}
