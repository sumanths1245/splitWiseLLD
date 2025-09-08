import java.util.List;

public class ExpenseManager {
    public static Expense createExpense(ExpenseType expenseType, User paidBy, double amount, List<Split> splits) {
        switch (expenseType) {
            case EQUAL_SPLIT -> {
                int totalSplits = splits.size();
                double splitAmount = ((double) amount/totalSplits);
                for(Split split : splits) {
                    split.setAmount(splitAmount);
                }
                return new EqualExpense(paidBy, amount, splits);
            }
            case EXACT_SPLIT -> {
                return new ExactExpense(paidBy, amount, splits);
            }
            case PERCENT_SPLIT -> {
                for (Split split : splits) {
                    PercentSplit percentSplit = (PercentSplit) split;
                    percentSplit.setAmount((amount * percentSplit.getPercent()) / 100);
                }
                return new PercentExpense(paidBy, amount, splits);
            }
            default -> {
                return null;
            }
        }
    }
}
