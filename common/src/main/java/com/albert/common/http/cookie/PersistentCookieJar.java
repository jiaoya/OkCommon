package com.albert.common.http.cookie;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import static java.util.logging.Level.WARNING;
import static okhttp3.internal.Util.delimiterOffset;
import static okhttp3.internal.Util.trimSubstring;

import com.albert.okutils.LogUtils;

/**
 * <pre>
 *      Copyright    : Copyright (c) 2019.
 *      Author       : jiaoya.
 *      Created Time : 2019-05-10.
 *      Desc         : 老系统一般会在 responce的header里设置cookie，这时就可以自动持久化
 * </pre>
 */
public class PersistentCookieJar implements CookieJar {

    private final CookieHandler cookieHandler;
    private static final Logger logger = Logger.getLogger(PersistentCookieJar.class.getName());

    public PersistentCookieJar(CookieHandler cookieHandler) {
        this.cookieHandler = cookieHandler;
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookieHandler != null) {
            List<String> cookieStrings = new ArrayList<>();
            for (Cookie cookie : cookies) {
                cookieStrings.add(cookie.toString());
            }
            Map<String, List<String>> multimap = Collections.singletonMap("Set-Cookie", cookieStrings);
            try {
                cookieHandler.put(url.uri(), multimap);
            } catch (IOException e) {
                logger.log(WARNING, "Saving cookies failed for " + url.resolve("/..."), e);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        // The RI passes all headers. We don't have 'em, so we don't pass 'em!
        Map<String, List<String>> headers = Collections.emptyMap();
        Map<String, List<String>> cookieHeaders;
        try {
            cookieHeaders = cookieHandler.get(url.uri(), headers);
        } catch (IOException e) {
            logger.log(WARNING, "Loading cookies failed for " + url.resolve("/..."), e);
            return Collections.emptyList();
        }

        List<Cookie> cookies = null;
        for (Map.Entry<String, List<String>> entry : cookieHeaders.entrySet()) {
            String key = entry.getKey();
            if (("Cookie".equalsIgnoreCase(key) || "Cookie2".equalsIgnoreCase(key)) && !entry.getValue().isEmpty()) {
                for (String header : entry.getValue()) {
                    if (cookies == null) cookies = new ArrayList<>();
                    cookies.addAll(decodeHeaderAsJavaNetCookies(url, header));
                }
            }
        }

        return cookies != null ? Collections.unmodifiableList(cookies) : Collections.<Cookie>emptyList();
    }


    /**
     * Convert a request header to OkHttp's cookies via {@link HttpCookie}. That extra step handles
     * multiple cookies in a single request header, which {@link Cookie#parse} doesn't support.
     */
    private List<Cookie> decodeHeaderAsJavaNetCookies(HttpUrl url, String header) {
        List<Cookie> result = new ArrayList<>();
        try {
            for (int pos = 0, limit = header.length(), pairEnd; pos < limit; pos = pairEnd + 1) {
                pairEnd = delimiterOffset(";,", header, pos, limit);
                int equalsSign = delimiterOffset("=", header, pos, pairEnd);
                String name = trimSubstring(header, pos, equalsSign);
                if (name.startsWith("$")) continue;

                // We have either name=value or just a name.
                String value = equalsSign < pairEnd
                        ? trimSubstring(header, equalsSign + 1, pairEnd)
                        : "";

                // If the value is "quoted", drop the quotes.
                if (value.startsWith("\"") && value.endsWith("\"")) {
                    value = value.substring(1, value.length() - 1);
                }

                result.add(new Cookie.Builder()
                        .name(name)
                        .value(value)
                        .domain(url.host())
                        .build());
            }
        } catch (Exception e) {
            LogUtils.e("PersistentCookieJar：", e.getMessage());
        }
        return result;
    }

}
