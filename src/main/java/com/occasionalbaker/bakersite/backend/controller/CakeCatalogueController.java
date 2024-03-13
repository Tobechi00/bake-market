package com.occasionalbaker.bakersite.backend.controller;

import com.occasionalbaker.bakersite.backend.entity.CakeCatalogue;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;
import org.springframework.stereotype.Controller;

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
public class CakeCatalogueController {


    public Image generateImage(CakeCatalogue cakeCatalogue) {
        StreamResource streamResource = new StreamResource("image",()-> new ByteArrayInputStream(cakeCatalogue.getCakeImage()));
        streamResource.setContentType("image/png");

        return new Image(streamResource,"cake-image");
    }

}

