package com;
import com.asana.Client;
import com.asana.models.User;

/**
 * PAT Instructions:
 * <p>
 * 1. set your ASANA_ACCESS_TOKEN environment variable to a Personal Access Token found in Asana Account Settings
 */
public class ExamplePersonalAccessToken {

    public static void main(String[] args) throws Exception {
        

        System.out.println("== Example using a personal access token:");

        // create a client with a personal access token
        Client client = Client.accessToken("0/2c2cb10a7d9770ade77193b2d00d2fc1");

        // get the user object for yourself
        User me = client.users.me().execute();
        System.out.println("me=" + me.name);
    }
}
