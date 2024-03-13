package com.occasionalbaker.bakersite.views.login;


import com.occasionalbaker.bakersite.backend.controller.LoginController;
import com.occasionalbaker.bakersite.backend.entity.PrincipalUser;
import com.occasionalbaker.bakersite.views.homepage.AboutView;
import com.occasionalbaker.bakersite.views.session.SessionAttributes;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

@Route(value = "login",layout = LoginLayout.class)
@PageTitle("login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {
//https://stackoverflow.com/questions/53050125/how-to-secure-vaadin-flow-application-with-spring-security
    FormLayout formLayout;

    EmailField emailField;

    VerticalLayout innerVerticalLayout,outerVerticalLayout;

    H2 h2;
    H3 h3;


    Paragraph paragraph;

    Button loginButton;


    RouterLink registerLink;

    PasswordField passwordField;

    @Autowired
    LoginController loginController;

    SessionAttributes sessionAttributes;

    public LoginView(SessionAttributes sessionAttributes){
        this.sessionAttributes = sessionAttributes;
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSizeFull();
        outerVerticalLayout = new VerticalLayout();
        outerVerticalLayout.setAlignItems(Alignment.CENTER);
        outerVerticalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        outerVerticalLayout.setMaxWidth("500px");

        innerVerticalLayout = new VerticalLayout();
        innerVerticalLayout.setAlignItems(Alignment.CENTER);
        innerVerticalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        innerVerticalLayout.addClassName("loginCenter");
        innerVerticalLayout.setPadding(true);

        formLayout = new FormLayout();


        h2 = new H2("Please Login");

        emailField = new EmailField();
        emailField.setLabel("email");
        emailField.setMinWidth("250px");
        emailField.setMaxLength(100);

        passwordField = new PasswordField();
        passwordField.setLabel("password");
        passwordField.setMinWidth("250px");
        passwordField.setMaxLength(18);


        paragraph = new Paragraph();
        paragraph.getStyle().setColor("red");

        loginButton = new Button("login");
        loginButton.addClassName("submitButton");


        loginButton.addClickListener(event -> {
            if (!emailField.isInvalid()||!passwordField.isInvalid()) {
                try {
                    loginController.login(emailField.getValue(), passwordField.getValue());

                    PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                    sessionAttributes.setLoggedInUser(principalUser.getId());

                    VaadinSession.getCurrent().setAttribute("SECURITY",SecurityContextHolder.getContext());


                    VaadinSession.getCurrent().setAttribute("USER_ID",principalUser.getId());

                    UI.getCurrent().navigate(AboutView.class);
                } catch (RuntimeException e) {
                    paragraph.setText(e.getMessage());
                }
            }else {
                loginButton.setEnabled(false);
            }
        });

        registerLink = new RouterLink("don't have an account? register here",RegistrationView.class);
        registerLink.addClassName("registerLink");



        innerVerticalLayout.add(h2,paragraph,emailField,passwordField,loginButton,registerLink);

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0",1)
        );


        formLayout.add(innerVerticalLayout);

        outerVerticalLayout.add(formLayout);

        add(outerVerticalLayout);



    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

    }
}
