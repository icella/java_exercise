package exercise.com.leo.third.redis.script;

public enum Rate {

    SECOND(0),
    MINUTE(1),
    HOUR(2),
    DAY(3);

    private Integer unit;

    Rate(Integer unit) {
        this.unit = unit;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public static Rate stateOf(Integer unit) {
        for (Rate rate : values()) {
            if (unit.equals(rate.getUnit())) {
                return rate;
            }
        }
        return null;
    }
}
