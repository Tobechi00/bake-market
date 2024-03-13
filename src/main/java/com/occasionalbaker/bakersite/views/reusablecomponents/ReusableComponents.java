package com.occasionalbaker.bakersite.views.reusablecomponents;

import com.occasionalbaker.bakersite.views.login.LoginView;
import com.occasionalbaker.bakersite.views.login.RegistrationView;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ReusableComponents {
    VerticalLayout verticalLayout;

    ConfirmDialog confirmDialog;

    Button login, register;

    Dialog dialog;

    Html html;

    public void showLoginDialogue(VerticalLayout mainLayout) {
        Span span = new Span("Please Login");
        mainLayout.add(span);

        html = new Html("<p>you need to be logged in inorder to access this page,<br> if you don't have an account you can create one by clicking on register</p>");

        dialog = new Dialog();
        dialog.setHeaderTitle("Seems Like You're not logged in");
        dialog.add(html);

        login = new Button("login", event -> {
            UI.getCurrent().navigate(LoginView.class);
            dialog.close();
        });
        login.addClassName("dialogueLogin");

        register = new Button("register", event -> {
            UI.getCurrent().navigate(RegistrationView.class);
            dialog.close();
        });
        register.addClassName("submitButton");

        dialog.getFooter().add(login);
        dialog.getFooter().add(register);
        dialog.setCloseOnOutsideClick(true);

        dialog.open();
    }
    public void showLoginDialogue(HorizontalLayout mainLayout){
        Span span = new Span("Please Login");
        mainLayout.add(span);

        html = new Html("<p>you need to be logged in inorder to access this page,<br> if you don't have an account you can create one by clicking on register</p>");

        dialog = new Dialog();
        dialog.setHeaderTitle("Seems Like You're not logged in");
        dialog.add(html);

        login = new Button("login", event -> {
            UI.getCurrent().navigate(LoginView.class);
            dialog.close();
        });
        login.addClassName("dialogueLogin");

        register = new Button("register", event -> {
            UI.getCurrent().navigate(RegistrationView.class);
            dialog.close();
        });
        register.addClassName("submitButton");

        dialog.getFooter().add(login);
        dialog.getFooter().add(register);
        dialog.setCloseOnOutsideClick(true);

        dialog.open();
    }

    public ConfirmDialog createConfirmDialogue(String header,String mainText, Button confirmButton,Button cancelButton) {
        ConfirmDialog confirmDialog = new ConfirmDialog();

        cancelButton.addClickListener(event -> confirmDialog.close());

        confirmDialog.setHeader(header);
        confirmDialog.setText(mainText);
        confirmDialog.setConfirmButton(confirmButton);
        confirmDialog.setCancelable(true);
        confirmDialog.setCancelButton(cancelButton);
        return confirmDialog;
    }
}
