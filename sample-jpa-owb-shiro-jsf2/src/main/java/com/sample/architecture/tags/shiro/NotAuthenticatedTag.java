package com.sample.architecture.tags.shiro;

import javax.faces.view.facelets.TagConfig;

/**
 * Tag that renders the tag body only if the current user has <em>not</em> executed a successful authentication
 * attempt <em>during their current session</em>.
 *
 * <p>The logically opposite tag of this one is the {@link AuthenticatedTag}.
 *

 */
public class NotAuthenticatedTag extends AuthenticatedTag {

    public NotAuthenticatedTag(TagConfig config) {
        super(config);
    }

    @Override
    protected boolean checkAuthentication() {
        return !super.checkAuthentication();
    }
}
