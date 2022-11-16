package org.hcmus.ln02.security.filter;

import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class APIKeyAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

  private final String principalRequestHeader;

  private final String timeRequestHeader;

  public APIKeyAuthFilter(String principalRequestHeader, String timeRequestHeader) {
    this.principalRequestHeader = principalRequestHeader;
    this.timeRequestHeader = timeRequestHeader;
  }

  @Override
  protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
    return request.getHeader(principalRequestHeader);
  }

  @Override
  protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
    return new ArrayList<>(Arrays.asList(request.getServletPath(), request.getHeader(timeRequestHeader)));
  }

}