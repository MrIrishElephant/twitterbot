package com.function;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import ibcs.ia.AzureDbConnection;
import ibcs.ia.FixtureModel;

/**
 * Azure Functions with HTTP Trigger.
 */
public class GetDbFixtures {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     * @throws SQLException
     */
    @FunctionName("GetDbFixtures")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) throws SQLException {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter
        final String query = request.getQueryParameters().get("trigger");
        final String processBetsAPI = request.getBody().orElse(query);


        
        if (processBetsAPI == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass the proper trigger").build();
        } else {

            AzureDbConnection azureDb = new AzureDbConnection();

            List<FixtureModel> fixtures = azureDb.getAllFixtures();

            StringBuilder html = new StringBuilder();
            html.append("<table>");
            html.append("<tr>");
            html.append("<th>Fixture ID</th>");
            html.append("<th>Fixture Name</th>");
            html.append("<th>Home Team</th>");
            html.append("<th>Away Team</th>");
            html.append("<th>Fixture Date</th>");
            html.append("<th>League ID</th>");
            html.append("</tr>");

            for (FixtureModel fixture : fixtures) {
                html.append("<tr>");
                html.append("<td>" + fixture.getFixtureId() + "</td>");
                html.append("<td>" + fixture.getFixtureName() + "</td>");
                html.append("<td>" + fixture.getHomeTeam() + "</td>");
                html.append("<td>" + fixture.getAwayTeam() + "</td>");
                html.append("<td>" + fixture.getFixtureDate() + "</td>");
                html.append("<td>" + fixture.getLeagueId() + "</td>");
                html.append("</tr>");
            }

            html.append("</table>");

            return request.createResponseBuilder(HttpStatus.OK).header("Content-Type", "text/html").body(html).build();
        }
    }
}
