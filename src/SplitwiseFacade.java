import java.util.List;

public class SplitwiseFacade {

    private final UserManager userManager = UserManager.getInstance();
    private final GroupManager groupManager = GroupManager.getInstance();

    public User createUser(String id, String name) {
        User user = new User(id, name);
        userManager.addUser(user);
        return user;
    }

    public Group createGroup(String name, String desc, List<User> members) {
        Group group = new Group(name, desc);
        for (User user : members) group.addMember(user);
        groupManager.addGroup(group);
        return group;
    }

    public void addExpense(Group group, ExpenseType type, User paidBy, double amount, List<Split> splits) {
        Expense expense = ExpenseManager.createExpense(type, paidBy, amount, splits);
        group.addExpense(expense);
    }

    public void showBalances(Group group) {
        group.showBalances();
    }

    public void minimize(Group group) {
        TransactionSimplifier.minimizeTransactions(group.getBalances());
    }
}
