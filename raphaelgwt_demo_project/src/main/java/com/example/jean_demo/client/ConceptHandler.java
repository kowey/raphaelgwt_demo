package com.example.jean_demo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import lombok.Data;

/**
* Created by kowey on 2014-02-03.
*/
public @Data
class ConceptHandler implements MouseOverHandler, MouseOutHandler {

    private final Concept concept;

    @Override
    public void onMouseOver(MouseOverEvent event) {
        concept.onMouseOver(event);
    }

    @Override
    public void onMouseOut(MouseOutEvent event) {
        concept.onMouseOut(event);
    }


}
