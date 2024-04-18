package roomescape.domain;

public class Time {
    private long id;
    private String startAt;

    public Time(long id, String startAt) {
        this.id = id;
        this.startAt = startAt;
    }

    public long getId() {
        return id;
    }

    public String getStartAt() {
        return startAt;
    }
}
