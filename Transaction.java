import helper.SHA1;

import java.util.ArrayList;

/**
 * @author professorik
 * @created 20/02/2022 - 12:29
 * @project DL-labs
 */
public class Transaction {
    //TODO: add timestamp (necessary), add a function for transaction verification (timerange + id)
    //TODO: think about checking of the t dublication in the future

    private String transactionId; // Если делаешь приватным, добавь метод для получения идентификатора
    private ArrayList<Operation> setOfOperations; // То же самое
    private int nonce;

    private Transaction(String transactionId, ArrayList<Operation> setOfOperations, int nonce) {
        this.transactionId = transactionId;
        this.setOfOperations = setOfOperations;
        this.nonce = nonce;
    }

    public static Transaction createTransaction(ArrayList<Operation> setOfOperations, int nonce){
        String id = SHA1.getSHA1Hash(setOfOperations.toString() + nonce);
        return new Transaction(id, setOfOperations, nonce);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Transaction{");
        sb.append("transactionId='").append(transactionId).append('\'');
        sb.append(", setOfOperations=").append(setOfOperations);
        sb.append(", nonce=").append(nonce);
        sb.append('}');
        return sb.toString();
    }

    public void print(){
        System.out.println(this);
    }
}
