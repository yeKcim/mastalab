/* Copyright 2017 Thomas Schneider
 *
 * This file is a part of Mastalab
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * Mastalab is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Mastalab; if not,
 * see <http://www.gnu.org/licenses>. */
package fr.gouv.etalab.mastodon.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import fr.gouv.etalab.mastodon.client.API;
import fr.gouv.etalab.mastodon.client.APIResponse;
import fr.gouv.etalab.mastodon.client.Entities.Results;
import fr.gouv.etalab.mastodon.interfaces.OnRetrieveRemoteAccountInterface;


/**
 * Created by Thomas on 22/07/2017.
 * Retrieves a remote account via its webpage
 */

public class RetrieveRemoteAccountsAsyncTask extends AsyncTask<Void, Void, Void> {

    private OnRetrieveRemoteAccountInterface listener;
    private String url;
    private Context context;
    private Results results;

    public RetrieveRemoteAccountsAsyncTask(Context context, String username, String instance, OnRetrieveRemoteAccountInterface onRetrieveRemoteAccountInterface){
        this.url = "https://" + instance  + "/@" + username;
        this.listener = onRetrieveRemoteAccountInterface;
        this.context = context;
    }



    @Override
    protected Void doInBackground(Void... params) {
        API api = new API(context);
        results = api.search(this.url);
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        listener.onRetrieveRemoteAccount(results);
    }

}
