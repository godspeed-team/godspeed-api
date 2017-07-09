package team.godspeed.api.root;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import team.godspeed.api.auth.AuthenticationController;

@RestController
public class RootController {

  @RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      method = RequestMethod.GET)
  public HttpEntity<ResourceSupport> getRoot() throws ServletException {
    ResourceSupport result = new ResourceSupport();
    result.add(defaultLinks());
    result.add(linkTo(methodOn(RootController.class).getRoot()).withSelfRel());
    return new ResponseEntity<ResourceSupport>(result, HttpStatus.OK);
  }

  private List<Link> defaultLinks() throws ServletException {
    return Arrays.asList(linkTo(methodOn(RootController.class).getRoot()).withRel("root"), //
        linkTo(methodOn(AuthenticationController.class).login(null)).withRel("login"), //
        linkTo(methodOn(AuthenticationController.class).register(null)).withRel("register"));
  }
}
