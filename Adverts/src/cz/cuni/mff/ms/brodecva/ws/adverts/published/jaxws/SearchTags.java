
package cz.cuni.mff.ms.brodecva.ws.adverts.published.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.7.18
 * Tue May 24 10:52:23 CEST 2016
 * Generated source version: 2.7.18
 */

@XmlRootElement(name = "searchTags", namespace = "http://published.adverts.ws.brodecva.ms.mff.cuni.cz/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchTags", namespace = "http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", propOrder = {"arg0", "arg1"})

public class SearchTags {

    @XmlElement(name = "arg0")
    private String[] arg0;
    @XmlElement(name = "arg1")
    private String[] arg1;

    public String[] getArg0() {
        return this.arg0;
    }

    public void setArg0(String[] newArg0)  {
        this.arg0 = newArg0;
    }

    public String[] getArg1() {
        return this.arg1;
    }

    public void setArg1(String[] newArg1)  {
        this.arg1 = newArg1;
    }

}
