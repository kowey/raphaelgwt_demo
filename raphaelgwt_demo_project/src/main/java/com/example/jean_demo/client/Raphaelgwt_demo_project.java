package com.example.jean_demo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.user.client.Window;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Raphaelgwt_demo_project implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

    // Ugh: it's not clear if we want this workaround or not.
    // Right now it's commented out because the current DraggableCircle
    // positions things using RootPanel.getAbsoluteTop() (and left)
    // which breaks if we set this position:relative attribute
    // But I can't figure out how to position things without using
    // getAbsoluteTop
    //
    // Cargo-culted from raphaelgwt demo; not sure how important it is to us
    // workaround for GWT issue 1813
    // http://code.google.com/p/google-web-toolkit/issues/detail?id=1813
    // RootPanel.get().getElement().getStyle().setProperty("position" , "relative");


		MyDrawing d = new MyDrawing(Window.getClientWidth(),
				Window.getClientHeight());

    RootPanel.get().add(d);


    gwtjsPlumbDemo();

  }

  public static native void gwtjsPlumbDemo() /*-{
                $wnd.gwtjsplumbdemo();

        }-*/;
}
