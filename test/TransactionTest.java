import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author professorik
 * @created 20/02/2022 - 13:42
 * @project DL-labs
 */
class TransactionTest {
    @Test
    void testId(){
        ArrayList<Operation> operationList = new ArrayList<>();
        String[] transactions = new String[5];
        for (int i = 0; i < transactions.length; i++) {
            Transaction tmp = Transaction.createTransaction(operationList, i);
            transactions[i] = tmp.toString();
            tmp.print();
        }
        for (int i = 0; i < transactions.length; i++) {
            for (int j = i + 1; j < transactions.length; j++) {
                assertNotEquals(transactions[i], transactions[j]);
            }
        }
    }
}
