package tn.esprit.fundme.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role  implements GrantedAuthority{
    USER,
    ADMIN,
    AGENT;
    
    @Override
	public String getAuthority() {

		return "ROLE_" + name();

		}
}
