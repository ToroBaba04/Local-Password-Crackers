import java.util.List;
import java.util.Arrays;
import java.io.Console;

public class PasswordCrackerApp extends CryptoUtils {
    public static void main(String[] args) {

        Console console = System.console();
        if (console == null) {
            System.out.println("La console n'est pas disponible. Utilisez Scanner à la place.");
            System.exit(1);
        }

        // Demander le premier mot de passe
        char[] password1Chars = console.readPassword("Veuillez renseigner un 1er Mot de Passe : ");
        String password1 = new String(password1Chars);
        Arrays.fill(password1Chars, ' '); // Effacer les caractères du mot de passe de la mémoire

        // Calculer le hachage MD5 du premier mot de passe
        String hash1 = md5Hasher(password1);

        // Afficher le hachage pour vérification
        System.out.println("Hash 1: " + hash1);

        // Créer une instance de BruteforceLocalCracker et tester
        BruteforceLocalCracker bruteforcer = new BruteforceLocalCracker();
        System.out.println("Testing Bruteforce Cracker:");
        bruteforcer.crackPassword(hash1);  // Devrait réussir pour des mots courts avec force brute

        // Demander le deuxième mot de passe
        char[] password2Chars = console.readPassword("Veuillez renseigner un 2nd Mot de Passe : ");
        String password2 = new String(password2Chars);
        Arrays.fill(password2Chars, ' '); // Effacer les caractères du mot de passe de la mémoire

        // Calculer le hachage MD5 du deuxième mot de passe
        String hash2 = md5Hasher(password2);

        // Afficher le hachage pour vérification
        System.out.println("Hash 2: " + hash2);

        // Créer une instance de DictionaryLocalCracker avec un petit dictionnaire
        List<String> dictionary = List.of("hello", "world", password2, "123456", "crack");
        DictionaryLocalCracker dictionaryCracker = new DictionaryLocalCracker(dictionary);
        System.out.println("Testing Dictionary Cracker:");
        dictionaryCracker.crackPassword(hash2);  // Devrait réussir avec une attaque de dictionnaire
    }
}
