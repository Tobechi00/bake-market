package com.occasionalbaker.bakersite.views.homepage;

import com.occasionalbaker.bakersite.views.login.LoginView;
import com.occasionalbaker.bakersite.views.session.SessionAttributes;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;


@Route(value = "account",layout = HomeLayout.class)
@PageTitle("account")
@AnonymousAllowed
public class AccountView extends VerticalLayout {

    VerticalLayout outerLayout;

    VerticalLayout innerLayout;
    Avatar avatar;
    Paragraph fullName,email,phoneNumber;

    Button editButton;

    SessionAttributes sessionAttributes;
    public AccountView(SessionAttributes sessionAttributes){
        this.sessionAttributes = sessionAttributes;
        addClassName("orderView");
        setSizeFull();
        setAlignItems(FlexComponent.Alignment.CENTER);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        if (!(VaadinSession.getCurrent().getAttribute("USER_ID") == null)) {



            avatar = new Avatar(sessionAttributes.getSessionFirstName() + " " + sessionAttributes.getSessionLastName());
            avatar.setWidth("70px");
            avatar.setHeight("70px");

            fullName = new Paragraph();
            fullName.setText(sessionAttributes.getSessionFirstName() + " " + sessionAttributes.getSessionLastName());
            fullName.addClassName("accountItems");

            email = new Paragraph();
            email.setText(sessionAttributes.getSessionEmail());
            email.addClassName("accountItems");


            phoneNumber = new Paragraph();
            phoneNumber.setText(sessionAttributes.getSessionPhoneNumber());
            phoneNumber.addClassName("accountItems");

            editButton = new Button("edit");
            editButton.addClassName("submitButton");

            outerLayout = new VerticalLayout();
            outerLayout.addClassName("white");
            outerLayout.setMaxWidth("500px");
            outerLayout.setAlignItems(FlexComponent.Alignment.CENTER);
            outerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

            innerLayout = new VerticalLayout();
            innerLayout.setAlignItems(Alignment.CENTER);
            innerLayout.setJustifyContentMode(JustifyContentMode.CENTER);

            innerLayout.add(fullName,email,phoneNumber);
            outerLayout.add(avatar,innerLayout,editButton);
            add(outerLayout);
        }else {
            UI.getCurrent().navigate(LoginView.class);
        }
    }

}
