package roomescape.dto;

public class TimeCreateDto {
    private String startAt;

    public TimeCreateDto() {
    }

    public TimeCreateDto(String startAt) {
        this.startAt = startAt;
    }

    public String getStartAt() {
        return startAt;
    }
}
