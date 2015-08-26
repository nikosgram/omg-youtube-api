/*
 * Copyright (c) 2015 Nikos Grammatikos
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import me.nikosgram.youtube.api.Parameter;
import me.nikosgram.youtube.api.Parameters;
import me.nikosgram.youtube.api.YoutubeAPI;
import me.nikosgram.youtube.api.responses.Playlist;
import me.nikosgram.youtube.api.responses.PlaylistItemList;
import me.nikosgram.youtube.api.responses.PlaylistList;

public class Example {
    private static YoutubeAPI api;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please type your API KEY.");
            return;
        }

        api = YoutubeAPI.create(args[0]).channel("gramerschan");

        String uploads = api.channel().getContentDetails().getRelatedPlaylists().getUploads();

        PlaylistItemList videos = api.getVideos(Parameter.create().put(Parameters.PLAYLIST_ID, uploads));
        System.out.println("Total Videos: " + videos.getPageInfo().getTotalResults());
        System.out.println("PlayLists: ");
        printPlayLists(null);
    }

    private static void printPlayLists(String pageToken) {
        Parameter entries = Parameter.create().put(Parameters.CHANNEL_ID, api.channel().getId());
        if (pageToken != null) {
            entries.put(Parameters.PAGE_TOKEN, pageToken);
        }

        PlaylistList playlistList = api.getPlayLists(entries);
        for (int i = 0; i < playlistList.getItems().length; i++) {
            Playlist item = playlistList.getItems()[i];

            PlaylistItemList itemList = api.getVideos(Parameter.create().put(Parameters.PLAYLIST_ID, item.getId()));

            System.out.println(item.getSnippet().getTitle() + " [Videos: " + itemList.getPageInfo().getTotalResults() + ']');
        }

        if (playlistList.getNextPageToken() != null) {
            printPlayLists(playlistList.getNextPageToken());
        }
    }
}
