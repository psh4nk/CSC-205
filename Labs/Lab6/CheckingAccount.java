public class CheckingAccount extends Account {
    private int numChecks = 0;

    public CheckingAccount(double var1) {
        super(var1);
        System.out.println("Beginning balance: " + var1);
        System.out.println();
    }

    public int getChecksWritten() {
        return this.numChecks;
    }

    public void writeCheck(double var1) {
        ++this.numChecks;
        this.withdraw(var1);
    }
}
