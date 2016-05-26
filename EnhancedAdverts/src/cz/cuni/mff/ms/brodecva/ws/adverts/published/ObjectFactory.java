
package cz.cuni.mff.ms.brodecva.ws.adverts.published;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cz.cuni.mff.ms.brodecva.ws.adverts.published package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SearchTags_QNAME = new QName("http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", "searchTags");
    private final static QName _ListPublishedResponse_QNAME = new QName("http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", "listPublishedResponse");
    private final static QName _SearchEmailResponse_QNAME = new QName("http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", "searchEmailResponse");
    private final static QName _ListPublished_QNAME = new QName("http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", "listPublished");
    private final static QName _GetAdvert_QNAME = new QName("http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", "getAdvert");
    private final static QName _SearchTagsResponse_QNAME = new QName("http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", "searchTagsResponse");
    private final static QName _SearchEmail_QNAME = new QName("http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", "searchEmail");
    private final static QName _GetAdvertResponse_QNAME = new QName("http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", "getAdvertResponse");
    private final static QName _SearchPhoneResponse_QNAME = new QName("http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", "searchPhoneResponse");
    private final static QName _Advert_QNAME = new QName("http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", "Advert");
    private final static QName _SearchPhone_QNAME = new QName("http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", "searchPhone");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cz.cuni.mff.ms.brodecva.ws.adverts.published
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListPublished }
     * 
     */
    public ListPublished createListPublished() {
        return new ListPublished();
    }

    /**
     * Create an instance of {@link SearchTagsResponse }
     * 
     */
    public SearchTagsResponse createSearchTagsResponse() {
        return new SearchTagsResponse();
    }

    /**
     * Create an instance of {@link GetAdvert }
     * 
     */
    public GetAdvert createGetAdvert() {
        return new GetAdvert();
    }

    /**
     * Create an instance of {@link SearchEmail }
     * 
     */
    public SearchEmail createSearchEmail() {
        return new SearchEmail();
    }

    /**
     * Create an instance of {@link SearchTags }
     * 
     */
    public SearchTags createSearchTags() {
        return new SearchTags();
    }

    /**
     * Create an instance of {@link ListPublishedResponse }
     * 
     */
    public ListPublishedResponse createListPublishedResponse() {
        return new ListPublishedResponse();
    }

    /**
     * Create an instance of {@link SearchEmailResponse }
     * 
     */
    public SearchEmailResponse createSearchEmailResponse() {
        return new SearchEmailResponse();
    }

    /**
     * Create an instance of {@link SearchPhone }
     * 
     */
    public SearchPhone createSearchPhone() {
        return new SearchPhone();
    }

    /**
     * Create an instance of {@link GetAdvertResponse }
     * 
     */
    public GetAdvertResponse createGetAdvertResponse() {
        return new GetAdvertResponse();
    }

    /**
     * Create an instance of {@link SearchPhoneResponse }
     * 
     */
    public SearchPhoneResponse createSearchPhoneResponse() {
        return new SearchPhoneResponse();
    }

    /**
     * Create an instance of {@link Advert }
     * 
     */
    public Advert createAdvert() {
        return new Advert();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchTags }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", name = "searchTags")
    public JAXBElement<SearchTags> createSearchTags(SearchTags value) {
        return new JAXBElement<SearchTags>(_SearchTags_QNAME, SearchTags.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListPublishedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", name = "listPublishedResponse")
    public JAXBElement<ListPublishedResponse> createListPublishedResponse(ListPublishedResponse value) {
        return new JAXBElement<ListPublishedResponse>(_ListPublishedResponse_QNAME, ListPublishedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchEmailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", name = "searchEmailResponse")
    public JAXBElement<SearchEmailResponse> createSearchEmailResponse(SearchEmailResponse value) {
        return new JAXBElement<SearchEmailResponse>(_SearchEmailResponse_QNAME, SearchEmailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListPublished }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", name = "listPublished")
    public JAXBElement<ListPublished> createListPublished(ListPublished value) {
        return new JAXBElement<ListPublished>(_ListPublished_QNAME, ListPublished.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAdvert }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", name = "getAdvert")
    public JAXBElement<GetAdvert> createGetAdvert(GetAdvert value) {
        return new JAXBElement<GetAdvert>(_GetAdvert_QNAME, GetAdvert.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchTagsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", name = "searchTagsResponse")
    public JAXBElement<SearchTagsResponse> createSearchTagsResponse(SearchTagsResponse value) {
        return new JAXBElement<SearchTagsResponse>(_SearchTagsResponse_QNAME, SearchTagsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchEmail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", name = "searchEmail")
    public JAXBElement<SearchEmail> createSearchEmail(SearchEmail value) {
        return new JAXBElement<SearchEmail>(_SearchEmail_QNAME, SearchEmail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAdvertResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", name = "getAdvertResponse")
    public JAXBElement<GetAdvertResponse> createGetAdvertResponse(GetAdvertResponse value) {
        return new JAXBElement<GetAdvertResponse>(_GetAdvertResponse_QNAME, GetAdvertResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchPhoneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", name = "searchPhoneResponse")
    public JAXBElement<SearchPhoneResponse> createSearchPhoneResponse(SearchPhoneResponse value) {
        return new JAXBElement<SearchPhoneResponse>(_SearchPhoneResponse_QNAME, SearchPhoneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Advert }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", name = "Advert")
    public JAXBElement<Advert> createAdvert(Advert value) {
        return new JAXBElement<Advert>(_Advert_QNAME, Advert.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchPhone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", name = "searchPhone")
    public JAXBElement<SearchPhone> createSearchPhone(SearchPhone value) {
        return new JAXBElement<SearchPhone>(_SearchPhone_QNAME, SearchPhone.class, null, value);
    }

}
