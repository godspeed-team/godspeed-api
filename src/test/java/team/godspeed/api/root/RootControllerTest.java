package team.godspeed.api.root;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RunWith(MockitoJUnitRunner.class)
public class RootControllerTest {

  @InjectMocks
  private RootController sut;

  @Before
  public void createRequestContextHolder() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setCharacterEncoding("UTF-8");
    MockHttpServletRequest servletRequest = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(servletRequest));
  }

  @Test
  public void verifyBla() throws ServletException {
    HttpEntity<ResourceSupport> result = sut.getRoot();

    assertThat(result.getBody().hasLinks(), is(true));
    assertThat(result.getBody().getLink("self").getHref(), is("http://localhost/"));
    assertThat(result.getBody().getLink("root").getHref(), is("http://localhost/"));
    assertThat(result.getBody().getLink("login").getHref(), is("http://localhost/auth/login"));
    assertThat(result.getBody().getLink("register").getHref(),
        is("http://localhost/auth/register"));
  }
}
