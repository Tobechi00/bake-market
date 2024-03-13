package com.occasionalbaker.bakersite.views.homepage;

import com.occasionalbaker.bakersite.views.login.LoginView;
import com.occasionalbaker.bakersite.views.session.SessionAttributes;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;


@Theme("custom_themes")
public class HomeLayout extends AppLayout implements AppShellConfigurator {
    DrawerToggle drawerToggle;
    H1 title;

    VerticalLayout linkLayout;

    SideNav sideNav;
    SideNavItem about,catalogue,order, feedback,account,myOrders;

    RouterLink signinLink;

    Avatar avatar;

    HorizontalLayout avatarLayout;

    HorizontalLayout headerLayout;
    SessionAttributes sessionAttributes;

    public HomeLayout(SessionAttributes sessionAttributes) {
        this.sessionAttributes = sessionAttributes;

        drawerToggle = new DrawerToggle();
        drawerToggle.addClassName("drawerToggle");

        linkLayout = new VerticalLayout();

        sideNav = new SideNav();

        title = new H1("The Occasional Baker");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        title.addClassName("logo");


        signinLink = new RouterLink("Login", LoginView.class);
        signinLink.addClassName("loginLink");

        avatarLayout = new HorizontalLayout();
        avatarLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        avatarLayout.setAlignItems(FlexComponent.Alignment.END);
        avatarLayout.setSizeFull();
        avatarLayout.setPadding(true);

        headerLayout = new HorizontalLayout();
        headerLayout.setSizeFull();
        headerLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        headerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        headerLayout.add(title);

        about = new SideNavItem("About Us");
        about.setPath(AboutView.class);

        order = new SideNavItem("Place an Order");
        order.setPath(PlaceOrderView.class);


        feedback = new SideNavItem("Submit Feedback");
        feedback.setPath(FeedBackView.class);

        account = new SideNavItem("Account");
        account.setPath(AccountView.class);

        myOrders = new SideNavItem("My Orders");
        myOrders.setPath(MyOrdersView.class);

        catalogue = new SideNavItem("Cake Catalogue");
        catalogue.setPath(CakeCatalogueView.class);




        about.addClassName("link");
        catalogue.addClassName("link");
        order.addClassName("link");
        feedback.addClassName("link");
        account.addClassName("link");
        myOrders.addClassName("link");

        if (VaadinSession.getCurrent().getAttribute("USER_ID") == null) {
            avatarLayout.add(signinLink);
            sideNav.addItem(about,catalogue,order);
        } else {

            avatar = new Avatar(sessionAttributes.getSessionFirstName()+" "+sessionAttributes.getSessionLastName());
            avatarLayout.add(avatar);
            sideNav.addItem(about,catalogue, order, feedback,account,myOrders);
        }
        addToDrawer(sideNav);
        addToNavbar(drawerToggle, headerLayout, avatarLayout);
        setPrimarySection(Section.DRAWER);

    }
}
