package listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionAttrListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("attribute added");
        System.out.println("Name:" + event.getName());
        System.out.println("Value:" + event.getValue());
        System.out.println(event.getSession().getId());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("attribute replaced");
        System.out.println("Name:" + event.getName());
        System.out.println("Value:" + event.getValue());
        System.out.println(event.getSession().getId());
    }
}
