package com.occasionalbaker.bakersite.views.login;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "register/success", layout = LoginLayout.class)
@AnonymousAllowed
@PageTitle("success")
public class SuccessView extends VerticalLayout {

    H1 header;
    Html html;

    Button button;

    VerticalLayout layout;

    public SuccessView(){
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        layout = new VerticalLayout();
        layout.setAlignItems(Alignment.CENTER);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setMaxWidth("700px");

        header = new H1("Success");

        html = new Html("<p>Congratulations! You're officially part of our sweet community. Your registration is a piece of cake! Now, let's start baking memories together.<br> click the button below to login</p>");

        button = new Button("proceed to login");
        button.addClassName("submitButton");
        button.addClickListener(event -> UI.getCurrent().navigate(LoginView.class));

        layout.add(header,html,button);

        add(layout);

    }
}
