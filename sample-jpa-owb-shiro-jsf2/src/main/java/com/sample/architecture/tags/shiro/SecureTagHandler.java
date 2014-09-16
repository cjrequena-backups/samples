package com.sample.architecture.tags.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

/**
 * Base class for all TagHandlers
 * 
 */
public abstract class SecureTagHandler extends TagHandler {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    public SecureTagHandler(TagConfig config) {
        super(config);
    }

    protected Subject getSubject() {
        return SecurityUtils.getSubject();
    }

}
