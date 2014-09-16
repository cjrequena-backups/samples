package com.sample.architecture.tags.shiro;

import javax.faces.view.facelets.TagConfig;

/**
 */
public class HasRoleTag extends PermissionTagHandler {

    // TODO - complete JavaDoc

    public HasRoleTag(TagConfig config) {
        super(config);
    }

    @Override
    protected boolean showTagBody(String roleName) {
        return getSubject() != null && getSubject().hasRole(roleName);
    }

}