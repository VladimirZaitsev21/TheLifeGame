package ru.zvo.game;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class LifeBoard {

    private static final int[][] DIRECTIONS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    private int[][] currentGeneration;
    private final Random random;

    public LifeBoard(int rows, int columns) throws NoSuchAlgorithmException {
        random = SecureRandom.getInstanceStrong();
        currentGeneration = new int[rows][columns];
        this.fillPlayField();
    }

    public int[][] getCurrentGeneration() {
        return currentGeneration;
    }

    private void fillPlayField() {
        for (int i = 0; i < currentGeneration.length; i++) {
            for (int j = 0; j < currentGeneration[i].length; j++) {
                currentGeneration[i][j] = random.nextInt(2);
            }
        }
    }

    public int[][] getNextGeneration() {
        int[][] nextGeneration = new int[currentGeneration.length][currentGeneration[0].length];
        for (int i = 0; i < currentGeneration.length; i++) {
            for (int j = 0; j < currentGeneration[i].length; j++) {
                nextGeneration[i][j] = defineNextGenerationCellValue(currentGeneration[i][j], countAliveNeighbors(i, j));
            }
        }
        currentGeneration = nextGeneration;
        return nextGeneration;
    }

    private int defineNextGenerationCellValue(int cell, int aliveNeighborsAmount) {
        if (cell == 1) {
            return defineAliveCellNextState(aliveNeighborsAmount);
        } else {
            return  defineDeadCellNextState(aliveNeighborsAmount);
        }
    }

    private int defineAliveCellNextState(int aliveNeighborsAmount) {
        if ((aliveNeighborsAmount < 2) || (aliveNeighborsAmount > 3)) {
            return 0;
        } else {
            return 1;
        }
    }

    private int defineDeadCellNextState(int aliveNeighborsAmount) {
        if (aliveNeighborsAmount == 3) {
            return 1;
        } else {
            return 0;
        }
    }

    private int countAliveNeighbors(int i, int j){
        int aliveNeighborsAmount = 0;
        for (int[] direction: DIRECTIONS) {
            int x = i + direction[0];
            int y = j + direction[1];
            if (x >= 0 && y >= 0 && x < currentGeneration.length && y < currentGeneration[0].length) {
                aliveNeighborsAmount += currentGeneration[x][y];
            }
        }
        return aliveNeighborsAmount;
    }
}
