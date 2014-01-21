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
import com.google.gwt.event.dom.client.*;

import com.google.gwt.user.client.ui.RootPanel;
import com.hydro4ge.raphaelgwt.client.Raphael;
import com.hydro4ge.raphaelgwt.client.PathBuilder;

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
    //final Raphael.Rect rectB1 = new Raphael.Rect(box1_x, box1_y, 50+5, 100+5, 0);
    //final Raphael.Rect rectB2 = new Raphael.Rect(box2_x, box2_y, 150+5,  100+5, 0);

    PathBuilder pb = new PathBuilder();
    pb.M(box1_x + 50, box2_y + 50)
            .L(box2_x, box2_y + 50);

    final Path p = new Path(pb);

    box1.attr("fill", "red");
    box2.attr("fill", "#d1b48c");

    // create a DragController to manage drag-n-drop actions
    // note: This creates an implicit DropController for the boundary panel
    // final PickupDragController dragController = new PickupDragController(RootPanel.get(), true);

    // add a new circle to the boundary panel and make it draggable
    final DraggableCircle circ0 = new DraggableCircle(this, 20);
    final DraggableCircle circ1 = new DraggableCircle(this, 40);
    final Circle boundary0 = new Circle(circ0_x, circ0_y, 25);
    final Circle boundary1 = new Circle(circ1_x, circ1_y, 45);

    /*
    dragController.makeDraggable(circ0);
    dragController.makeDraggable(circ1);
    dragController.makeDraggable(box1);
    dragController.makeDraggable(box2);
*/

    circ0.addMouseDownHandler(new MouseDownHandler() {
      @Override
      public void onMouseDown(MouseDownEvent event) {
        circ0.attr("fill", "green");
      }
    });

    // FIXME: I'm not thrilled about accessing the RootPanel directly here but
    // I can't seem to find a way around it
    RootPanel rp = RootPanel.get();
    circ0.addToPanel(rp, circ0_x, circ0_y);
    circ1.addToPanel(rp, circ1_x, circ1_y);
    box1.addToPanel(rp, box1_x, box1_y);
    box2.addToPanel(rp, box2_x, box2_y);

    box1.getElement().setId("kowey-box1");
    box2.getElement().setId("kowey-box2");
    List<DraggableShape> shapes = new ArrayList<DraggableShape>();
    shapes.add(box1);
    shapes.add(box2);
    shapes.add(circ0);
    shapes.add(circ1);
    for (DraggableShape s : shapes) {
      s.getElement().setClassName("koweybox");
    }
    gwtjsPlumbDemo();
  }

  public static native void gwtjsPlumbDemo() /*-{
                $wnd.gwtjsplumbdemo();

        }-*/;

}