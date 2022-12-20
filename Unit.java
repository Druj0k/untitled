abstract class Unit {
    int meleeAttack;
    int rangeAttack;
    int maxHp;
    int currentHp;
    boolean haveSword = false;
    boolean haveBow = false;

    public void setSword() {
        if (haveBow || haveSword ){
            System.out.println("У воина уже есть оружие!");
        }
        else {
            haveSword = true;
            meleeAttack += 2;
        }
    }
    public void removeSword() {
        if(haveSword){
            haveSword = false;
            meleeAttack -= 2;
        } else{
            System.out.println("У воина итак нет меча!");
        }
    }
}

class Knight extends Unit {
    Knight() { meleeAttack = 6; maxHp = 8; currentHp = 8;}
}

class Archer extends Unit {
    Archer() {
        meleeAttack = 2;
        rangeAttack = 4;
        maxHp = 6;
        currentHp = 6;
    }

    void setBow() {
        if (haveBow || haveSword) {
            System.out.println("У воина уже есть оружие!");
        } else {
            haveBow = true;
            rangeAttack += 2;
        }
    }

    public void removeBow() {
        if (haveBow) {
            haveBow = false;
            rangeAttack -= 2;
        }
    }
}