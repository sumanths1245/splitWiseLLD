public class NewExpenseObserver implements ExpenseObserver {

    @Override
    public void notify(Expense expense) {
        System.out.println("🔔 Notifying " + ": New expense of " + expense.getAmt() +
                " added by " + expense.getPaidBy().getName());
    }
}
