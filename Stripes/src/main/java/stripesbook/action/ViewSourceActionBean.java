/***
 * Excerpted from "Stripes: and Java Web Development is Fun Again",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/fdstr for more book information.
***/
package stripesbook.action;

import java.io.*;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class ViewSourceActionBean extends BaseActionBean {
    private String filename;
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    private String source;
    public String getSource() {
        return source;
    }
    public Resolution view() throws Exception {
        String file = filename;
        if (file.startsWith("/")) {
            file = file.substring(file.indexOf("/", 1));
        }
        else {
            file = file.replaceAll("\\.","/");
            file = "/WEB-INF/src/" + file + ".java";
        }
        String path = getContext().getServletContext().getRealPath(file);

        BufferedReader reader =
            new BufferedReader(new FileReader(path));

        StringWriter writer = new StringWriter();

        String nextLine = null;
        while ((nextLine = reader.readLine()) != null) {
            writer.write(nextLine);
            writer.write('\n');
        }
        reader.close();
        source = writer.toString();
        return new ForwardResolution("/WEB-INF/jsp/view_source.jsp");
    }
}
