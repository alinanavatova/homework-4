package com.company;

import java.util.Random;

public class Main {
    public static String[] heroesAttackType = {"Physical","Magical","Kinetic","Golem","Lucky","Medic"};
    public static int[] heroesHealth = {270, 280, 260, 600, 250, 300};
    public static int[] heroesDamage = {20, 15, 25, 5, 20, 0};

    public static int bossHealth = 800;
    public static int bossDamage = 70;
    public static String bossDefenceType = "";

    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }

    }
    public static void round() {
        roundNumber++;
        chooseDefenceType();
        medicHeal();
        bossHits();
        luckysPower();
        golemsPower();
        heroesHits();
        printStatistics();
    }

    public static Boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!");
            return true;
        }

        boolean allHeroesDead = true;

        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!");
        }
        return allHeroesDead;
    }

    private static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < bossDamage) {
                heroesHealth[i] = 0;
            } else {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
        }

    }

    private static void heroesHits() {
        for (int i = 0; i < heroesHealth.length; i++) {

            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random r = new Random();
                    int coef = r.nextInt(8) + 2;

                    if (bossHealth < heroesDamage[i] + coef) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coef);
                } else {
                    if (bossHealth < heroesDamage[i]) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }

        }


    }

    private static void printStatistics() {
        System.out.println("********* " + roundNumber + " ROUND *********");
        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");

        for (int i = 0; i < heroesHealth.length - 1; i++) {
            System.out.println("Hero " + heroesAttackType[i] +
                    " Health " + heroesHealth[i] +
                    " [" + heroesDamage[i] + "]");
        }

    }

    public static void chooseDefenceType() {
        Random random = new Random();
        int randomIndex = random.nextInt(3);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choose:" + bossDefenceType);
    }

    public static void medicHeal() {
        Random random = new Random();
        int randomIndex = random.nextInt(100)+ 50;

        Random random1 = new Random();
        int randomNum = random1.nextInt(6);

        for (int i = 0; i < 1; i++) {
            i = randomNum;
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0) {
                heroesHealth[i] = heroesHealth[i] + randomIndex;
                System.out.println("Medic heal:" + heroesAttackType[i] + " " + randomIndex);
            } else if (heroesHealth[4] <= 0){
                break;
            }


        }
    }
    public static void golemsPower(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[3] > 0 ){
                heroesHealth[3] = (int) (heroesHealth[3] - heroesHealth.length * (bossDamage * 0.2));
                heroesHealth[i] = (int) (heroesHealth[i] + bossDamage * 0.2);
            }
        }

    }
    public static void luckysPower(){
        Random random = new Random();
        int randoml = random.nextInt(2);
        if (randoml == 0){
            heroesHealth[4] = heroesHealth[4] + bossDamage;
        }
    }
}
