package com.sample.architecture.tags.shiro;

import javax.faces.view.facelets.TagConfig;

/**
 */
public class LacksPermissionTag extends PermissionTagHandler {

    // TODO - complete JavaDoc

    public LacksPermissionTag(TagConfig config) {
        super(config);
    }

    protected boolean showTagBody(String p) {
        return !isPermitted(p);
    }

}
