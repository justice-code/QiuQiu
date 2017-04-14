package org.eddy.xml;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Created by eddy on 2017/4/14.
 */
public class UrlDtdPathResolver implements EntityResolver {
    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        if (systemId != null) {
            return new InputSource(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/config.dtd"));
        }
        return null;
    }
}
