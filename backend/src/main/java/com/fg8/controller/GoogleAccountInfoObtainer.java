package com.fg8.controller;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

public class GoogleAccountInfoObtainer {
	private String clientId = null;
	private String clientSecret = null;
	private String redirectUri = null;
	private OAuthClientRequest request = null;

	private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json";

	private static final String CLIENT_ID = "961588210576-gnv7bhopemj22lu44alon86livj1d03i.apps.googleusercontent.com";
	private static final String CLIENT_SECRET = "hWqIB5Zg4jlogoM31dRcMM7d";
	private static final String REDIRECT_URI = "http://islab.csie.ntut.edu.tw:7070/comic/";

	/**
	 * 1. GoogleAccountInfoObtainer obtianer = new GoogleAccountInfoObtainer();
	 * 2. System.out.println(obtianer.getUri());
	 * 3. System.out.println(obtianer.getInfo(code));
	 * or
	 * 1. GoogleAccountInfoObtainer obtianer = new GoogleAccountInfoObtainer(clientId, clientSecret, redirectUri);
	 * 2. System.out.println(obtianer.getUri());
	 * 3. System.out.println(obtianer.getInfo(code));
	 * 
	 * @param clientId
	 * @param clientSecret
	 * @param redirectUri
	 */
	public GoogleAccountInfoObtainer(String clientId, String clientSecret,
			String redirectUri) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.redirectUri = redirectUri;
	}

	public GoogleAccountInfoObtainer() {
		clientId = CLIENT_ID;
		clientSecret = CLIENT_SECRET;
		redirectUri = REDIRECT_URI;
	}

	public String getUri() throws OAuthSystemException {
		request = OAuthClientRequest
				.authorizationProvider(OAuthProviderType.GOOGLE)
				.setClientId(clientId).setRedirectURI(redirectUri)
				.setResponseType("code").setScope("email profile")
				.buildQueryMessage();
		return request.getLocationUri();
	}

	public String getInfo(String code) throws OAuthSystemException,
			OAuthProblemException {
		request = OAuthClientRequest.tokenProvider(OAuthProviderType.GOOGLE)
				.setGrantType(GrantType.AUTHORIZATION_CODE)
				.setClientId(clientId).setClientSecret(clientSecret)
				.setRedirectURI(redirectUri).setCode(code).buildBodyMessage();

		OAuthClient client = new OAuthClient(new URLConnectionClient());
		OAuthJSONAccessTokenResponse response = client.accessToken(request);

		System.out.println("\nAccess Token: " + response.getAccessToken()
				+ "\nExpires in: " + response.getExpiresIn());

		// Use the access token to retrieve the data.
		OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
				PROTECTED_RESOURCE_URL).setAccessToken(
				response.getAccessToken()).buildQueryMessage();

		OAuthResourceResponse resourceResponse = client.resource(
				bearerClientRequest, OAuth.HttpMethod.GET,
				OAuthResourceResponse.class);

		if (resourceResponse.getResponseCode() == 200) {
			return resourceResponse.getBody();
		} else {
			return null;
		}
	}
}