package com.occasionalbaker.bakersite.views.login;

import com.occasionalbaker.bakersite.backend.controller.RegistrationController;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "register",layout = LoginLayout.class)
@AnonymousAllowed
@PageTitle("register")
public class RegistrationView extends VerticalLayout {
    H3 h3;
    TextField firstName,lastName,phoneNumber;
    EmailField email;
    PasswordField password,confirmPassword;

    Button register;

    VerticalLayout outerLayout;

    FormLayout formLayout;


    @Autowired
    RegistrationController registrationController;

    public RegistrationView(){
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSizeFull();

        h3 = new H3("Register");
        firstName = new TextField();
        firstName.setMaxLength(64);
        firstName.setRequiredIndicatorVisible(true);
        firstName.setLabel("firstname");

        lastName = new TextField();
        lastName.setMaxLength(64);
        lastName.setRequiredIndicatorVisible(true);
        lastName.setLabel("lastname");

        phoneNumber = new TextField();
        phoneNumber.setLabel("phone number");
        phoneNumber.setMaxLength(11);
        phoneNumber.setMinLength(11);
        phoneNumber.setRequiredIndicatorVisible(true);
        phoneNumber.setPlaceholder("+234");


        email = new EmailField();
        email.setMaxLength(100);
        email.setRequiredIndicatorVisible(true);
        email.setLabel("email");

        password = new PasswordField();
        password.setLabel("password");
        password.setAllowedCharPattern("[A-Za-z0-9]");
        password.setMinLength(9);
        password.setMaxLength(9);
        password.setRequiredIndicatorVisible(true);
        password.setHelperText("your password length must be greater than 8");

        confirmPassword = new PasswordField();
        confirmPassword.setAllowedCharPattern("[A-Za-z0-9]");
        confirmPassword.setMinLength(9);
        confirmPassword.setMaxLength(9);
        confirmPassword.setLabel("confirm password");

        register = new Button("register");
        register.addClassName("submitButton");



        register.addClickListener(event -> {
            firstName.setHelperText("");
            lastName.setHelperText("");
            phoneNumber.setHelperText("");
            email.setHelperText("");
            password.setHelperText("");

            register();

        });


        outerLayout = new VerticalLayout();
        outerLayout.setAlignItems(Alignment.CENTER);
        outerLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        outerLayout.setMaxWidth("700px");
        outerLayout.addClassName("loginCenter");

        formLayout = new FormLayout();

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0",2));
        formLayout.add(firstName,lastName,phoneNumber,email,password,confirmPassword);


        outerLayout.add(h3,formLayout,register);

        add(outerLayout);

    }

    public void register() {
        //clean up
        if (firstName.getValue().isEmpty() || !firstName.getValue().chars().allMatch(Character::isLetter)) {
            firstName.setHelperText("invalid firstname");
        } else if (lastName.getValue().isEmpty() || !lastName.getValue().chars().allMatch(Character::isLetter)) {
            lastName.setHelperText("invalid lastname");
        } else if (phoneNumber.isInvalid() || !phoneNumber.getValue().chars().allMatch(Character::isDigit)) {
            phoneNumber.setHelperText("invalid phonenumber");
        } else if (email.isEmpty()||email.isInvalid()) {
            email.setHelperText("invalid email");
        } else if (registrationController.userExists(email.getValue())){
            email.setHelperText("this email already exists");
        } else if (password.isEmpty()||password.isInvalid()) {
            password.setHelperText("invalid password, password must be up to 8");
            //sanitise pass
        } else if (!confirmPassword.getValue().equals(password.getValue())) {
            confirmPassword.setHelperText("your passwords do not match");
        }else {
            registrationController.register(firstName.getValue(), lastName.getValue(), phoneNumber.getValue(), email.getValue(), password.getValue());
            Notification.show("your account has been created successfully");
            UI.getCurrent().navigate(SuccessView.class);
        }
        }
    }
