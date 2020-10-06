package evaluacion.evaluacion.security;

import org.springframework.stereotype.Component;

import evaluacion.evaluacion.constants.Constants;
import evaluacion.evaluacion.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {
	
	public JwtUser validate(String token) {
		JwtUser jwtUser = null;
		
		try {
			Claims body = Jwts.parser()
					.setSigningKey(Constants.YOUR_SECRET)
					.parseClaimsJws(token)
					.getBody();
			
			jwtUser = new JwtUser();
			jwtUser.setUserName(body.getSubject());
			jwtUser.setId((String)body.get(Constants.USER_ID));		
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
		return jwtUser;
	}

}
