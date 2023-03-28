
// Pager.java

package io.betsAPIModels;

import com.fasterxml.jackson.annotation.*;

public class Pager {
    private long page;
    private long perPage;
    private long total;

    @JsonProperty("page")
    public long getPage() { return page; }
    @JsonProperty("page")
    public void setPage(long value) { this.page = value; }

    @JsonProperty("per_page")
    public long getPerPage() { return perPage; }
    @JsonProperty("per_page")
    public void setPerPage(long value) { this.perPage = value; }

    @JsonProperty("total")
    public long getTotal() { return total; }
    @JsonProperty("total")
    public void setTotal(long value) { this.total = value; }
}

// Result.java
