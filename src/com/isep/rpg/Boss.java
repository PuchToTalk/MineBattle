package com.isep.rpg;



public class Boss extends Enemy
{
    public Boss(Builder b){
        this.lifePoints = b.lifePoints;
        this.name = b.name;
        this.damage = b.damage;
    }

    /**
     * @PS : Class Boss
     * Cette classe dispose les attributs qu'elle a obtenu par héritage de sa classe mère (Enemy)
     * idem attributs que BasicEnemy aussi
     *
     * @auteur(s)  (Paul)
     * @version (v.o2 - 22/05/2022)
     * simplification des constructeurs setup Boss
     */



    public static class Builder{

        public int lifePoints;
        public int damage;
        public String name;

        public Builder(int lp){
            this.lifePoints = lp;
        }


        /** Construit Nom + Dégât **/

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setDamage(int damage){
            this.damage = damage;
            return this;
        }

        public Boss build(){
            return new Boss(this);
        }
    }
}
