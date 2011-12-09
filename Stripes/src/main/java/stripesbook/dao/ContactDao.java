package stripesbook.dao;


import java.util.List;
import stripesbook.model.Contact;


public interface ContactDao {
    public List<Contact> read();
    public Contact read(Integer id);
    public void save(Contact contact);
    public void delete(Integer id);
}
