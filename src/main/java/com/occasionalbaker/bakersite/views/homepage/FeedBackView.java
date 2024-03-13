package com.occasionalbaker.bakersite.views.homepage;

import com.occasionalbaker.bakersite.backend.controller.FeedBackController;
import com.occasionalbaker.bakersite.views.login.LoginView;
import com.occasionalbaker.bakersite.views.reusablecomponents.ReusableComponents;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "feedback",layout = HomeLayout.class)
@PageTitle("feedback")
@AnonymousAllowed
public class FeedBackView extends VerticalLayout implements BeforeEnterObserver {
    VerticalLayout verticalLayout;

    H2 header;

    TextField firstName,lastName;

    EmailField email;

    FeedBackController feedBackController;

    ReusableComponents reusableComponents;
    Button submit,cancel,send;
    TextArea message;

    FormLayout formLayout;

    String maxWidth;
    @Autowired
    public FeedBackView(FeedBackController feedBackController){

        this.feedBackController = feedBackController;

        addClassName("feedBackView");
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSizeFull();
        maxWidth = "500px";

        reusableComponents = new ReusableComponents();


        verticalLayout = new VerticalLayout();
        verticalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        verticalLayout.setAlignItems(Alignment.CENTER);
        verticalLayout.setMaxWidth(maxWidth);
        verticalLayout.setPadding(true);
        verticalLayout.addClassName("feedBackCenter");

        header = new H2("Customer Feedback");
//        header.addClassName("feedBackHeader");

        firstName = new TextField();
        firstName.setLabel("firstname");

        lastName = new TextField();
        lastName.setLabel("lastname");

        email = new EmailField();
        email.setLabel("email");

        message = new TextArea();
        message.setLabel("message");
        message.setPlaceholder("your message goes here");
        message.setMinHeight("200px");

        submit = new Button("submit");
        submit.addClassName("submitButton");

        cancel = new Button("cancel");
        cancel.addClassName("dialogueLogin");

        send = new Button("send");
        send.addClassName("submitButton");

        send.addClickListener(event -> feedBackController.sendFeedback(firstName.getValue(),lastName.getValue(),email.getValue(),"tobechiokaro2013@gmail.com",message.getValue()));



        submit.addClickListener(event -> {
            boolean clean = checkInputs(firstName,lastName,email,message);

            if (clean){
                reusableComponents.createConfirmDialogue("Send?","are you sure you want to send",send,cancel).open();
            }else {
                Notification notification = new Notification();
                notification.setDuration(5000);
                notification.setText("ensure all fields are filled corectly");
                notification.setPosition(Notification.Position.TOP_CENTER);
                notification.open();
            }
                }
        );

        formLayout = new FormLayout();
        formLayout.add(firstName,lastName,email,message);

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0",1)
        );



        verticalLayout.add(header,formLayout,submit);

        add(verticalLayout);

    }

    public boolean checkInputs(TextField firstName,TextField lastName,EmailField email,TextArea message){
        if (firstName.isEmpty() || lastName.isEmpty() || email.isInvalid() || message.isEmpty()){
            return false;
        }else {
            return true;
        }




    }
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (VaadinSession.getCurrent().getAttribute("USER_ID") == null){
            beforeEnterEvent.rerouteTo(LoginView.class);
        }
    }
}
