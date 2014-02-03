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

import com.google.gwt.user.client.ui.*;
import com.hydro4ge.raphaelgwt.client.Raphael;

import java.util.ArrayList;
import java.util.List;

/**
 * simple drawing demonstration
 */
public class MyDrawing extends Raphael {
  /* center point of the drawing */
  private int cx;
  private int cy;

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

    final int box1_x = (int)Math.round(cx * 0.5);
    final int box1_y = (int)Math.round(cy * 0.5);

    final int box2_x = (int)Math.round(cx * 0.8);
    final int box2_y = (int)Math.round(cy * 0.5);

    final int circ0_x = (int)Math.round(cx * 0.5);
    final int circ0_y = (int)Math.round(cy * 0.5);

    final int circ1_x = 30;
    final int circ1_y = 60;

    final DraggableRect box1 = new DraggableRect(this, 50,  100, 20);
    final DraggableRect box2 = new DraggableRect(this, 150, 100, 20);

    box1.attr("fill", "red");
    box2.attr("fill", "#d1b48c");

    // add a new circle to the boundary panel and make it draggable
    final DraggableCircle circ0 = new DraggableCircle(this, 20);
    final DraggableCircle circ1 = new DraggableCircle(this, 40);

    // FIXME: I'm not thrilled about accessing the RootPanel directly here but
    // I can't seem to find a way around it
    RootPanel rp = RootPanel.get();

    AbsolutePanel subpanel = new AbsolutePanel();
    subpanel.setWidth("400px");
    subpanel.setHeight("400px");
    rp.add(subpanel, 5, 5);

    circ0.addToPanel(rp, circ0_x, circ0_y);
    circ1.addToPanel(subpanel, circ1_x, circ1_y);

    circ0.getElement().setId("kowey-box1");
    circ1.getElement().setId("kowey-box2");


    List<DraggableShape> shapes = new ArrayList<DraggableShape>();
    shapes.add(circ0);
    shapes.add(circ1);
    for (DraggableShape s : shapes) {
      s.getElement().setClassName("koweybox");
    }
    subpanel.getElement().setClassName("koweydrag");
    subpanel.getElement().setId("kowey-container");
    gwtjsPlumbDemo();
  }

  public static native void gwtjsPlumbDemo() /*-{
                $wnd.gwtjsplumbdemo();

        }-*/;

}