package evaluacion.evaluacion.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import evaluacion.evaluacion.constants.Constants;
import evaluacion.evaluacion.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {

	public String generate(JwtUser jwtUser) {
		Claims claims = Jwts.claims()
			.setSubject(jwtUser.getUserName());
		claims.put(Constants.USER_ID, jwtUser.getId());
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS256, Constants.YOUR_SECRET)
				.compact();
	}

}
