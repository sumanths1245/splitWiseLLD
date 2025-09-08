import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SplitwiseFacade splitwise = new SplitwiseFacade();

        User u1 = splitwise.createUser("u1", "Alice");
        User u2 = splitwise.createUser("u2", "Bob");
        User u3 = splitwise.createUser("u3", "Charlie");

        Group tripGroup = splitwise.createGroup("Trip", "Goa trip", List.of(u1, u2, u3));

        splitwise.addExpense(tripGroup, ExpenseType.EQUAL_SPLIT, u2, 300.0,
                List.of(new EqualSplit(u1), new EqualSplit(u2), new EqualSplit(u3)));

        // 🔽 Add Exact Expense
        splitwise.addExpense(tripGroup, ExpenseType.EXACT_SPLIT, u2, 200.00,
                Arrays.asList(
                        new ExactSplit(u1, 100.00),
                        new ExactSplit(u2, 50.00),
                        new ExactSplit(u3, 50.00)
                )
        );

        // 🔽 Add Percent Expense
        splitwise.addExpense(tripGroup, ExpenseType.PERCENT_SPLIT, u2, 500.00,
                Arrays.asList(
                        new PercentSplit(40.00, u1),
                        new PercentSplit(30.00, u2),
                        new PercentSplit(30.00, u3)
                )
        );

        splitwise.showBalances(tripGroup);
        splitwise.minimize(tripGroup);
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