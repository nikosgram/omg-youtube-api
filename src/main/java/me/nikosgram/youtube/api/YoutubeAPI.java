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

package me.nikosgram.youtube.api;

import lombok.ToString;
import me.nikosgram.youtube.api.responses.Channel;
import me.nikosgram.youtube.api.responses.ChannelList;
import me.nikosgram.youtube.api.responses.PlaylistItemList;
import me.nikosgram.youtube.api.responses.PlaylistList;

import java.io.IOException;
import java.util.Map;

@ToString
@SuppressWarnings("unused")
public class YoutubeAPI {
    private final String key;

    private Channel channel;

    private YoutubeAPI(String key) {
        this.key = key;
    }

    public static YoutubeAPI create(String key) {
        return new YoutubeAPI(key);
    }

    private static Parameter changes(Parameter first, Parameter second) {
        if (second.method() != null) {
            first.method(second.method());
        }
        for (Map.Entry<String, String> entry : second) {
            if (!first.contains(entry.getKey())) {
                first.put(entry.getKey(), entry.getValue());
            }
        }
        return first;
    }

    public YoutubeAPI channel(String channel) {
        this.channel = getCustom(
                Parameter.create("channels")
                        .put(Parameters.PART, "contentDetails")
                        .put(Parameters.FOR_USERNAME, channel),
                ChannelList.class
        ).getItems()[0];

        return this;
    }

    public Channel channel() {
        return channel;
    }

    public PlaylistList getPlayLists(Parameter parameter) {
        return getCustom(changes(
                parameter,
                Parameter.create("playlists")
                        .put(Parameters.PART, "snippet")
                        .put(Parameters.CHANNEL_ID, channel.getId())
        ), PlaylistList.class);
    }

    public PlaylistItemList getVideos(Parameter parameter) {
        return getCustom(changes(
                parameter,
                Parameter.create("playlistItems")
                        .put(Parameters.PART, "snippet")
        ), PlaylistItemList.class);
    }

    public <T> T getCustom(Parameter parameter, Class<T> tClass) {
        try {
            return changes(parameter, Parameter.create().put(Parameters.KEY, key)).result(tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
