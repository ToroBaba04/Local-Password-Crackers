public class BruteforceLocalCracker extends CryptoUtils implements CrackerFactory {
    
    public void crackPassword(String hashedPassword) {
        bruteForceCrack(hashedPassword);
    }

    public static void bruteForceCrack(String hashedPassword) {
        System.out.println("Craquage par BruteForce en cours.....");
        // Longueur maximale du mot de passe à générer pour le craquage par force brute
        int maxLength = 5;

        for (int length = 1; length <= maxLength; length++) {
            bruteForceHelper(hashedPassword, "", length);
        }
    }

    private static void bruteForceHelper(String hashedPassword, String attempt, int length) {
        // Caractères possibles pour le craquage par force brute
        char[] charset = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        if (length == 0) {
            // Calculer le hash MD5 de la tentative
            String hashedAttempt = md5Hasher(attempt);
            if (hashedAttempt.equals(hashedPassword)) {
                System.out.println("Mot de Passe craqué par Brute Force: " + attempt);
                return;
            }
        } else {
            for (char c : charset) {
                bruteForceHelper(hashedPassword, attempt + c, length - 1);
            }
        }
    }
}