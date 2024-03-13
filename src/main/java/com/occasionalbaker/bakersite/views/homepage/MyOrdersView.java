package com.occasionalbaker.bakersite.views.homepage;

import com.occasionalbaker.bakersite.backend.entity.Order;
import com.occasionalbaker.bakersite.backend.service.MyOrderService;
import com.occasionalbaker.bakersite.views.reusablecomponents.ReusableComponents;
import com.occasionalbaker.bakersite.views.session.SessionAttributes;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "my_orders",layout = HomeLayout.class)
@AnonymousAllowed
@PageTitle("my orders")
public class MyOrdersView extends VerticalLayout{


    MyOrderService myOrderService;

    VerticalLayout verticalLayout;

    ReusableComponents reusableComponents;


    H3 header;

    SessionAttributes sessionAttributes;

    @Autowired
    public MyOrdersView(MyOrderService myOrderService, SessionAttributes sessionAttributes){

        this.sessionAttributes = sessionAttributes;
        this.myOrderService = myOrderService;

        if (!(VaadinSession.getCurrent().getAttribute("USER_ID") == null)) {
            setJustifyContentMode(JustifyContentMode.CENTER);
            setAlignItems(Alignment.CENTER);
            setPadding(true);


            verticalLayout = new VerticalLayout();

            verticalLayout.setAlignItems(Alignment.CENTER);
            verticalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
            verticalLayout.setSizeFull();

            header = new H3("Recent Orders");

            verticalLayout.add(header);

            List<Order> order = myOrderService.listOrderByUsername(sessionAttributes.getSessionEmail());
            for (Order orderValue : order){

                createListView(orderValue);

            }
            add(verticalLayout);

        }else {
            reusableComponents = new ReusableComponents();

            reusableComponents.showLoginDialogue(verticalLayout);

        }

    }


    private void createListView(Order orderValue){
        Paragraph orderName =  new Paragraph();
        Paragraph orderNumber = new Paragraph();
        Paragraph orderDate = new Paragraph();
        Paragraph orderTime = new Paragraph();

        orderName.setText("Order name:"+" "+orderValue.getOrderName());
        orderNumber.setText("Order number:"+" "+orderValue.getOrderNumber());
        orderTime.setText("Date:"+" "+orderValue.getOrderDate());
        orderDate.setText("Time:"+" "+orderValue.getOrderTime());

        verticalLayout.add(repeatedList(orderName,orderNumber,orderDate,orderTime));
    }

    private VerticalLayout repeatedList(Paragraph orderName, Paragraph orderNumber,Paragraph orderDate,Paragraph orderTime) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        VerticalLayout verticalLayout = new VerticalLayout();


        Button button = new Button("delete");
        button.addClickListener(event -> {
            orderName.setText("");
            orderNumber.setText("");
        });


        verticalLayout.setAlignItems(Alignment.START);
        setJustifyContentMode(JustifyContentMode.CENTER);
        verticalLayout.addClassName("orderItems");
        verticalLayout.setPadding(true);
        verticalLayout.setMaxWidth("620px");

        horizontalLayout.add(orderName,orderNumber,orderDate,orderTime,button);
        verticalLayout.add(horizontalLayout);
        return verticalLayout;
    }
}
