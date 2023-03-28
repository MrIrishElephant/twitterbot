
// Welcome.java

package io.betsAPIModels;

import com.fasterxml.jackson.annotation.*;

public class BetsAPIEntity {
    private long success;
    private Pager pager;
    private Result[] results;

    @JsonProperty("success")
    public long getSuccess() { return success; }
    @JsonProperty("success")
    public void setSuccess(long value) { this.success = value; }

    @JsonProperty("pager")
    public Pager getPager() { return pager; }
    @JsonProperty("pager")
    public void setPager(Pager value) { this.pager = value; }

    @JsonProperty("results")
    public Result[] getResults() { return results; }
    @JsonProperty("results")
    public void setResults(Result[] value) { this.results = value; }
}
