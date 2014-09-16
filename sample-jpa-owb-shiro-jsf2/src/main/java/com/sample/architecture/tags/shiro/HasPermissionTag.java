package com.sample.architecture.tags.shiro;

import javax.faces.view.facelets.TagConfig;

/**
 * Tag that renders the tag body only if the current user has the string permissions
 * specified in <tt>name</tt> attribute.
 *
 */
public class HasPermissionTag extends PermissionTagHandler {

    public HasPermissionTag(TagConfig config) {
        super(config);
    }

    protected boolean showTagBody(String p) {
        return isPermitted(p);
    }
}
