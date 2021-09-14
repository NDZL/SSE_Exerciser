package com.ndzl;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;

public class SSEConsumer {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        System.out.println("Hey Nik!");




        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                //.followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(3600))
                //.authenticator(Authenticator.getDefault())
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                //.uri(URI.create("https://stream.wikimedia.org/v2/stream/recentchange"))//test url
                .uri(URI.create("https://cxnt48.com/sserelay"))
                .timeout(Duration.ofMinutes(2))
                //.header("Content-Type", "application/json")
                .GET()
                .build();

        var lines = client.send(request, HttpResponse.BodyHandlers.ofLines()).body();
        lines.forEach(s->System.out.println(s));

    }
}


/*
* REAL EXAMPLE OF SSE STREAM
*

:ok

event: message
id: [{"topic":"eqiad.mediawiki.recentchange","partition":0,"timestamp":1631630383001},{"topic":"codfw.mediawiki.recentchange","partition":0,"offset":-1}]
data: {"$schema":"/mediawiki/recentchange/1.0.0","meta":{"uri":"https://ko.wikipedia.org/wiki/%EC%82%AC%EC%9A%A9%EC%9E%90:%E5%BB%A2%E5%90%8E","request_id":"1bf9c720-3507-4577-9afc-8fb068a7edba","id":"8b6fa827-24eb-4dcb-99b6-72074421aeb3","dt":"2021-09-14T14:39:43Z","domain":"ko.wikipedia.org","stream":"mediawiki.recentchange","topic":"eqiad.mediawiki.recentchange","partition":0,"offset":3297754827},"id":57282722,"type":"edit","namespace":2,"title":"사용자:廢后","comment":"","timestamp":1631630383,"user":"廢后","bot":false,"minor":false,"length":{"old":60097,"new":60097},"revision":{"old":30184987,"new":30281462},"server_url":"https://ko.wikipedia.org","server_name":"ko.wikipedia.org","server_script_path":"/w","wiki":"kowiki","parsedcomment":""}

event: message
id: [{"topic":"
*
* */