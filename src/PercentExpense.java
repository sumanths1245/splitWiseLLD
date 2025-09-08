import java.util.List;

public class PercentExpense extends Expense {
    public PercentExpense(User paidBy, Double amount, List<Split> splits) {
        super(paidBy, amount, splits);
    }
    @Override
    public boolean validate() {
        List<Split> splits = getSplits();
        double totalPerc = 100;
        double curPerc = 0;
        for(Split split : splits) {
            if(!((split) instanceof PercentSplit)) {
                return false;
            }
            PercentSplit exactSplit = (PercentSplit) split;
            curPerc += exactSplit.getPercent();
        }
        if (totalPerc == curPerc) {
            return true;
        }
        return false;
    }
}
