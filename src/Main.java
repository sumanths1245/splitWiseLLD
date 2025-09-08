import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = UserManager.getInstance();
        User u1 = new User("u1", "Alice");
        User u2 = new User("u2", "Bob");
        User u3 = new User("u3", "Charlie");

        userManager.addUser(u1);
        userManager.addUser(u2);
        userManager.addUser(u3);

        Group group = new Group("Trip", "Goa trip");

        group.addMember(u1);
        group.addMember(u2);
        group.addMember(u3);

        GroupManager.getInstance().addGroup(group);

        // 🔽 Add Equal Expense
        List<Split> equalSplits = Arrays.asList(
                new EqualSplit(u1),
                new EqualSplit(u2),
                new EqualSplit(u3)
        );

        Expense e1 = ExpenseManager.createExpense(ExpenseType.EQUAL_SPLIT, u2, 300.00, equalSplits);
        group.addExpense(e1);

        // 🔽 Add Exact Expense
        List<Split> exactSplits = Arrays.asList(
                new ExactSplit(u1, 100.00),
                new ExactSplit(u2, 50.00),
                new ExactSplit(u3, 50.00)
        );

        Expense e2 = ExpenseManager.createExpense(ExpenseType.EXACT_SPLIT, u2, 200.00, exactSplits);
        group.addExpense(e2);

        // 🔽 Add Percent Expense
        List<Split> percentSplits = Arrays.asList(
                new PercentSplit(40.00,u1),
                new PercentSplit(30.00,u2),
                new PercentSplit(30.00,u3)
        );

        Expense e3 = ExpenseManager.createExpense(ExpenseType.PERCENT_SPLIT, u2, 500.00, percentSplits);
        group.addExpense(e3);

        // 🧾 Show Balances
        group.showBalances();
    }
}


//Splitwise
// - User(id, name)
// - Group(list[user], list[expense], name, desc)
// - Expense (paidBy, amount, List[Splits])
// - equalexpense, exactexpense, percentexpense
// - ExpenseManager (balancesheet)
// - Split (paidTo, splitType, amount/%)
// - exactSplit - equalSplit - percentSplit  -> strategy pattern
// - splitType enum
// - UserManager - GroupManager -> Singleton pattern
// - Observer (notify)
// - settlemnetObserver - newExpenseAddedObserver