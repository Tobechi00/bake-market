package com.occasionalbaker.bakersite.views.homepage;

import com.occasionalbaker.bakersite.backend.entity.CakeCatalogue;
import com.occasionalbaker.bakersite.backend.entity.Order;
import com.occasionalbaker.bakersite.backend.repository.CakeCatalogueRepository;
import com.occasionalbaker.bakersite.backend.service.OrderService;
import com.occasionalbaker.bakersite.views.reusablecomponents.ReusableComponents;
import com.occasionalbaker.bakersite.views.session.SessionAttributes;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Route(value = "place_an_order", layout = HomeLayout.class)
@PageTitle("place an order")
@AnonymousAllowed
public class PlaceOrderView extends VerticalLayout {

    H2 header;

    VerticalLayout verticalLayout,scrollerLayout;
    ReusableComponents reusableComponents;
    List<CakeCatalogue> availableCakes;

    List<String> cakeNames,selectedCakeList;

    Accordion cakeAccordion;

    Scroller cakeScroller;


    CakeCatalogueRepository cakeCatalogueRepository;

    Select<String> cakeSelection;

    TextArea details,address,selectedCake;

    Button orderButton,clearButton;

    StringBuilder stringBuilder;
    Select<String> cakeArrangement,frosting_Filling;

    SessionAttributes sessionAttributes;

    OrderService orderService;

    //total cost of order filling incurs extra cost
    int totalCost;


    public PlaceOrderView(CakeCatalogueRepository cakeCatalogueRepository,SessionAttributes sessionAttributes,OrderService orderService){
        this.orderService = orderService;
        this.sessionAttributes = sessionAttributes;
        this.cakeCatalogueRepository = cakeCatalogueRepository;

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        verticalLayout = new VerticalLayout();

        if (!(VaadinSession.getCurrent().getAttribute("USER_ID") == null)){
            verticalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
            verticalLayout.setAlignItems(Alignment.CENTER);
            verticalLayout.addClassName("feedBackCenter");
            verticalLayout.setMaxWidth("700px");
            verticalLayout.setPadding(true);
            verticalLayout.setSizeFull();

            reusableComponents = new ReusableComponents();


            cakeAccordion = new Accordion();
            cakeAccordion.close();

            cakeArrangement = new Select<>();
            cakeArrangement.setLabel("cake arrangement");
            cakeArrangement.setSizeFull();
            cakeArrangement.setMaxWidth("300px");
            cakeArrangement.setItems("separate","stacked");
            cakeArrangement.setValue("separate");

            frosting_Filling = new Select<>();
            frosting_Filling.setSizeFull();
            frosting_Filling.setMaxWidth("300px");
            frosting_Filling.setLabel("frosting/filling");
            frosting_Filling.setItems("none","ganache");
            frosting_Filling.setValue("none");

            cakeScroller = new Scroller();
            cakeScroller.setScrollDirection(Scroller.ScrollDirection.VERTICAL);
            cakeScroller.setMaxHeight("100px");

            scrollerLayout = new VerticalLayout();


            stringBuilder = new StringBuilder();



            cakeSelection = new Select<>();
            selectedCakeList = new ArrayList<>();

            details = new TextArea("details(optional)");
            details.setSizeFull();
            details.setMaxWidth("300px");
            details.setMaxHeight("200px");

            address = new TextArea("address");
            address.setSizeFull();
            address.setMaxWidth("300px");
            address.setMaxHeight("200px");

            selectedCake = new TextArea("selected Cake");
            selectedCake.setSizeFull();
            selectedCake.setMaxWidth("300px");
            selectedCake.setMaxHeight("200px");
            selectedCake.setReadOnly(true);
            selectedCake.setPlaceholder("your selected cake will appear here");



            header = new H2("place an Order");

            availableCakes = cakeCatalogueRepository.findAll();
            cakeNames = new ArrayList<>();

            for (CakeCatalogue cakeCatalogue: availableCakes){
                if (cakeCatalogue.getAvailable()){
                    cakeNames.add(cakeCatalogue.getCakeName());
                }
            }


            cakeSelection.setItems(cakeNames);

            cakeScroller.setContent(repeatedList(availableCakes));


            clearButton = new Button("clear");
            clearButton.addClassName("submitButton");
            clearButton.addClickListener(event -> {
                selectedCake.setValue("");
                selectedCakeList.clear();
                stringBuilder.setLength(0);
            });


            orderButton = new Button("order");
            orderButton.addClassName("submitButton");
            orderButton.addClickListener(event -> {

                if (clean(stringBuilder.toString(), cakeArrangement.getValue(), frosting_Filling.getValue(), details.getValue(), address.getValue())){
                    Date date = new Date();

                    SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Order order = new Order();
                    order.setUsername(sessionAttributes.getSessionEmail());
                    order.setOrderName(selectedCakeList.size() + "cake(s)");
                    order.setOrderDetails(
                            stringBuilder.toString()+"\n"+
                                    cakeArrangement.getValue()+"\n"
                                    + frosting_Filling.getValue()+"\n"
                                    + details.getValue()
                    );
                    order.setAddress(address.getValue());
                    order.setOrderDate(sdf.format(date));
                    order.setOrderTime(stf.format(date));
                    //generate order number
                    order.setOrderNumber("1011002");
                    System.out.println(
                            cakeArrangement.getValue()+"\n"
                                    + frosting_Filling.getValue()+"\n"
                                    + details.getValue()
                    );



                    Button confirm = new Button("order now", exe -> orderService.saveOrder(order));
                    Button cancel = new Button("cancel");
                    ConfirmDialog dialog = reusableComponents.createConfirmDialogue("please confirm","you have placed an order for"+" "+selectedCakeList.size()+" "+"cake(s)"+"\n"+
                            "would you like to proceed with this order?",confirm,cancel
                    );
                    dialog.open();
                }


            });

            cakeAccordion.add("Select Cake",cakeScroller);
            verticalLayout.add(header,cakeAccordion,selectedCake,clearButton,cakeArrangement,frosting_Filling,details,address,orderButton);

            add(verticalLayout);


        }
        else{
            reusableComponents = new ReusableComponents();
            reusableComponents.showLoginDialogue(verticalLayout);
        }

    }
    private VerticalLayout repeatedList(List<CakeCatalogue> availableCakes){
        VerticalLayout verticalLayout = new VerticalLayout();

        for (CakeCatalogue cakeCatalogue : availableCakes){
            HorizontalLayout horizontalLayout = new HorizontalLayout();

            Button addButton = new Button("add");
            addButton.addClassName("submitButton");
            addButton.addClickListener(event -> {
                stringBuilder.append(cakeCatalogue.getCakeName()).append(",");
                selectedCakeList.add(cakeCatalogue.getCakeName());
                selectedCake.setValue(stringBuilder.toString());
            });
            Span cakeName = new Span(cakeCatalogue.getCakeName());
            horizontalLayout.add(cakeName,addButton);
            verticalLayout.add(horizontalLayout);
        }
        return verticalLayout;
    }
    private boolean clean(String selectedCake,String cakeArrangement,String filling,String detailValue, String address){

        if (selectedCake.isEmpty()|| cakeArrangement.isEmpty()||filling.isEmpty()||detailValue.isEmpty()||address.isEmpty()){
            Notification notification = new Notification();
            notification.setDuration(5000);
            notification.setText("please ensure to fill out all spaces");
            notification.setPosition(Notification.Position.TOP_CENTER);
            notification.open();
            return false;
        }else {
            return true;
        }
    }
    //calculate total order cost
}
