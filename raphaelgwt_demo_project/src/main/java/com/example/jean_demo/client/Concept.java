package com.example.jean_demo.client;

import com.google.common.base.Optional;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.*;

import lombok.Data;
import lombok.NonNull;

/**
 * Created by kowey on 2014-02-03.
 */
public @Data
class Concept extends AbsolutePanel implements Cloneable, MouseOverHandler, MouseOutHandler {

    @Data class RenameHandler implements KeyUpHandler {
        @NonNull private final Concept concept;
        @NonNull private final TextBox textbox;

        @Override
        public void onKeyUp(KeyUpEvent event) {
            final String text = textbox.getText().trim();
            final Optional<String> label = (text.isEmpty())
                    ? Optional.<String>absent()
                    : Optional.of(text);
            concept.justSetLabel(label);
        }
    }

    @Data class DeleteHandler implements ClickHandler {
        @NonNull private final Concept concept;

        @Override
        public void onClick(ClickEvent event) {
            concept.removeFromParent();
        }
    }

    @NonNull final String id;
    @NonNull Optional<String> label = Optional.absent();

    final private TextBox wLabel = new TextBox();

    public String getCurveId() {
        return this.id + "_curve";
    }

    @Override
    public void onLoad() {
        this.getElement().setId(this.id);
        this.setWidth("150px");
        this.setHeight("70px");
        super.onLoad();

        final DraggableCircle wCircle = new DraggableCircle(30);
        wCircle.getElement().setId(getCurveId());

        this.add(this.wLabel, 60, 5);
        this.wLabel.addKeyUpHandler(new RenameHandler(this, wLabel));
        this.wLabel.setReadOnly(true);

        final VerticalPanel wButtons = new VerticalPanel();
        wButtons.getElement().setClassName("concept-button");

        final Button wDelete = new Button("X");
        wDelete.addClickHandler(new DeleteHandler(this));
        wButtons.add(wDelete);
        this.add(wButtons, 120, 40);
        this.add(wCircle, 1, 1);
    }


    public Concept copyTemplate(@NonNull final AbsolutePanel container,
                                @NonNull final String idPrefix,
                                final int counter) {

        Concept copy  = new Concept(idPrefix + counter);
        copy.setLabel(this.getLabel());
        container.add(copy, this.getAbsoluteLeft(), this.getAbsoluteTop());
        copy.getElement().getStyle().setVisibility(Style.Visibility.VISIBLE);
        copy.getElement().setClassName("template");
        TemplateHandler.addHandler(container, this, copy, idPrefix, counter);
        makeDraggable("#" + copy.getId());
        return copy;
    }

    public void mouseOverHighlight() {
        this.getElement().setClassName("concept-over");
    }

    @Override
    public void onMouseOver(MouseOverEvent event) {
        mouseOverHighlight();
        wLabel.setReadOnly(false);
    }

    @Override
    public void onMouseOut(MouseOutEvent event) {
        this.getElement().setClassName("concept");
        wLabel.setReadOnly(true);
    }

    private void justSetLabel(@NonNull Optional<String> label) {
        this.label = label;
    }

    public void setLabel(@NonNull Optional<String> label) {
        justSetLabel(label);
        wLabel.setText(this.label.or(""));
    }

    public void switchToConceptMode() {
        addDomHandler(this, MouseOverEvent.getType());
        addDomHandler(this, MouseOutEvent.getType());
        mouseOverHighlight();
        setLabel(Optional.<String>absent());
    }

    /**
     * Note: you should only ever call this once
     */
    public void startTemplateMode(final String label) {
        this.setLabel(Optional.of("CONCEPT"));
        this.getElement().getStyle().setVisibility(Style.Visibility.HIDDEN);
        this.getElement().setClassName("template");
        this.wLabel.setReadOnly(true);
    }

    private native void makeDraggable(String draggableId) /*-{
        $wnd.make_draggable(draggableId);
        }-*/;
}
