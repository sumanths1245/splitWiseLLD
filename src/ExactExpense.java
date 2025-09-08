import java.util.List;

public class ExactExpense extends Expense{
    public ExactExpense(User paidBy, Double amount, List<Split> splits) {
        super(paidBy, amount, splits);
    }
    @Override
    public boolean validate() {
        List<Split> splits = getSplits();
        double amount = getAmt();
        double curAmt = 0;
        for (Split split : splits) {
            if (!((split) instanceof ExactSplit)) {
                return false;
            }
            ExactSplit exactSplit = (ExactSplit) split;
            curAmt += exactSplit.getAmt();
        }
        if (curAmt != amount) {
            return false;
        }
        return true;
    }
}
