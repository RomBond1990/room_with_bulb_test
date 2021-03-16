package com.rbondarovich.utils;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;

import static com.rbondarovich.utils.HttpHeader.*;


public class LocationFinder {

    private static String UNKNOWN = "unknown";
    private static String IP_DB = "mainapp/src/main/resources/IP_DB.mmdb";

    public static String getRemoteIpFrom(HttpServletRequest request) {
        String ip = null;
        int tryCount = 1;

        while (!isIpFound(ip) && tryCount <= 6) {
            switch (tryCount) {
                case 1:
                    ip = request.getHeader(X_FORWARDED_FOR.key());
                    break;
                case 2:
                    ip = request.getHeader(PROXY_CLIENT_IP.key());
                    break;
                case 3:
                    ip = request.getHeader(WL_PROXY_CLIENT_IP.key());
                    break;
                case 4:
                    ip = request.getHeader(HTTP_CLIENT_IP.key());
                    break;
                case 5:
                    ip = request.getHeader(HTTP_X_FORWARDED_FOR.key());
                    break;
                default:
                    ip = request.getRemoteAddr();
            }

            tryCount++;
        }

        return ip;
    }

    private static boolean isIpFound(String ip) {
        return ip != null && ip.length() > 0 && !UNKNOWN.equalsIgnoreCase(ip);
    }

    public static Map<String, String> getRequestHeadersInMap(HttpServletRequest request) {

        Map<String, String> result = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            result.put(key, value);
        }

        return result;
    }

    public static Set<String> getCountryByIp(String ip) throws IOException, GeoIp2Exception {
        Set<String> countryNames = new HashSet<>();

        File database = new File(IP_DB);
        DatabaseReader dbReader = new DatabaseReader.Builder(database).build();

        InetAddress ipAddress = InetAddress.getByName(ip);
        CountryResponse response = dbReader.country(ipAddress);
        Country country = response.getCountry();

        countryNames.add(country.getName());
        countryNames.add(country.getNames().get("ru"));

        return countryNames;
    }
}
