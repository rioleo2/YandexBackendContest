import java.util.Scanner;

public class App {

    public static int findCountOfUniqueCharacters(String FIO) {
        int countOfUniqueCharacters = 1; // количество уникальных букв в ФИО
        for (int i = 0; i < FIO.length(); i++) {
            for (int j = i + 1; j < FIO.length(); j++) {
                if (FIO.charAt(i) == FIO.charAt(j)) {
                    break;
                }
                if (j == FIO.length() - 1) {
                    countOfUniqueCharacters++;
                }
            }
        }
        return countOfUniqueCharacters;
    }

    public static int findSumOfNumbersInBirthdayDate(String fullBirthNumber) {
        int sumOfNumbers = 0; // сумма цифр в дне и месяце рождения
        for (int i = 0; i < fullBirthNumber.length(); i++) {

            sumOfNumbers += Character.getNumericValue(fullBirthNumber.charAt(i));

        }
        return sumOfNumbers;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N;

        System.out.print("Введите количество людей: ");
        N = scanner.nextInt();

        String[] CSVData = new String[N];
        String[][] PersonData = new String[N][64];
        String[] FIO = new String[N];
        String[] fullBirthNumberArray = new String[N];
        String[] hexFullCiphersArray = new String[N];
        int countOfUniqueCharactersArray[] = new int[N]; // количество уникальных букв в ФИО
        int sumOfNumbersArray[] = new int[N]; // сумма цифр в дне и месяце рождения
        int firstCharactersArray[] = new int[N]; // порядковый номер по алфавиту первого символа фамилии
        int fullCiphersArray[] = new int[N];

        scanner.nextLine();

        System.out.println("Введите данные людей в формате CSV:");
        for (int i = 0; i < N; i++) {
            CSVData[i] = scanner.nextLine();
            PersonData[i] = CSVData[i].split(",");
            System.out.println(
                    "Фамилия: " + PersonData[i][0] + ", Имя: " + PersonData[i][1] + ", Отчество: " + PersonData[i][2]
                            + ", Дата рождения: " + PersonData[i][3] + "." + PersonData[i][4] + "." + PersonData[i][5]);
        }
        scanner.close();

        for (int i = 0; i < N; i++) {
            FIO[i] = PersonData[i][0] + PersonData[i][1] + PersonData[i][2];
            System.out.println("ФИО человека: " + FIO[i]);
            countOfUniqueCharactersArray[i] = findCountOfUniqueCharacters(FIO[i]); // количество уникальных букв в ФИО
                                                                                   // первого
            // человека
            System.out.println("Количество уникальных букв в ФИО: " + countOfUniqueCharactersArray[i]);

        }

        fullBirthNumberArray[0] = PersonData[0][3] + PersonData[0][4];

        for (int i = 0; i < N; i++) {
            fullBirthNumberArray[i] = PersonData[i][3] + PersonData[i][4];
            sumOfNumbersArray[i] = findSumOfNumbersInBirthdayDate(fullBirthNumberArray[i]); // сумма цифр в дне и месяце
                                                                                            // рождения

            System.out.println("Сумма цифр в полном году рождения: " + sumOfNumbersArray[i]);
        }

        for (int i = 0; i < N; i++) {
            firstCharactersArray[i] = (int) PersonData[i][0].toUpperCase().charAt(0) - 64;
            System.out.println(
                    "Порядковый номер по алфавиту первого символа фамилии: "
                            + firstCharactersArray[i]);
        }

        for (int i = 0; i < N; i++) {
            fullCiphersArray[i] = countOfUniqueCharactersArray[i] + sumOfNumbersArray[i] * 64
                    + firstCharactersArray[i] * 256;
            hexFullCiphersArray[i] = Integer.toHexString(fullCiphersArray[i]); // переводим в шестнадцатеричный вид
            System.out.println("Ключ: " + fullCiphersArray[i]);
            System.out.println("Ключ(16) : " + hexFullCiphersArray[i]);
        }

        for (int i = 0; i < N; i++) {
            for (int j = hexFullCiphersArray[i].length() - 3; j < hexFullCiphersArray[i].length(); j++) {
                System.out.print(hexFullCiphersArray[i].charAt(j));
            }
            System.out.print(" ");
        }
    }

}