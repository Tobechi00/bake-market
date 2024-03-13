package com.occasionalbaker.bakersite.views.login;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;

public class LoginLayout extends AppLayout {

    H1 title;

    Paragraph p;

    public LoginLayout(){

        p = new Paragraph("The Occasional Baker");
        p.addClassName("loginTitle");

        title = new H1(p);
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        title.addClassName("logo");
        addToNavbar(title);
    }
}
