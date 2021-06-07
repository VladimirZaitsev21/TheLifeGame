package ru.zvo.application;

import ru.zvo.game.LifeBoard;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Runner {

    public static final int GENERATIONS_AMOUNT = 10;
    public static final String RANDOM_CREATING_EXCEPTION_MESSAGE = "Some problem occurred while creating Random\n";
    public static final String GENERATION_MESSAGE = "Generation #";
    public static final String START_ARRAY_MESSAGE = "Initial array:";
    public static final int ROWS_AMOUNT = 10;
    public static final int COLUMNS_AMOUNT = 10;

    public static void main(String[] args) {
        LifeBoard lifeBoard;
        try {
            lifeBoard = new LifeBoard(ROWS_AMOUNT, COLUMNS_AMOUNT);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(RANDOM_CREATING_EXCEPTION_MESSAGE + e.getMessage());
            return;
        }
        int[][] array = lifeBoard.getCurrentGeneration();
        System.out.println(START_ARRAY_MESSAGE);
        printGeneration(array);
        for (int i = 0; i < GENERATIONS_AMOUNT; i++) {
            array = lifeBoard.getNextGeneration();
            System.out.println(GENERATION_MESSAGE + (i + 1));
            printGeneration(array);
        }
    }

    private static void printGeneration(int[][] array) {
        Arrays.stream(array).forEach(x -> System.out.println(Arrays.toString(x)));
    }

}
