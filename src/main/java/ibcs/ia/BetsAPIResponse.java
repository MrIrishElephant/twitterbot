package ibcs.ia;
import java.util.List;

import io.betsAPIModels.Event;

public class BetsAPIResponse {
    private String success;
    private List<Event> results;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Event> getResults() {
        return results;
    }

    public void setResults(List<Event> results) {
        this.results = results;
    }
}
