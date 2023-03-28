
// Timer.java

package io.betsAPIModels;

import com.fasterxml.jackson.annotation.*;

public class Timer {
    private long tm;
    private long ts;
    private String tt;
    private long ta;
    private long md;

    @JsonProperty("tm")
    public long getTm() { return tm; }
    @JsonProperty("tm")
    public void setTm(long value) { this.tm = value; }

    @JsonProperty("ts")
    public long getTs() { return ts; }
    @JsonProperty("ts")
    public void setTs(long value) { this.ts = value; }

    @JsonProperty("tt")
    public String getTt() { return tt; }
    @JsonProperty("tt")
    public void setTt(String value) { this.tt = value; }

    @JsonProperty("ta")
    public long getTa() { return ta; }
    @JsonProperty("ta")
    public void setTa(long value) { this.ta = value; }

    @JsonProperty("md")
    public long getMd() { return md; }
    @JsonProperty("md")
    public void setMd(long value) { this.md = value; }
}