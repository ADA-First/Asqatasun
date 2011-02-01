package org.opens.tanaguru.crawler;

import java.util.List;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;

/**
 * 
 * @author ADEX
 */
public interface Crawler {

    /**
     * 
     * @return
     *          the crawled webresource
     */
    WebResource getResult();

    /**
     * 
     */
    void run();

    /**
     *
     * @param webResourceFactory
     */
    void setWebResourceFactory(WebResourceFactory webResourceFactory);

    /**
     *
     * @param webResourceURL
     */
    void setSiteURL(String webResourceURL);

    /**
     *
     * @param siteName
     * @param webResourceURL
     */
    void setSiteURL(String siteName, String[] webResourceURL);

    /**
     *
     * @param pageURL
     */
    void setPageURL(String pageURL);

    /**
     *
     * @return
     *          the list of crawled contents
     */
    List<Content> getContentListResult();
}
