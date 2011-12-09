package stripesbook.action;

import net.sourceforge.stripes.action.*;

import java.util.Date;
import java.util.Random;

public class HelloActionBean implements ActionBean {/* (1) */
    private ActionBeanContext ctx;
    public ActionBeanContext getContext() { return ctx; }
    public void setContext(ActionBeanContext ctx) { this.ctx = ctx; }

    private Date date;/* (2) */
    public Date getDate() {
        return date;
    }
    @DefaultHandler
    public Resolution currentDate() {/* (3) */
        date = new Date();
        return new ForwardResolution(VIEW);
    }
    public Resolution randomDate() {
        long max = System.currentTimeMillis();
        long random = new Random().nextLong() % max;
        date = new Date(random);
        return new ForwardResolution(VIEW);
    }
    private static final String VIEW = "/WEB-INF/jsp/hello.jsp";
}