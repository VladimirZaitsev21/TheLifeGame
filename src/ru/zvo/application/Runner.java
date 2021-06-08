package ru.zvo.application;

import ru.zvo.game.FiguresTypes;
import ru.zvo.game.LifeBoard;

import java.util.Arrays;
import java.util.Locale;

public class Runner {

    public static final int GENERATIONS_AMOUNT = 20;
    public static final String GENERATION_MESSAGE = "Generation #";
    public static final String START_ARRAY_MESSAGE = "Initial array:";
    public static final int ROWS_AMOUNT = 10;
    public static final int COLUMNS_AMOUNT = 10;

    public static void main(String[] args) throws InterruptedException {
        FiguresTypes currentFigureType;
        if (args.length != 0) {
            currentFigureType = FiguresTypes.valueOf(args[0].toUpperCase(Locale.ROOT));
        } else {
            currentFigureType = FiguresTypes.RANDOM_FIELD;
        }
        LifeBoard lifeBoard = new LifeBoard(ROWS_AMOUNT, COLUMNS_AMOUNT, currentFigureType);
        int[][] array = lifeBoard.getCurrentGeneration();
        System.out.println(START_ARRAY_MESSAGE);
        printGeneration(array);
        for (int i = 0; i < GENERATIONS_AMOUNT; i++) {
            array = lifeBoard.getNextGeneration();
            System.out.println(GENERATION_MESSAGE + (i + 1));
            printGeneration(array);
            Thread.sleep(1000);
            if (i != GENERATIONS_AMOUNT - 1) {
                cls();
            }
        }
    }

    private static void printGeneration(int[][] array) {
        Arrays.stream(array).forEach(x -> System.out.println(Arrays.toString(x)));
    }

    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
