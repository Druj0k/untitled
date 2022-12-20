import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;

public class Army {
    private int armySize;
    private String name;
    ArrayList<Unit> soldiers;

    Army(String name, int armySize) {
        this.name = name;
        this.armySize = armySize;
        setArmySize(armySize);
    }

    public void setArmySize(int armySize) {
        this.armySize = armySize;
        if (armySize > 0) {
            soldiers = new ArrayList<>(0);
            int numberOfKnights = (int) Math.round(armySize * Math.random());
            do {
                soldiers.add(new Knight());
            }
            while (soldiers.size() < numberOfKnights);

            while (soldiers.size() < armySize) {
                soldiers.add(new Archer());
            }
        } else {
            System.out.println("Задан недопустимый размер армии!");
        }
    }

    public int getArmySize() {
        return armySize;
    }

    private int sumArmyMeleeAttack() {
        int totalMeleeAttack = 0;
        for (Unit u : soldiers) {
            totalMeleeAttack = totalMeleeAttack + u.meleeAttack;
        }
        return totalMeleeAttack;
    }

    private int sumArmyRangeAttack() {
        int totalRangeAttack = 0;
        for (Unit u : soldiers) {
            totalRangeAttack = totalRangeAttack + u.rangeAttack;
        }
        return totalRangeAttack;
    }

    private int sumArmyHP() {
        int totalHP = 0;
        for (Unit u : soldiers) {
            totalHP = totalHP + u.currentHp;
        }
        return totalHP;
    }

    private void checkAlive(Army x) {
        //Iterator<Unit> unitIterator = soldiers.iterator();
        //wile(unitIterator.hasNext()) {
        //  Unit nextUnit = unitIterator.next();
        // if (nextUnit.currentHp <= 0) {
        //     unitIterator.remove();
        //
        // }
        ArrayList<Unit> casualty = new ArrayList<>();
        for (Unit u : x.soldiers) {
            if (u.currentHp <= 0) {
                casualty.add(u);
            }
        }
        x.soldiers.removeAll(casualty);
        x.armySize = x.soldiers.size();
        System.out.println("Потери " + x.name + " составили " + casualty.size() + " солдат");
    }

    private void setRangeDamage(Army a) {
        double extraAreaRangeDamage = (0.5 * sumArmyRangeAttack()) % a.armySize;
        if (0.5 * sumArmyRangeAttack() < a.armySize) {
            for (int i = 0; i <= 0.5 * sumArmyRangeAttack() - 1; i++) {
                a.soldiers.get(i).currentHp--;
            }
        } else {
            for (Unit u : a.soldiers) {
                u.currentHp = u.currentHp - (int) (0.5 * sumArmyRangeAttack() / a.armySize);
            }
            for (int i = 0; i <= extraAreaRangeDamage - 1; i++) {
                a.soldiers.get(i).currentHp--;
            }
        }
        a.soldiers.get(0).currentHp = a.soldiers.get(0).currentHp - (int) (0.5 * sumArmyRangeAttack());
    }

    private void setMeleeDamage(Army a) {
        double extraAreaMeleeDamage = (0.5 * sumArmyMeleeAttack()) % a.armySize;
        if (0.5 * sumArmyMeleeAttack() < a.armySize) {
            for (int i = 0; i <= 0.5 * sumArmyMeleeAttack() - 1; i++) {
                a.soldiers.get(i).currentHp--;
            }
        } else {
            for (Unit u : a.soldiers) {
                u.currentHp = u.currentHp - (int) (int) (0.5 * sumArmyMeleeAttack() / a.armySize);
            }
            for (int i = 0; i <= extraAreaMeleeDamage - 1; i++) {
                a.soldiers.get(i).currentHp--;
            }
        }
        a.soldiers.get(0).currentHp = a.soldiers.get(0).currentHp - (int) (0.5 * sumArmyMeleeAttack());
    }

    public void fight(Army x, Army y) {
        while (x.armySize > 0 & y.armySize > 0) {
            y.setRangeDamage(x);
            x.setRangeDamage(y);
            checkAlive(x);
            checkAlive(y);
            if (x.armySize > 0 & y.armySize > 0) {
                y.setMeleeDamage(x);
                x.setMeleeDamage(y);
                checkAlive(x);
                checkAlive(y);
            }
        }
        if (x.armySize > y.armySize) {
            System.out.println("Победила армия " + x.name + "!");
            System.out.println("Список выживших: ");
            x.info();
        } else if (y.armySize > x.armySize) {
            System.out.println("Победила армия " + y.name + "!");
            System.out.println("Список выживших: ");
            y.info();
        }
        else System.out.println("никто wons");
    }


    public void info(){
        for (Unit u : soldiers) {
                System.out.println(u);
                System.out.println(u.currentHp);
        }
    }
}




