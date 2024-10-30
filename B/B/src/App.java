import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N;

        System.out.println("Введите количество строк логов: ");

        N = scanner.nextInt(); // N = количество записей в логе

        String[] log = new String[N]; // массив строк лога
        String[][] LogData = new String[N][5];
        String[][] updatedLogData = new String[N][6];
        int countOfMinutes[] = new int[N];

        scanner.nextLine();
        System.out.println("Введите логи: ");
        for (int i = 0; i < N; i++) {
            log[i] = scanner.nextLine(); // запись в массив строк лога
            LogData[i] = log[i].split(" ");
        }

        scanner.close();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < LogData[i].length; j++) {
                updatedLogData[i][j] = LogData[i][j];
            }
        }

        LogData = updatedLogData;

        for (int i = 0; i < N; i++) {
            int doyToMinutes = Integer.parseInt(LogData[i][0]) * 60 * 24;
            int hoursToMinutes = Integer.parseInt(LogData[i][1]) * 60;
            int minutesToMinutes = Integer.parseInt(LogData[i][2]);
            LogData[i][5] = Integer.toString(doyToMinutes + hoursToMinutes + minutesToMinutes);
        }

        System.out.println("\nВы ввели: ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(LogData[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (Integer.parseInt(LogData[j][3]) < Integer.parseInt(LogData[i][3])) {
                    String[] temp = LogData[i];
                    LogData[i] = LogData[j];
                    LogData[j] = temp;
                }
            }
        }

        System.out.println("\nМассив после сортировки по ID: ");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(LogData[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (Integer.parseInt(LogData[j][3]) == Integer.parseInt(LogData[i][3])) {
                    if (Integer.parseInt(LogData[j][0]) < Integer.parseInt(LogData[i][0])) {
                        String[] temp = LogData[i];
                        LogData[i] = LogData[j];
                        LogData[j] = temp;
                    }
                }
            }
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (Integer.parseInt(LogData[j][3]) == Integer.parseInt(LogData[i][3])) {
                    if ((Integer.parseInt(LogData[j][1]) < Integer.parseInt(LogData[i][1]))
                            && (Integer.parseInt(LogData[j][0]) == Integer.parseInt(LogData[i][0]))) {
                        String[] temp = LogData[i];
                        LogData[i] = LogData[j];
                        LogData[j] = temp;
                    }
                }
            }
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (Integer.parseInt(LogData[j][3]) == Integer.parseInt(LogData[i][3])) {
                    if ((Integer.parseInt(LogData[j][2]) < Integer.parseInt(LogData[i][2]))
                            && (Integer.parseInt(LogData[j][1]) == Integer.parseInt(LogData[i][1]))) {
                        String[] temp = LogData[i];
                        LogData[i] = LogData[j];
                        LogData[j] = temp;
                    }
                }
            }
        }

        System.out.println("\nМассив после сортировки по времени: ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(LogData[i][j] + " ");
            }
            System.out.println();
        }

        int k = 0;

        for (int i = 0; i < N - 1; i++) {
            System.out.println(i + " " + k + " " + countOfMinutes[k]);
            if ((Integer.parseInt(LogData[i][3]) == Integer.parseInt(LogData[i + 1][3]))) {
                if ((LogData[i][4].equals("A")) && ((LogData[i + 1][4].equals("B")) || (LogData[i + 1][4].equals("C")))) {
                    countOfMinutes[k] += Integer.parseInt(LogData[i + 1][5]) - (Integer.parseInt(LogData[i][5]));
                } else if (LogData[i][4].equals("B")) {
                    countOfMinutes[k] += Integer.parseInt(LogData[i + 1][5]) - (Integer.parseInt(LogData[i][5]));
                }
             } else {
                k++;
                countOfMinutes[k] = 0;
            }
        }

        for (int i = 0; i < N; i++) {
            if (countOfMinutes[i] > 0) {
                System.out.print(countOfMinutes[i] + " ");
            }
        }


    }
}
