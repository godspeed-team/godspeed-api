package team.godspeed.api.auth;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import team.godspeed.api.Error;
import team.godspeed.api.ErrorMessage;
import team.godspeed.api.auth.db.User;
import team.godspeed.api.root.RootController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

  @Value("${jwt.secret:secret}")
  private String secret;
  @Autowired
  private UserService userService;

  @RequestMapping(value = "/login", method = POST)
  public ResponseEntity<Resource<? extends Object>> login(@RequestBody Credentials credentials)
      throws ServletException {
    if (credentials.getUsername() == null || credentials.getPassword() == null) {
      return returnError(HttpStatus.UNAUTHORIZED, Error.INVALID_CREDENTIALS);
    }

    User user = userService.authenticate(credentials.getUsername(), credentials.getPassword());

    if (user != null) {
      return returnOk(Jwts.builder().setSubject(user.getEmail()).claim("role", "user")
          .setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, secret).compact());
    } else {
      return returnError(HttpStatus.UNAUTHORIZED, Error.INVALID_CREDENTIALS);
    }
  }

  @RequestMapping(value = "register", method = POST)
  public Resource<Void> register(@RequestBody Credentials credentials) {
    return new Resource<Void>(null,
        linkTo(methodOn(AuthenticationController.class).register(null)).withSelfRel());
  }

  private ResponseEntity<Resource<? extends Object>> returnError(HttpStatus status, Error error)
      throws ServletException {
    return new ResponseEntity<Resource<? extends Object>>(
        new Resource<ErrorMessage>(new ErrorMessage(error), defaultLinks()), status);
  }

  private ResponseEntity<Resource<? extends Object>> returnOk(String jwtToken)
      throws ServletException {
    return new ResponseEntity<Resource<? extends Object>>(
        new Resource<String>(jwtToken, defaultLinks()), HttpStatus.OK);
  }

  private Link[] defaultLinks() throws ServletException {
    return new Link[] { //
        linkTo(methodOn(AuthenticationController.class).login(null)).withSelfRel(), //
        linkTo(methodOn(RootController.class).getRoot()).withRel("root")};
  }
}
