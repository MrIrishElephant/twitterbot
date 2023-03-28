package com.function;

import java.util.Optional;
import java.util.logging.Level;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import ibcs.ia.FootballScoresBotUI;

/**
 * Azure Functions with HTTP Trigger.
 */
public class TweetGeneratorUI {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */

    @FunctionName("TweetGeneratorUI")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

            try {
                FootballScoresBotUI ui = new FootballScoresBotUI();
                ui.setPremierLeagueSelected(true);
                ui.setLaLigaSelected(true);
                ui.setSerieASelected(true);
                ui.setServiceStatus("enabled");
                ui.setTweetId("");
               

                switch (request.getHttpMethod()) {
                    case GET:
                        String html = ui.generateHTML();
                        return request.createResponseBuilder(HttpStatus.OK)
                                .header("Content-Type", "text/html")
                                .body(html)
                                .build();
                    case POST:
                        String formBody = request.getBody().orElse("");
                        ui.handleFormSubmission(formBody);
                        String updatedHtml = ui.generateHTML();
                        return request.createResponseBuilder(HttpStatus.OK)
                                .header("Content-Type", "text/html")
                                .body(updatedHtml)
                                .build();
                    default:
                        return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Invalid request method.").build();
                }
            } catch (Exception e) {
                context.getLogger().log(Level.SEVERE, "An error occurred: ", e);
                e.printStackTrace();
                return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.").build();
            }
        }    
    }
    
