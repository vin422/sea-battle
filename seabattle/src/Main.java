import java.util.Scanner;
import java.util.Random;
public class Main
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean startNewGame = true;
        while (startNewGame) {
            int[][] field = new int[11][11];  //4 = miss
            //                                  0 = nothing here
            //                                  1 = hit
            //                                  2 = sunk
            //                                  3 = ship is placed there
            int direction = random.nextInt(2); //0  = vertical; 1 = horizontal

            //3 squares
            int shipIndexJ;
            int shipIndexK;
            int shipDirection;
            if (direction == 0) {
                shipDirection = 0;
                int j = random.nextInt(5) + 2;
                int k = random.nextInt(7) + 2;
                shipIndexJ = j - 2;
                shipIndexK = k - 2;
                for (int i = 0; i < 3; i++) {
                    field[j][k] = 3;
                    j++;
                }
            } else {
                shipDirection = 1;
                int j = random.nextInt(7) + 2;
                int k = random.nextInt(5) + 2;
                shipIndexJ = j - 2;
                shipIndexK = k - 2;
                for (int i = 0; i < 3; i++) {
                    field[j][k] = 3;
                    k++;
                }
            }
            int ship1IndexJ = 0;
            int ship1IndexK = 0;
            int ship2IndexJ = 0;
            int ship2IndexK = 0;
            int ship3IndexJ = 0;
            int ship3IndexK = 0;
            int ship4IndexJ = 0;
            int ship4IndexK = 0;
            for (int i = 0; i < 4; i++) { //1 square
                int j = random.nextInt(7) + 2;
                int k = random.nextInt(7) + 2;
                if (field[j - 1][k - 1] != 3 && field[j - 1][k] != 3 && field[j - 1][k + 1] != 3 && field[j][k - 1] != 3 && field[j][k] != 3 && field[j][k + 1] != 3 && field[j + 1][k - 1] != 3 && field[j + 1][k] != 3 && field[j + 1][k + 1] != 3) {
                    field[j][k] = 3;
                    if (i == 0) {
                        ship1IndexJ = j - 2;
                        ship1IndexK = k - 2;
                    } else if (i == 1) {
                        ship2IndexJ = j - 2;
                        ship2IndexK = k - 2;
                    } else if (i == 2) {
                        ship3IndexJ = j - 2;
                        ship3IndexK = k - 2;
                    } else if (i == 3) {
                        ship4IndexJ = j - 2;
                        ship4IndexK = k - 2;
                    }
                } else {
                    i--;
                }
            }
            int TwoShip1IndexJ = 0;
            int TwoShip1IndexK = 0;
            int TwoShip2IndexJ = 0;
            int TwoShip2IndexK = 0;
            int shipDirection1 = 0;
            int shipDirection2 = 0;
            for (int i = 0; i < 2; i++) { //2 squares
                direction = random.nextInt(2);
                if (direction == 0) { // vertical
                    int j = random.nextInt(6) + 2;
                    int k = random.nextInt(7) + 2;
                    if (i == 0) {
                        shipDirection1 = 0;
                        TwoShip1IndexJ = j - 2;
                        TwoShip1IndexK = k - 2;
                    } else {
                        TwoShip2IndexJ = j - 2;
                        TwoShip2IndexK = k - 2;
                        shipDirection2 = 0;
                    }
                    if (field[j - 1][k - 1] != 3 && field[j - 1][k] != 3 && field[j - 1][k + 1] != 3 && field[j][k - 1] != 3 && field[j][k] != 3 && field[j][k + 1] != 3 && field[j + 1][k - 1] != 3 && field[j + 1][k] != 3 && field[j + 1][k + 1] != 3 && field[j + 2][k - 1] != 3 && field[j + 2][k] != 3 && field[j + 2][k + 1] != 3) {
                        for (int l = 0; l < 2; l++) {
                            field[j][k] = 3;
                            j++;
                        }
                    } else {
                        i--;
                    }
                } else { //horizontal
                    int j = random.nextInt(7) + 2;
                    int k = random.nextInt(6) + 2;
                    if (i == 0) {
                        TwoShip1IndexJ = j - 2;
                        TwoShip1IndexK = k - 2;
                        shipDirection1 = 1;
                    } else if (i == 1) {
                        TwoShip2IndexJ = j - 2;
                        TwoShip2IndexK = k - 2;
                        shipDirection2 = 1;
                    }
                    if (field[j - 1][k - 1] != 3 && field[j - 1][k] != 3 && field[j - 1][k + 1] != 3 && field[j][k - 1] != 3 && field[j][k] != 3 && field[j][k + 1] != 3 && field[j + 1][k - 1] != 3 && field[j + 1][k] != 3 && field[j + 1][k + 1] != 3 && field[j - 1][k + 2] != 3 && field[j][k + 2] != 3 && field[j + 1][k + 2] != 3) {
                        for (int l = 0; l < 2; l++) {
                            field[j][k] = 3;
                            k++;
                        }
                    } else {
                        i--;
                    }
                }
            }
            int[][] actualField = new int[7][7];
            int k = 0;
            int l = 0;
            for (int i = 2; i < 9; i++) {
                for (int j = 2; j < 9; j++) {
                    actualField[k][l] = field[i][j];
                    l++;
                    if (l == 7) {
                        l = 0;
                    }
                }
                k++;
            }

            printField(actualField);
            System.out.println("Enter the coordinates of your shot");
            int movesCount = 0;
            int firstIndex;
            int secondIndex;
            boolean isGameUnfinished = true;
            while (isGameUnfinished) {
                try {
                    firstIndex = scanner.nextInt();
                    secondIndex = scanner.nextInt();
                    movesCount++;


                    //checks if players move hit something
                    if (actualField[firstIndex - 1][secondIndex - 1] == 3) {
                        actualField[firstIndex - 1][secondIndex - 1] = 1;
                        printField(actualField);
                    } // else if it didnt hit
                    else if (actualField[firstIndex - 1][secondIndex - 1] == 0) {
                        actualField[firstIndex - 1][secondIndex - 1] = 4;
                        printField(actualField);
                    }
                    //4 = miss
                    //0 = nothing here
                    //1 = hit
                    //2 = sunk
                    //3 = ship is placed there

                    //check if 3sq ship sunk
                    if (shipDirection == 0 && actualField[shipIndexJ][shipIndexK] == 1 && actualField[shipIndexJ + 1][shipIndexK] == 1 && actualField[shipIndexJ + 2][shipIndexK] == 1) {
                        for (int i = 0; i < 3; i++) {
                            actualField[shipIndexJ][shipIndexK] = 2;
                            printField(actualField);
                            shipIndexJ++;
                        }
                    } else if (shipDirection == 1 && actualField[shipIndexJ][shipIndexK] == 1 && actualField[shipIndexJ][shipIndexK + 1] == 1 && actualField[shipIndexJ][shipIndexK + 2] == 1) {
                        for (int i = 0; i < 3; i++) {
                            actualField[shipIndexJ][shipIndexK] = 2;
                            printField(actualField);
                            shipIndexK++;
                        }
                    }

                    //check 1sq ships
                    if (actualField[ship1IndexJ][ship1IndexK] == 1) {
                        actualField[ship1IndexJ][ship1IndexK] = 2;
                        printField(actualField);
                    }
                    if (actualField[ship2IndexJ][ship2IndexK] == 1) {
                        actualField[ship2IndexJ][ship2IndexK] = 2;
                        printField(actualField);
                    }
                    if (actualField[ship3IndexJ][ship3IndexK] == 1) {
                        actualField[ship3IndexJ][ship3IndexK] = 2;
                        printField(actualField);
                    }
                    if (actualField[ship4IndexJ][ship4IndexK] == 1) {
                        actualField[ship4IndexJ][ship4IndexK] = 2;
                        printField(actualField);
                    }
                    //check 2 2sq ships

                    if (shipDirection1 == 0 && actualField[TwoShip1IndexJ][TwoShip1IndexK] == 1 && actualField[TwoShip1IndexJ + 1][TwoShip1IndexK] == 1) {
                        for (int i = 0; i < 2; i++) {
                            actualField[TwoShip1IndexJ][TwoShip1IndexK] = 2;
                            printField(actualField);
                            TwoShip1IndexJ++;
                        }
                    } else if (shipDirection1 == 1 && actualField[TwoShip1IndexJ][TwoShip1IndexK] == 1 && actualField[TwoShip1IndexJ][TwoShip1IndexK + 1] == 1) {
                        for (int i = 0; i < 2; i++) {
                            actualField[TwoShip1IndexJ][TwoShip1IndexK] = 2;
                            printField(actualField);
                            TwoShip1IndexK++;
                        }
                    }
                    if (shipDirection2 == 0 && actualField[TwoShip2IndexJ][TwoShip2IndexK] == 1 && actualField[TwoShip2IndexJ + 1][TwoShip2IndexK] == 1) {
                        for (int i = 0; i < 2; i++) {
                            actualField[TwoShip2IndexJ][TwoShip2IndexK] = 2;
                            printField(actualField);
                            TwoShip2IndexJ++;
                        }
                    } else if (shipDirection2 == 1 && actualField[TwoShip2IndexJ][TwoShip2IndexK] == 1 && actualField[TwoShip2IndexJ][TwoShip2IndexK + 1] == 1) {
                        for (int i = 0; i < 2; i++) {
                            actualField[TwoShip2IndexJ][TwoShip2IndexK] = 2;
                            printField(actualField);
                            TwoShip2IndexK++;
                        }
                    }

                    //checks if the game is finished yet
                    for (int i = 0; i < 7; i++) {
                        for (int j = 0; j < 7; j++) {
                            if (actualField[i][j] == 3) {
                                isGameUnfinished = true;
                                i = 7;
                                break;
                            } else {
                                isGameUnfinished = false;
                            }
                        }
                    }
                    if (!isGameUnfinished) {
                        System.out.println("You have made " + movesCount + " moves in total.");
                        System.out.println("Start another game? ");
                        System.out.println("1 = yes");
                        System.out.println("0 = no");
                        int userDecision = scanner.nextInt();
                        if (userDecision == 0) {
                            startNewGame = false;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Enter valid coordinates");
                    movesCount--;
                }
            }
        }
    }

    private static void printField(int[][] actualField) {
        System.out.print("\033c");
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(actualField[i][j] + " ");
            }
            System.out.println();
        }
    }
}