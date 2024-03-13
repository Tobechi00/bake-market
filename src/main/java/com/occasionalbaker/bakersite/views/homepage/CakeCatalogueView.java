package com.occasionalbaker.bakersite.views.homepage;

import com.occasionalbaker.bakersite.backend.controller.CakeCatalogueController;
import com.occasionalbaker.bakersite.backend.entity.CakeCatalogue;
import com.occasionalbaker.bakersite.backend.service.CakeCatalogueService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "cake_catalogue",layout = HomeLayout.class)
@AnonymousAllowed
@PageTitle("cake catalogue")
public class CakeCatalogueView extends VerticalLayout {

    CakeCatalogueService cakeCatalogueService;

    CakeCatalogueController cakeCatalogueController;

    List<CakeCatalogue>cakeList;

    @Autowired
    public CakeCatalogueView(CakeCatalogueService cakeCatalogueService,CakeCatalogueController cakeCatalogueController){
        this.cakeCatalogueService = cakeCatalogueService;
        this.cakeCatalogueController = cakeCatalogueController;

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        cakeList = cakeCatalogueService.catalogueRepositoryList();

        for (CakeCatalogue cakeCatalogue : cakeList){
            HorizontalLayout cakeListComponent = createCakeList(cakeCatalogue);
            cakeListComponent.setAlignItems(Alignment.CENTER);
            cakeListComponent.setJustifyContentMode(JustifyContentMode.CENTER);
            cakeListComponent.setSpacing(true);
            cakeListComponent.setPadding(true);
            cakeListComponent.setSizeFull();
            cakeListComponent.setMaxWidth("600px");

            add(cakeListComponent);
        }


    }
    private HorizontalLayout createCakeList(CakeCatalogue cakeCatalogue){
        HorizontalLayout wrapperLayout = new HorizontalLayout();

        wrapperLayout.setAlignItems(Alignment.CENTER);

        HorizontalLayout priceLayout = new HorizontalLayout();
        priceLayout.setAlignItems(Alignment.END);
        priceLayout.setJustifyContentMode(JustifyContentMode.END);

        VerticalLayout detailsLayout = new VerticalLayout();
        detailsLayout.setAlignItems(Alignment.CENTER);
        detailsLayout.setMaxWidth("300px");
        detailsLayout.setPadding(true);
        detailsLayout.setSpacing(true);


        Button details = new Button("see full details");
        details.addClassName("submitButton");


        Image image = cakeCatalogueController.generateImage(cakeCatalogue);

        image.setMaxHeight("150px");
        image.setMaxWidth("300px");
        image.addClassName("catalogueImage");

        VerticalLayout imageLayout = new VerticalLayout();
        imageLayout.setAlignItems(Alignment.START);
        imageLayout.add(image);


        H3 header = new H3(cakeCatalogue.getCakeName());

        Span price = new Span(cakeCatalogue.getCakePrice());
        price.getElement().getThemeList().add("badge");
        price.addClassName("red");



        boolean isAvailable = cakeCatalogue.getAvailable();

        Span available = new Span();
        if (isAvailable){
            available.setText("Available");
            available.getElement().getThemeList().add("badge success");
        }else {
            available.setText("Not Available");
            available.getElement().getThemeList().add("badge error");
        }


        priceLayout.add(available,price);

        detailsLayout.add(header,details,priceLayout);


        wrapperLayout.addClassName("orderItems");
        wrapperLayout.add(imageLayout,detailsLayout);

        return wrapperLayout;
    }


    //compress image
}
