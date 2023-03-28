package com.function;

import java.util.Optional;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import ibcs.ia.SaveDB;

public class SaveDbFixtures {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     * @throws Exception
     */
    @FunctionName("SaveDbFixtures")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) throws Exception {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter
        final String query = request.getQueryParameters().get("trigger");
        final String processBetsAPI = request.getBody().orElse(query);

        if (processBetsAPI == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass the proper trigger").build();
        } else {

            // if triggered go to BetsAPI and save all fixtures to DB
            SaveDB saveDB = new SaveDB();

            try {
                saveDB.SaveToAzureDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
            StringBuilder html = new StringBuilder();
            return request.createResponseBuilder(HttpStatus.OK).header("Content-Type", "text/html").body(html).build();
        }
    }
}
