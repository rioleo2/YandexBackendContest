import java.util.Arrays;
import java.util.Scanner;

public class App {

    public static void quickSort(String[][] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Разделение массива для быстрой сортировки
    public static int partition(String[][] arr, int low, int high) {
        String[] pivot = arr[high];
        int pivotId = Integer.parseInt(pivot[3]);
        int pivotTime = Integer.parseInt(pivot[5]);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            int currentId = Integer.parseInt(arr[j][3]);
            int currentTime = Integer.parseInt(arr[j][5]);

            if (currentId < pivotId || (currentId == pivotId && currentTime < pivotTime)) {
                i++;
                String[] temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        String[] temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N;

        // System.out.println("Введите количество строк логов: ");

        N = scanner.nextInt(); // N = количество записей в логе

        String[] log = new String[N]; // массив строк лога
        String[][] LogData = new String[N][5];
        String[][] updatedLogData = new String[N][6];
        int countOfMinutes[] = new int[N];

        scanner.nextLine();
        // System.out.println("Введите логи: ");
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
            int totalMinutes = doyToMinutes + hoursToMinutes + minutesToMinutes;

            LogData[i][5] = Integer.toString(totalMinutes);
        }

        // Сортировка массива LogData по LogData[i][3] и LogData[i][5]
        quickSort(LogData, 0, N - 1);

        // System.out.println("\nВы ввели: ");
        // for (int i = 0; i < N; i++) {
        // for (int j = 0; j < 6; j++) {
        // System.out.print(LogData[i][j] + " ");
        // }
        // System.out.println();
        // }

        // for (int i = 0; i < N - 1; i++) {
        // for (int j = i + 1; j < N; j++) {
        // if (Integer.parseInt(LogData[j][3]) < Integer.parseInt(LogData[i][3])) {
        // String[] temp = LogData[i];
        // LogData[i] = LogData[j];
        // LogData[j] = temp;
        // }
        // }
        // }

        Arrays.sort(LogData, (a, b) -> Integer.parseInt(a[3]) - Integer.parseInt(b[3]));

        // System.out.println("\nМассив после сортировки по ID: ");

        // for (int i = 0; i < N; i++) {
        // for (int j = 0; j < 6; j++) {
        // System.out.print(LogData[i][j] + " ");
        // }
        // System.out.println();
        // }

        // for (int i = 0; i < N - 1; i++) {
        //     for (int j = i + 1; j < N; j++) {
        //         if (Integer.parseInt(LogData[j][3]) == Integer.parseInt(LogData[i][3])) {
        //             if (Integer.parseInt(LogData[j][5]) < Integer.parseInt(LogData[i][5])) {
        //                 String[] temp = LogData[i];
        //                 LogData[i] = LogData[j];
        //                 LogData[j] = temp;
        //             }
        //         }
        //     }
        // }

        // System.out.println("\nМассив после сортировки по времени: ");
        // for (int i = 0; i < N; i++) {
        // for (int j = 0; j < 6; j++) {
        // System.out.print(LogData[i][j] + " ");
        // }
        // System.out.println();
        // }

        int k = 0;

        for (int i = 0; i < N - 1; i++) {
            if (Integer.parseInt(LogData[i][3]) == Integer.parseInt(LogData[i + 1][3])) {
                int minutesDifference = Integer.parseInt(LogData[i + 1][5]) - Integer.parseInt(LogData[i][5]);
                String currentStatus = LogData[i][4];
                String nextStatus = LogData[i + 1][4];

                if ((currentStatus.equals("A") && (nextStatus.equals("B") || nextStatus.equals("C"))) ||
                        currentStatus.equals("B")) {
                    countOfMinutes[k] += minutesDifference;
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
