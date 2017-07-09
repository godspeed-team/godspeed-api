package team.godspeed.api.root;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

  @RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      method = RequestMethod.GET)
  public HttpEntity<ResourceSupport> getRoot() {
    ResourceSupport result = new ResourceSupport();
    result.add(linkTo(methodOn(RootController.class).getRoot()).withSelfRel());
    return new ResponseEntity<ResourceSupport>(result, HttpStatus.OK);
  }
}
