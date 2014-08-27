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

package com.arshu.roommate.server.endpoint.rmendpoint.model;

/**
 * Model definition for RoomCollection.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the rmendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class RoomCollection extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<Room> items;

  static {
    // hack to force ProGuard to consider Room used, since otherwise it would be stripped out
    // see http://code.google.com/p/google-api-java-client/issues/detail?id=528
    com.google.api.client.util.Data.nullOf(Room.class);
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<Room> getItems() {
    return items;
  }

  /**
   * @param items items or {@code null} for none
   */
  public RoomCollection setItems(java.util.List<Room> items) {
    this.items = items;
    return this;
  }

  @Override
  public RoomCollection set(String fieldName, Object value) {
    return (RoomCollection) super.set(fieldName, value);
  }

  @Override
  public RoomCollection clone() {
    return (RoomCollection) super.clone();
  }

}
