package stripesbook.dao.mock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stripesbook.dao.ContactDao;
import stripesbook.model.Contact;

/**
 * Mock implementation of the Contact DAO. This implementation does
 * not attempt to be efficient; rather, it serves the purposes of
 * providing a simple working DAO with no dependencies on libraries or
 * a database.
 */
public class MockContactDao implements ContactDao {
    private List<Contact> list = new ArrayList<Contact>();
    private Map<Integer,Contact> map = new HashMap<Integer,Contact>();
    private int nextId = 1;

    public List<Contact> read() {
        return list;
    }
    public Contact read(Integer id) {
        return map.get(id);
    }
    public void save(Contact contact) {
        if (contact != null) {
            if (contact.getId() == null) {
                int id = nextId++;
                contact.setId(id);
                list.add(contact);
            }
            else {
                Integer id = contact.getId();
                list.set(list.indexOf(map.get(id)), contact);
            }
            map.put(contact.getId(), contact);
        }
    }
    public void delete(Integer id) {
        Contact contact = map.get(id);
        list.remove(contact);
        map.remove(id);
    }

    private static final int FIRST_NAME   =  0;
    private static final int LAST_NAME    =  1;
    private static final int EMAIL        =  2;
    private static final int PHONE_NUMBER =  3;
    private static final int BIRTH_DATE   =  4;

    private static final String[] RAW_DATA = {
        "Sophie,Hunter,sh@stripesbook.org,555-555-8440,1981-08-08",
        "Daniel,Greene,dg@stripesbook.org,555-555-7763,1986-06-03",
        "Jen,Ballou,jb@stripesbook.org,555-555-6495,1982-08-30",
        "Sammy,Blair,sb@stripesbook.org,555-555-9592,1981-06-02",
        "Betty,Stocker,bs@stripesbook.org,555-555-8316,1987-05-22",
        "Lou,Thompson,lt@stripesbook.org,555-555-2765,1980-08-29",
        "Lexi,Hawk,lh@stripesbook.org,555-555-7211,1982-05-01",
        "George,Wells,gw@stripesbook.org,555-555-7689,1987-05-15",
        "Donna,McCallum,dm@stripesbook.org,555-555-3432,1979-12-28",
        "Jason,Wilson,jw@stripesbook.org,555-555-4032,1978-04-03",
    };

    private static final DateFormat dateFormat =
        new SimpleDateFormat("yyyy-MM-dd");

    private MockContactDao() {
        try {
            for (String string : RAW_DATA) {
                save(createContactFromString(string));
            }
        }
        catch (Exception exc) {
            throw new RuntimeException(exc);
        }
    }

    private static MockContactDao instance = new MockContactDao();
    public static MockContactDao getInstance() { return instance; }

    private Contact createContactFromString(String string)
        throws Exception
    {
        String[] fields = string.split(",");

        Contact contact = new Contact();

        contact.setFirstName(fields[FIRST_NAME]);
        contact.setLastName(fields[LAST_NAME]);
        contact.setEmail(fields[EMAIL]);
        contact.setPhoneNumber(fields[PHONE_NUMBER]);
        contact.setBirthDate(dateFormat.parse(fields[BIRTH_DATE]));

        return contact;
    }
}