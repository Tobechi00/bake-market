package com.occasionalbaker.bakersite.views.homepage;


import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "",layout = HomeLayout.class)
@AnonymousAllowed
@PageTitle("about us")
public class AboutView extends VerticalLayout {
    H1 header;
    Text t1,t2,t3,t4;

    Paragraph p1,p2,p3,slogan;

    Html html;

    VerticalLayout headerLayout;

    VerticalLayout bodyLayout;
    String maxWidth;
    public AboutView(){
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setClassName("aboutView");
        maxWidth = "700px";

        headerLayout = new VerticalLayout();
        bodyLayout = new VerticalLayout();


        headerLayout.setSizeFull();
        headerLayout.addClassName("headerLayout");
        headerLayout.setAlignItems(Alignment.CENTER);
        headerLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        headerLayout.setMaxWidth(maxWidth);

        bodyLayout.setAlignItems(Alignment.CENTER);
        bodyLayout.addClassName("aboutBodyLayout");
        bodyLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        bodyLayout.setMaxWidth(maxWidth);

        header = new H1("The Occasional Baker");
        header.addClassName("aboutHeader");

        slogan = new Paragraph("Baking Memories, One Cake at a Time.");
        slogan.setClassName("slogan");

        t1 = new Text(WebText.abtParagraphOne);
        t2 = new Text(WebText.abtParagraphTwo);
        t3 = new Text(WebText.abtParagraphThree);
        t4 = new Text(WebText.abtParagraphFour);

        p1 = new Paragraph(t1);
        p2 = new Paragraph(t2);
        p3 = new Paragraph(t3);

        html = new Html(WebText.abtParagraphFour);

        headerLayout.add(header,slogan);

        bodyLayout.add(p1,p2,p3,html);

        add(headerLayout,bodyLayout);

    }
}
