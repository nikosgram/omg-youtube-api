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

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Parameters {
    ON_BEHALF_OF_CONTENT_OWNER("onBehalfOfContentOwner"),
    PUBLISHED_BEFORE("publishedBefore"),
    PUBLISHED_AFTER("publishedAfter"),
    MY_SUBSCRIBERS("mySubscribers"),
    MANAGED_BY_ME("managedByMe"),
    FOR_USERNAME("forUsername"),
    PLAYLIST_ID("playlistId"),
    MAX_RESULTS("maxResults"),
    TEXT_FORMAT("textFormat"),
    REGION_CODE("regionCode"),
    CATEGORY_ID("categoryId"),
    CHANNEL_ID("channelId"),
    PAGE_TOKEN("pageToken"),
    FIELDS("fields"),
    PART("part"),
    MINE("mine"),
    HOME("home"),
    KEY("key"),
    ID("id"),
    HL("hl");

    private final String type;
}
