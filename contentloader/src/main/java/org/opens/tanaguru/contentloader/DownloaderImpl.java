package org.opens.tanaguru.contentloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLPeerUnverifiedException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class DownloaderImpl implements Downloader {

    protected String result;
    protected String url;
    private final String HTTP_PROTOCOL_PREFIX = "http://";
    private final String HTTPS_PROTOCOL_PREFIX = "https://";
    private final String FILE_PROTOCOL_PREFIX = "file://";

    public DownloaderImpl() {
        super();
    }

    private String load(String url) {
        BufferedReader in = null;
        try {
            StringBuffer urlContent = new StringBuffer();
            String thisLine;
            URL u = new URL(url);
            in = new BufferedReader(new InputStreamReader(u.openStream()));
            while ((thisLine = in.readLine()) != null) {
                //Correction of #34 bug
                urlContent.append(thisLine + "\r");
            }
            return urlContent.toString();
        } catch (IOException ex) {
            Logger.getLogger(DownloaderImpl.class.getName()).log(Level.SEVERE,
                    null, ex);
            return "";
//            throw new RuntimeException(ex);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(DownloaderImpl.class.getName()).log(
                        Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        }
    }

    private String download(String url) {
        HttpClient httpclient = new DefaultHttpClient();

        HttpGet httpget = new HttpGet(url);

        // Create a response handler
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = null;
        try {
            responseBody = httpclient.execute(httpget, responseHandler);
        } catch (HttpResponseException ex) {
            Logger.getLogger(DownloaderImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
//            HttpReturnStatus returnStatus = new HttpReturnStatus(ex.getStatusCode());
//            return returnStatus.toString();
            return "";
        } catch (UnknownHostException ex ) {
            Logger.getLogger(DownloaderImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
            return "";
        } catch (SSLPeerUnverifiedException ex) {
            Logger.getLogger(DownloaderImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
            return "";
        } catch (IOException ex) {
            Logger.getLogger(DownloaderImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
            return "";
        } 
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
        return responseBody;
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public String getURL() {
        return url;
    }

    @Override
    public void run() {
        if (url.startsWith(HTTP_PROTOCOL_PREFIX)
                || url.startsWith(HTTPS_PROTOCOL_PREFIX)) {
            result = download(url);
        } else if (url.startsWith(FILE_PROTOCOL_PREFIX)) {
            result = load(url);
        }
    }

    @Override
    public void setURL(String url) {
        this.url = url;
    }

}
