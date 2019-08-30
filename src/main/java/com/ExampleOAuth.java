package com;
import com.asana.Client;
import com.asana.OAuthApp;
import com.asana.models.User;
import com.google.common.io.LineReader;

import java.awt.*;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * OAuth Instructions:
 * <p>
 * 1. create a new application in your Asana Account Settings ("App" panel)
 * 2. set the redirect URL to "urn:ietf:wg:oauth:2.0:oob"
 * 3. set your ASANA_CLIENT_ID and ASANA_CLIENT_SECRET environment variables
 */
public class ExampleOAuth {
    public static void main(String[] args) throws Exception {
        

        System.out.println("== Example using OAuth Client ID and Client Secret:");

        // create an OAuth app with the OAuth credentials:
        OAuthApp app = new OAuthApp("1137649898949766",
                "4800a90b4dad2efc63efa1b0e1537001",
                // this special redirect URI will prompt the user to copy/paste the code.
                // useful for command line scripts and other non-web apps
                OAuthApp.NATIVE_REDIRECT_URI
        );

        // create an OAuth client with the app
        Client client = Client.oauth(app);

        System.out.println("isAuthorized=" + app.isAuthorized());

        // get an authorization URL:
        String url = app.getAuthorizationUrl("FIXME: random state");
        System.out.println(url);

        // in a web app you'd redirect the user to this URL when they take action to
        // login with Asana or connect their account to Asana
        Desktop.getDesktop().browse(new URI(url));

        // prompt the user to copy and paste the code from the browser window
        System.out.println("Copy and paste the returned code from the browser and press enter:");
        String code = new LineReader(new InputStreamReader(System.in)).readLine();

        // exchange the code for a bearer token
        // normally you'd persist this token somewhere
        String accessToken = app.fetchToken(code);

        System.out.println("isAuthorized=" + app.isAuthorized());
        System.out.println("token=" + accessToken);

        // get some information about your own user
        User user = client.users.me().execute();
        System.out.println("me=" + user.name);
        System.out.println(user.id);

        // get your photo, if you have one
        if (user.photo != null) {
            System.out.println(user.photo.image_128x128);
        }

        System.out.println(user.workspaces.iterator().next().name);

        // demonstrate creating a client using a previously obtained bearer token
        System.out.println("== Example using OAuth Access Token:");
        app = new OAuthApp(
        		"1137649898949766",
                "0/2c2cb10a7d9770ade77193b2d00d2fc1",
                "urn:ietf:wg:oauth:2.0:oob",
                accessToken
        );
        client = Client.oauth(app);

        System.out.println("isAuthorized=" + app.isAuthorized());
        System.out.println("me=" + client.users.me().execute().name);
    }
}
