package stripesbook.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import stripesbook.dao.ContactDao;
import stripesbook.dao.mock.MockContactDao;
import stripesbook.model.Contact;

import java.util.List;

public class ContactListActionBean extends BaseActionBean {
    private static final String LIST = "/WEB-INF/jsp/contact_list.jsp";
    private static final String VIEW = "/WEB-INF/jsp/contact_view.jsp";
    private ContactDao contactDao = MockContactDao.getInstance();
    private Integer contactId;

    @DefaultHandler
    public Resolution list() {
        return new ForwardResolution(LIST);
    }

    public List<Contact> getContacts() {
        return contactDao.read();
    }

    public Resolution view() {
        return new ForwardResolution(VIEW);
    }

    public void setContactId(Integer id) {
        contactId = id;
    }

    public Contact getContact() {
        return contactDao.read(contactId);
    }

    public Resolution delete(){
        contactDao.delete(contactId);
        return new RedirectResolution(getClass());
    }
}
