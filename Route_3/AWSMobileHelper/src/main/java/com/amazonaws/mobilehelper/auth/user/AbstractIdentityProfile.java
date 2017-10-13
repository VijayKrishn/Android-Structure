package com.amazonaws.mobilehelper.auth.user;
//
// Copyright 2017 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.18
//

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Map;

/**
 * Base class for Identity Profile objects that retrieve the identity profile for users
 * signed in with an identity provider.
 */
public abstract class AbstractIdentityProfile implements IdentityProfile {
    /** Log tag. */
    private static final String LOG_TAG = AbstractIdentityProfile.class.getSimpleName();

    protected String userId = null;

    /** User's name. */
    protected String userName = null;

    /** User's image Url. */
    protected String userImageUrl = null;

    // User's image if loaded.
    private Bitmap userImage = null;

    @Override
    public String getUserId(){return userId;}

    @Override
    public String getUserImageUrl() {
        return userImageUrl;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public Bitmap getUserImage() {
        return userImage;
    }
    @Override
    public Map<String, String> getCustomProfileAttributesMap() {
        return Collections.emptyMap();
    }

    @Override
    public void loadUserImage() throws IOException {
        if (userImageUrl == null) {
            userImage = null;
            Log.d(LOG_TAG, "loadUserImage(): Cannot load user image; user image url is null. ");
            return;
        }

        final InputStream is = new URL(userImageUrl).openStream();
        try {
            userImage = BitmapFactory.decodeStream(is);
        } finally {
            is.close();
        }
    }
}
