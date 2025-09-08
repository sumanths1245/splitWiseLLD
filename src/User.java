public class User implements ExpenseObserver{
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    @Override
    public void notify(Expense expense) {
        System.out.println("🔔 Notifying " + getName() + ": New expense of " + expense.getAmt() +
                " added by " + expense.getPaidBy().getName());
    }
}
