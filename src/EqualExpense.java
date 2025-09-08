import java.util.List;

public class EqualExpense extends Expense{

    public EqualExpense(User paidBy, Double amount, List<Split> splits) {
        super(paidBy, amount, splits);
    }
    @Override
    public boolean validate() {
        List<Split> splits = getSplits();
        for (Split split : splits) {
            if (!((split) instanceof EqualSplit)) {
                return false;
            }
        }
        return true;
    }
}
