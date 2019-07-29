import java.util.HashMap;
import java.util.Map;

public class CashRegister {
    private final int TWENTY = 20;
    private final int TEN = 10;
    private final int FIVE = 5;
    private final int TWO = 2;
    private final int ONE = 1;
    private HashMap<Integer,Integer> till;
    private HashMap<Integer,Integer> previousState;

    CashRegister() {
        till = new HashMap<>();
        till.put(TWENTY,0);
        till.put(TEN,0);
        till.put(FIVE,0);
        till.put(TWO,0);
        till.put(ONE,0);
    }

    //save the current state of the till in case invalid data is sent in
    void saveState() {
        previousState = new HashMap<>();
        for(Map.Entry<Integer,Integer> entry : till.entrySet()) {
            previousState.put(entry.getKey(), entry.getValue());
        }
    }

    void restoreState() {
        till = new HashMap<>();
        if(previousState != null) {
            for (Map.Entry<Integer, Integer> entry : previousState.entrySet()) {
                till.put(entry.getKey(), entry.getValue());
            }
        }
    }
    
    private boolean rMakeChange(int numToChange, HashMap<Integer,Integer> change) {
        boolean done = false;

        if(numToChange == 0) {
            return true;
        }
        if(numToChange >= TWENTY && till.get(TWENTY) > 0) {
            numToChange -= TWENTY;
            change.put(TWENTY, change.get(TWENTY) + 1);
            till.put(TWENTY, till.get(TWENTY) - 1);
            done = rMakeChange(numToChange, change);
            if(!done) {
                change.put(TWENTY, change.get(TWENTY) - 1);
                till.put(TWENTY, till.get(TWENTY) + 1);
                numToChange += TWENTY;
            }
        }
        if(!done && numToChange >= TEN && till.get(TEN) > 0) {
            numToChange -= TEN;
            change.put(TEN, change.get(TEN) + 1);
            till.put(TEN, till.get(TEN) - 1);
            done = rMakeChange(numToChange, change);
            if(!done) {
                change.put(TEN, change.get(TEN) - 1);
                till.put(TEN, till.get(TEN) + 1);
                numToChange += TEN;
            }
        }
        if(!done && numToChange >= FIVE && till.get(FIVE) > 0) {
            numToChange -= FIVE;
            change.put(FIVE, change.get(FIVE) + 1);
            till.put(FIVE, till.get(FIVE) - 1);
            done = rMakeChange(numToChange, change);
            if(!done) {
                change.put(FIVE, change.get(FIVE) - 1);
                till.put(FIVE, till.get(FIVE) + 1);
                numToChange += FIVE;
            }
        }
        if(!done && numToChange >= TWO && till.get(TWO) > 0) {
            numToChange -= TWO;
            change.put(TWO, change.get(TWO) + 1);
            till.put(TWO, till.get(TWO) - 1);
            done = rMakeChange(numToChange, change);
            if(!done) {
                change.put(TWO, change.get(TWO) - 1);
                till.put(TWO, till.get(TWO) + 1);
                numToChange += TWO;
            }
        }
        if(!done && numToChange >= ONE && till.get(ONE) > 0) {
            numToChange -= ONE;
            change.put(ONE, change.get(ONE) + 1);
            till.put(ONE, till.get(ONE) - 1);
            done = rMakeChange(numToChange, change);
            if(!done) {
                change.put(ONE, change.get(ONE) - 1);
                till.put(ONE, till.get(ONE) + 1);
                numToChange += ONE;
            }
        }

        return done;
    }

    //take the dollar amount and try to make change with the bills available
    String makeChange(int numToChange) throws Exception {
        HashMap<Integer,Integer> change = new HashMap<>();
        change.put(TWENTY,0);
        change.put(TEN,0);
        change.put(FIVE,0);
        change.put(TWO,0);
        change.put(ONE,0);

        //recursively try to make change from our till
        boolean changeMade = rMakeChange(numToChange, change);

        if(!changeMade) {
            exception();
        }

        return change.get(TWENTY) + " " + change.get(TEN) + " " + change.get(FIVE) + " " + change.get(TWO) + " " +
                change.get(ONE);
    }

    /*******************************************************/
    /***** Add Methods: add number sent in to the till  ****/
    /*******************************************************/
    void addTwenties(int numToAdd) {
        if(numToAdd > 0) {
            till.put(TWENTY, till.get(TWENTY) + numToAdd);
        }
    }

    void addTens(int numToAdd) {
        if(numToAdd > 0) {
            till.put(TEN, till.get(TEN) + numToAdd);
        }
    }

    void addFives(int numToAdd) {
        if(numToAdd > 0) {
            till.put(FIVE, till.get(FIVE) + numToAdd);
        }
    }

    void addTwos(int numToAdd) {
        if(numToAdd > 0) {
            till.put(TWO, till.get(TWO) + numToAdd);
        }
    }

    void addOnes(int numToAdd) {
        if(numToAdd > 0) {
            till.put(ONE, till.get(ONE) + numToAdd);
        }
    }

    /*******************************************************/
    /** Take Methods: remove number sent in from the till **/
    /*******************************************************/
    void takeTwenties(int numToTake) throws Exception {
        if(numToTake > 0 && numToTake <= till.get(TWENTY)) {
            till.put(TWENTY, till.get(TWENTY) - numToTake);
        } else if(numToTake > 0) {
            exception();
        }
    }

    void takeTens(int numToTake) throws Exception {
        if(numToTake > 0 && numToTake <= till.get(TEN)) {
            till.put(TEN, till.get(TEN) - numToTake);
        } else if(numToTake > 0) {
            exception();
        }
    }

    void takeFives(int numToTake) throws Exception {
        if(numToTake > 0 && numToTake <= till.get(FIVE)) {
            till.put(FIVE, till.get(FIVE) - numToTake);
        } else if(numToTake > 0) {
            exception();
        }
    }

    void takeTwos(int numToTake) throws Exception {
        if(numToTake > 0 && numToTake <= till.get(TWO)) {
            till.put(TWO, till.get(TWO) - numToTake);
        } else if(numToTake > 0) {
            exception();
        }
    }

    void takeOnes(int numToTake) throws Exception {
        if(numToTake > 0 && numToTake <= till.get(ONE)) {
            till.put(ONE, till.get(ONE) - numToTake);
        } else if(numToTake > 0) {
            exception();
        }
    }

    private void exception() throws Exception {
        throw new Exception("sorry");
    }

    int getSum() {
        int sum = 0;
        for(Map.Entry<Integer,Integer> entry : till.entrySet()) {
            sum += entry.getKey() * entry.getValue();
        }
        return sum;
    }

    public String toString() {
        return "$" + getSum() + " " + till.get(TWENTY) + ' ' + till.get(TEN) + ' ' +
                till.get(FIVE) + ' ' + till.get(TWO) + ' ' + till.get(ONE);
    }
}
