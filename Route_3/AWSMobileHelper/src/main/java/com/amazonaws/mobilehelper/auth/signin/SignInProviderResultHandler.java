package com.amazonaws.mobilehelper.auth.signin;
//
// Copyright 2017 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.18
//

import com.amazonaws.mobilehelper.auth.IdentityProvider;

/**
 *  Implement this interface to get callbacks for the results to a sign-in operation.
 */
public interface SignInProviderResultHandler {

    /**
     * Sign-in was successful.
     *
     * @param provider sign-in identity provider
     */
    void onSuccess(IdentityProvider provider);

    /**
     * Sign-in was cancelled by the user.
     *
     * @param provider sign-in identity provider
     */
    void onCancel(IdentityProvider provider);

    /**
     * Sign-in failed.
     *
     * @param provider sign-in identity provider
     * @param ex exception that occurred
     */
    void onError(IdentityProvider provider, Exception ex);
}
