import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TransactionSimplifier {
    static class UserBalance {
        User user;
        Double balance;
        public UserBalance(User user, Double balance) {
            this.user = user;
            this.balance = balance;
        }
    }

    public static PriorityQueue<UserBalance> minimizeTransactions(Map<User, Map<User, Double>> balances) {
        Map<User, Double> net = new HashMap<>();
        for (Map.Entry<User, Map<User, Double>> balance : balances.entrySet()) {
            User debtor = balance.getKey();
            for (Map.Entry<User, Double> entry : balance.getValue().entrySet()) {
                User creditor = entry.getKey();
                Double amount = entry.getValue();
                net.put(debtor, net.getOrDefault(debtor, 0.0) - amount);
                net.put(creditor, net.getOrDefault(creditor, 0.0) + amount);
            }
        }

        PriorityQueue<UserBalance> debtors = new PriorityQueue<>(Comparator.comparingDouble(a -> a.balance));
        PriorityQueue<UserBalance> creditors = new PriorityQueue<>((a,b) -> Double.compare(b.balance, a.balance));
        for (Map.Entry<User, Double> entry : net.entrySet()) {
            double bal = entry.getValue();
            if (Math.abs(bal) < 0.001) continue;
            if(bal > 0) {
                creditors.add(new UserBalance(entry.getKey(), bal));
            } else {
                creditors.add(new UserBalance(entry.getKey(), bal));
            }
        }
        while(!debtors.isEmpty() && !creditors.isEmpty()) {
            UserBalance debtor = debtors.poll();
            UserBalance creditor = creditors.poll();

            Double settledBalance = Math.min(-debtor.balance, creditor.balance);
            debtor.balance += settledBalance;
            creditor.balance -= settledBalance;

            if (Math.abs(debtor.balance) > 0.001) debtors.add(debtor);
            if (Math.abs(creditor.balance) > 0.001) creditors.add(creditor);
        }
        return debtors.isEmpty() ? creditors : debtors;
    }
}
