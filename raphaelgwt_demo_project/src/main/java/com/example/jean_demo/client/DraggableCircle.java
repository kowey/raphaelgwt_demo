/*
 * Copyright 2010-2011 Hydro4GE, Incorporated. http://www.hydro4ge.com/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jean_demo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.hydro4ge.raphaelgwt.client.*;

import java.util.HashMap;
import java.util.Map;

public class DraggableCircle extends Raphael
    implements HasMouseDownHandlers, HasMouseUpHandlers, HasMouseMoveHandlers, HasMouseOutHandlers
{
  static  final private int PADDING = 5;
  private final int radius;
  private final Map<String, String> attrs;

  private Circle circle;

  public DraggableCircle(int radius) {
    super((radius+PADDING)*2, (radius+PADDING)*2);
    this.radius = radius;
    this.attrs  = new HashMap<String, String>();
  }

  @Override
  public void onLoad() {
    super.onLoad();
    circle = new Circle(this.radius+PADDING, this.radius+PADDING, this.radius);
    circle.attr("fill", "#666");
    for (Map.Entry<String, String> entry : attrs.entrySet()) {
        circle.attr(entry.getKey(), entry.getValue());
    }
  }

  public void attr(String key, String value) {
    attrs.put(key, value);
    if (circle != null) {
      circle.attr(key, value);
    }
  }

  public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
    return this.addDomHandler(handler, MouseDownEvent.getType());
  }

  public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
    return this.addDomHandler(handler, MouseUpEvent.getType());
  }

  public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
    return this.addDomHandler(handler, MouseMoveEvent.getType());
  }

  public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
    return this.addDomHandler(handler, MouseOutEvent.getType());
  }
}

