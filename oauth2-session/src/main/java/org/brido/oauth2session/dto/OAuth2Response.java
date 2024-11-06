package org.brido.oauth2session.dto;

public interface OAuth2Response {
  String getProvider();

  String getProviderId();

  String getName();

  String getEmail();
}
