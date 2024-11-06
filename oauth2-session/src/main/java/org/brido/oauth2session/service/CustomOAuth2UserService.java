package org.brido.oauth2session.service;

import org.brido.oauth2session.dto.CustomOAuth2User;
import org.brido.oauth2session.dto.NaverResponse;
import org.brido.oauth2session.dto.OAuth2Response;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User user = super.loadUser(userRequest);

    String registrationId = userRequest.getClientRegistration().getRegistrationId();

    OAuth2Response response = null;
    if (registrationId.equals("naver")) {
      response = new NaverResponse(user.getAttributes());
    }
    String role = "ROLE_USER";
    return new CustomOAuth2User(response, role);
  }
}
