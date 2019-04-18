package fr.afcepf.al33.data.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class AuthRequest {
	private String username;
	private String password;
	
}
