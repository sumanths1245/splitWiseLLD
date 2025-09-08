public class PercentSplit extends Split{
    private double percent;
    public PercentSplit(double percent, User user) {
        super(user);
        this.percent = percent;
    }
    public double getPercent() {
        return this.percent;
    }
}
