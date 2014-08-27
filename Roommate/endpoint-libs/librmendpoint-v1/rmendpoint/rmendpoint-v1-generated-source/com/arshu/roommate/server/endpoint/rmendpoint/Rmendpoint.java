/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-07-22 21:53:01 UTC)
 * on 2014-08-26 at 23:35:33 UTC 
 * Modify at your own risk.
 */

package com.arshu.roommate.server.endpoint.rmendpoint;

/**
 * Service definition for Rmendpoint (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link RmendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Rmendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.18.0-rc of the rmendpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://myapp.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "rmendpoint/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Rmendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Rmendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "doLogin".
   *
   * This request holds the parameters needed by the rmendpoint server.  After setting any optional
   * parameters, call the {@link DoLogin#execute()} method to invoke the remote operation.
   *
   * @param content the {@link com.arshu.roommate.server.endpoint.rmendpoint.model.Mate}
   * @return the request
   */
  public DoLogin doLogin(com.arshu.roommate.server.endpoint.rmendpoint.model.Mate content) throws java.io.IOException {
    DoLogin result = new DoLogin(content);
    initialize(result);
    return result;
  }

  public class DoLogin extends RmendpointRequest<com.arshu.roommate.server.endpoint.rmendpoint.model.Mate> {

    private static final String REST_PATH = "doLogin";

    /**
     * Create a request for the method "doLogin".
     *
     * This request holds the parameters needed by the the rmendpoint server.  After setting any
     * optional parameters, call the {@link DoLogin#execute()} method to invoke the remote operation.
     * <p> {@link
     * DoLogin#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must
     * be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link com.arshu.roommate.server.endpoint.rmendpoint.model.Mate}
     * @since 1.13
     */
    protected DoLogin(com.arshu.roommate.server.endpoint.rmendpoint.model.Mate content) {
      super(Rmendpoint.this, "POST", REST_PATH, content, com.arshu.roommate.server.endpoint.rmendpoint.model.Mate.class);
    }

    @Override
    public DoLogin setAlt(java.lang.String alt) {
      return (DoLogin) super.setAlt(alt);
    }

    @Override
    public DoLogin setFields(java.lang.String fields) {
      return (DoLogin) super.setFields(fields);
    }

    @Override
    public DoLogin setKey(java.lang.String key) {
      return (DoLogin) super.setKey(key);
    }

    @Override
    public DoLogin setOauthToken(java.lang.String oauthToken) {
      return (DoLogin) super.setOauthToken(oauthToken);
    }

    @Override
    public DoLogin setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (DoLogin) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public DoLogin setQuotaUser(java.lang.String quotaUser) {
      return (DoLogin) super.setQuotaUser(quotaUser);
    }

    @Override
    public DoLogin setUserIp(java.lang.String userIp) {
      return (DoLogin) super.setUserIp(userIp);
    }

    @Override
    public DoLogin set(String parameterName, Object value) {
      return (DoLogin) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "getAllRooms".
   *
   * This request holds the parameters needed by the rmendpoint server.  After setting any optional
   * parameters, call the {@link GetAllRooms#execute()} method to invoke the remote operation.
   *
   * @return the request
   */
  public GetAllRooms getAllRooms() throws java.io.IOException {
    GetAllRooms result = new GetAllRooms();
    initialize(result);
    return result;
  }

  public class GetAllRooms extends RmendpointRequest<com.arshu.roommate.server.endpoint.rmendpoint.model.RoomCollection> {

    private static final String REST_PATH = "roomcollection";

    /**
     * Create a request for the method "getAllRooms".
     *
     * This request holds the parameters needed by the the rmendpoint server.  After setting any
     * optional parameters, call the {@link GetAllRooms#execute()} method to invoke the remote
     * operation. <p> {@link
     * GetAllRooms#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected GetAllRooms() {
      super(Rmendpoint.this, "GET", REST_PATH, null, com.arshu.roommate.server.endpoint.rmendpoint.model.RoomCollection.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetAllRooms setAlt(java.lang.String alt) {
      return (GetAllRooms) super.setAlt(alt);
    }

    @Override
    public GetAllRooms setFields(java.lang.String fields) {
      return (GetAllRooms) super.setFields(fields);
    }

    @Override
    public GetAllRooms setKey(java.lang.String key) {
      return (GetAllRooms) super.setKey(key);
    }

    @Override
    public GetAllRooms setOauthToken(java.lang.String oauthToken) {
      return (GetAllRooms) super.setOauthToken(oauthToken);
    }

    @Override
    public GetAllRooms setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetAllRooms) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetAllRooms setQuotaUser(java.lang.String quotaUser) {
      return (GetAllRooms) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetAllRooms setUserIp(java.lang.String userIp) {
      return (GetAllRooms) super.setUserIp(userIp);
    }

    @Override
    public GetAllRooms set(String parameterName, Object value) {
      return (GetAllRooms) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "matesJoinRoom".
   *
   * This request holds the parameters needed by the rmendpoint server.  After setting any optional
   * parameters, call the {@link MatesJoinRoom#execute()} method to invoke the remote operation.
   *
   * @param content the {@link com.arshu.roommate.server.endpoint.rmendpoint.model.JoinRoomRequest}
   * @return the request
   */
  public MatesJoinRoom matesJoinRoom(com.arshu.roommate.server.endpoint.rmendpoint.model.JoinRoomRequest content) throws java.io.IOException {
    MatesJoinRoom result = new MatesJoinRoom(content);
    initialize(result);
    return result;
  }

  public class MatesJoinRoom extends RmendpointRequest<com.arshu.roommate.server.endpoint.rmendpoint.model.Room> {

    private static final String REST_PATH = "matesJoinRoom";

    /**
     * Create a request for the method "matesJoinRoom".
     *
     * This request holds the parameters needed by the the rmendpoint server.  After setting any
     * optional parameters, call the {@link MatesJoinRoom#execute()} method to invoke the remote
     * operation. <p> {@link MatesJoinRoom#initialize(com.google.api.client.googleapis.services.Abstra
     * ctGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param content the {@link com.arshu.roommate.server.endpoint.rmendpoint.model.JoinRoomRequest}
     * @since 1.13
     */
    protected MatesJoinRoom(com.arshu.roommate.server.endpoint.rmendpoint.model.JoinRoomRequest content) {
      super(Rmendpoint.this, "POST", REST_PATH, content, com.arshu.roommate.server.endpoint.rmendpoint.model.Room.class);
    }

    @Override
    public MatesJoinRoom setAlt(java.lang.String alt) {
      return (MatesJoinRoom) super.setAlt(alt);
    }

    @Override
    public MatesJoinRoom setFields(java.lang.String fields) {
      return (MatesJoinRoom) super.setFields(fields);
    }

    @Override
    public MatesJoinRoom setKey(java.lang.String key) {
      return (MatesJoinRoom) super.setKey(key);
    }

    @Override
    public MatesJoinRoom setOauthToken(java.lang.String oauthToken) {
      return (MatesJoinRoom) super.setOauthToken(oauthToken);
    }

    @Override
    public MatesJoinRoom setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (MatesJoinRoom) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public MatesJoinRoom setQuotaUser(java.lang.String quotaUser) {
      return (MatesJoinRoom) super.setQuotaUser(quotaUser);
    }

    @Override
    public MatesJoinRoom setUserIp(java.lang.String userIp) {
      return (MatesJoinRoom) super.setUserIp(userIp);
    }

    @Override
    public MatesJoinRoom set(String parameterName, Object value) {
      return (MatesJoinRoom) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "registerMate".
   *
   * This request holds the parameters needed by the rmendpoint server.  After setting any optional
   * parameters, call the {@link RegisterMate#execute()} method to invoke the remote operation.
   *
   * @param content the {@link com.arshu.roommate.server.endpoint.rmendpoint.model.Mate}
   * @return the request
   */
  public RegisterMate registerMate(com.arshu.roommate.server.endpoint.rmendpoint.model.Mate content) throws java.io.IOException {
    RegisterMate result = new RegisterMate(content);
    initialize(result);
    return result;
  }

  public class RegisterMate extends RmendpointRequest<com.arshu.roommate.server.endpoint.rmendpoint.model.Mate> {

    private static final String REST_PATH = "registerMate";

    /**
     * Create a request for the method "registerMate".
     *
     * This request holds the parameters needed by the the rmendpoint server.  After setting any
     * optional parameters, call the {@link RegisterMate#execute()} method to invoke the remote
     * operation. <p> {@link
     * RegisterMate#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link com.arshu.roommate.server.endpoint.rmendpoint.model.Mate}
     * @since 1.13
     */
    protected RegisterMate(com.arshu.roommate.server.endpoint.rmendpoint.model.Mate content) {
      super(Rmendpoint.this, "POST", REST_PATH, content, com.arshu.roommate.server.endpoint.rmendpoint.model.Mate.class);
    }

    @Override
    public RegisterMate setAlt(java.lang.String alt) {
      return (RegisterMate) super.setAlt(alt);
    }

    @Override
    public RegisterMate setFields(java.lang.String fields) {
      return (RegisterMate) super.setFields(fields);
    }

    @Override
    public RegisterMate setKey(java.lang.String key) {
      return (RegisterMate) super.setKey(key);
    }

    @Override
    public RegisterMate setOauthToken(java.lang.String oauthToken) {
      return (RegisterMate) super.setOauthToken(oauthToken);
    }

    @Override
    public RegisterMate setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RegisterMate) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RegisterMate setQuotaUser(java.lang.String quotaUser) {
      return (RegisterMate) super.setQuotaUser(quotaUser);
    }

    @Override
    public RegisterMate setUserIp(java.lang.String userIp) {
      return (RegisterMate) super.setUserIp(userIp);
    }

    @Override
    public RegisterMate set(String parameterName, Object value) {
      return (RegisterMate) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Rmendpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Rmendpoint}. */
    @Override
    public Rmendpoint build() {
      return new Rmendpoint(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link RmendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setRmendpointRequestInitializer(
        RmendpointRequestInitializer rmendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(rmendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
