import java.util.*;

public class Group {
    private String name;
    private String desc;
    private List<User> members;
    private List<Expense> expenses;
    private Map<User, Map<User, Double>> balances;

    public Group(String name, String desc) {
        this.desc = desc;
        this.expenses = new ArrayList<>();
        this.members = new ArrayList<>();
        this.name = name;
        this.balances = new HashMap<>();
    }

    public void addMember(User user) {
        members.add(user);
        balances.put(user, new HashMap<>());
    }

    public Map<User, Map<User, Double>> getBalances() {
        return this.balances;
    }
    public void addExpense(Expense expense) {
        if (expense.validate()) {
            expenses.add(expense);
            updateBalances(expense);
            notifyMembers(expense);
        } else {
            System.out.println("Invalid expense");
        }
    }
    private void updateBalances(Expense expense) {
        User paidBy = expense.getPaidBy();

        for (Split split : expense.getSplits()) {
            User debtor = split.getPaidTo(); // or split.getUser()
            if (paidBy.equals(debtor)) continue;

            double amount = split.getAmount();

            balances.putIfAbsent(debtor, new HashMap<>());
            Map<User, Double> debtorMap = balances.get(debtor);
            debtorMap.put(paidBy, debtorMap.getOrDefault(paidBy, 0.0) + amount);
        }
    }


    private void notifyMembers(Expense expense) {
        for (User user : members) {
            if (user.equals(expense.getPaidBy())) continue;
            user.notify(expense);
        }
    }

    public void showBalances() {
        System.out.println("\n💰 Current Balances:");
        Set<String> printed = new HashSet<>();

        for (User u1 : balances.keySet()) {
            for (Map.Entry<User, Double> entry : balances.get(u1).entrySet()) {
                User u2 = entry.getKey();
                double amount = entry.getValue();

                String key = u1.getId() + ":" + u2.getId();
                String reverseKey = u2.getId() + ":" + u1.getId();

                if (amount > 0 && !printed.contains(reverseKey)) {
                    System.out.println(u1.getName() + " owes " + u2.getName() + ": ₹" + String.format("%.2f", amount));
                    printed.add(key);
                }
            }
        }
    }


}
