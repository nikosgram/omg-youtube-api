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

import com.google.gson.Gson;
import lombok.ToString;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@ToString
@SuppressWarnings("unused")
public class Parameter implements Iterable<Map.Entry<String, String>>, Cloneable {
    private static final Gson GSON = new Gson();
    private static final String API_V3 = "https://www.googleapis.com/youtube/v3/";
    private final Map<String, String> map = new HashMap<>();
    private String method;

    private Parameter() {
        this.method = null;
    }

    private Parameter(String method) {
        this.method = method;
    }

    private static <T> T result(URL url, Class<T> tClass) throws IOException {
        return GSON.fromJson(new InputStreamReader(
                url.openStream(), Charset.forName("UTF-8")
        ), tClass);
    }

    public static Parameter create() {
        return new Parameter();
    }

    public static Parameter create(String method) {
        return new Parameter(method);
    }

    public Parameter remove(String key) {
        map.remove(key);
        return this;
    }

    public Parameter put(String key, String value) {
        map.put(key, value);
        return this;
    }

    public Parameter put(Parameters key, String value) {
        map.put(key.getType(), value);
        return this;
    }

    public Parameter put(Map<String, String> map) {
        this.map.putAll(map);
        return this;
    }

    public Parameter put(Parameter parameter) {
        map.putAll(parameter.map);
        return this;
    }

    public boolean contains(String key) {
        return map.containsKey(key);
    }

    public Parameter method(String method) {
        this.method = method;
        return this;
    }

    public String method() {
        return method;
    }

    public final Map<String, String> map() {
        return map;
    }

    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        return map.entrySet().iterator();
    }

    public URL url() throws MalformedURLException {
        return new URL(API_V3 + compile());
    }

    public URI uri() throws URISyntaxException {
        return new URI(API_V3 + compile());
    }

    public <T> T result(Class<T> tClass) throws IOException {
        return result(url(), tClass);
    }

    public String compile() {
        if (method == null) throw new RuntimeException("This Parameter object has not method.");

        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, String> entry : this) {
            builder.append('&').append(entry.getKey()).append('=')
                    .append(entry.getValue());
        }

        return method + '?' + builder.toString().replaceFirst("&", "");
    }
}