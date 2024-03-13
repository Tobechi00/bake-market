package com.occasionalbaker.bakersite.views.adminportal;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.router.Route;

public class AdminPortalLayout extends AppLayout {

    H1 header;

    //new navbar components
    public AdminPortalLayout(){
        header = new H1("occasional baker portal");
        header.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");


        addToNavbar(header);
    }
}
