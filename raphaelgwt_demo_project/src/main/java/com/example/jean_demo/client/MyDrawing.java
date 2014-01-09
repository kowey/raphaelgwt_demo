package com.example.jean_demo.client;

/*
 * Copyright 2010 Hydro4GE, Incorporated. http://www.hydro4ge.com/
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

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasMouseWheelHandlers;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.user.client.ui.RootPanel;
import com.hydro4ge.raphaelgwt.client.Raphael;
import com.hydro4ge.raphaelgwt.client.PathBuilder;

import com.hydro4ge.dnd.client.DraggableCircle;

/**
 * simple drawing demonstration
 */
public class MyDrawing extends Raphael {

  /* center point of the drawing */
  private int cx;
  private int cy;

  /**
   * a clickable, mousewheel-able circle
   */
  public class Circle extends Raphael.Circle
      implements HasClickHandlers, HasMouseWheelHandlers
  {
    public Circle(double x, double y, double node_size) {
      super(x, y, node_size);
    }

    public HandlerRegistration addClickHandler(ClickHandler handler) {
      return this.addDomHandler(handler, ClickEvent.getType());
    }

    public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
      return this.addDomHandler(handler, MouseWheelEvent.getType());
    }
  }

  /**
   * MyDrawing constructor
   */
  public MyDrawing(final int width, final int height) {
    super(width, height);
    this.cx = width/2;
    this.cy = height/2;
  }

  /**
   * Raphael Text and the Firefox 3.6 SVG implementation do not
   * work together when the text is appended to the drawing
   * before the drawing is appended to the document.  Therefore,
   * we defer the layout to onLoad() here instead of doing it in
   * the constructor.
   */
  @Override
  public void onLoad() {
    super.onLoad();

    // ensure the document BODY has dimensions in standards mode
    RootPanel.get().setPixelSize(600, 600);

    // workaround for GWT issue 1813
    // http://code.google.com/p/google-web-toolkit/issues/detail?id=1813
    RootPanel.get().getElement().getStyle().setProperty("position" , "relative");

    final double box1_x = cx * 0.5;
    final double box1_y = cy * 0.5;

    final double box2_x = cx * 0.8;
    final double box2_y = cy * 0.5;

    final double circ0_x = cx * 0.5;
    final double circ0_y = cy * 0.5;

    final Raphael.Rect box1 = new Raphael.Rect(box1_x, box1_y, 50,  100, 20);
    final Raphael.Rect box2 = new Raphael.Rect(box2_x, box2_y, 150, 100, 20);

    final Circle circ0 = new Circle(circ0_x, circ0_y, 20);
    circ0.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        circ0.attr("fill", "green");
      }
    });

    PathBuilder pb = new PathBuilder();
    pb.M(box1_x,  box1_y);
    pb.L(circ0_x, circ0_y);
            // box1_x, box1_y, circ0_x, circ0_y);

    box1.attr("fill", "red");
    box2.attr("fill", "#d1b48c");

    // create a DragController to manage drag-n-drop actions
    // note: This creates an implicit DropController for the boundary panel
    PickupDragController dragController = new PickupDragController(RootPanel.get(), true);

    // add a new circle to the boundary panel and make it draggable
    DraggableCircle d = new DraggableCircle(40);
    RootPanel.get().add(d, 60, 30);
    dragController.makeDraggable(d);
    //dragController.makeDraggable(box1);

  }

}