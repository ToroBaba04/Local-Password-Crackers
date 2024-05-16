import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryLocalCracker extends CryptoUtils implements CrackerFactory {
    private List<String> dictionary;
    private Map<String, String> passwordMap; // Pour stocker les correspondances entre les hachages et les mots de passe

    public DictionaryLocalCracker(List<String> dictionary) {
        this.dictionary = dictionary;
        this.passwordMap = new HashMap<>(); // Initialisation de la carte
    }

    public void crackPassword(String hashedPassword) {
        dictionaryAttack(hashedPassword);
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    private void dictionaryAttack(String hashedPassword) {
        if (dictionary == null) {
            System.out.println("Default dictionary is not set.");
            return;
        }

        dictionaryAttack(hashedPassword, dictionary);
    }

    public void dictionaryAttack(String hashedPassword, List<String> customDictionary) {
        System.out.println("Cracking password using dictionary...");

        // Précalculez les hachages du dictionnaire s'ils ne sont pas déjà précalculés
        if (passwordMap.isEmpty()) {
            for (String word : customDictionary) {
                String hashedWord = md5(word);
                passwordMap.put(hashedWord, word); // Stocker la correspondance entre le hachage et le mot de passe
            }
        }

        // Recherche dans le dictionnaire
        if (passwordMap.containsKey(hashedPassword)) {
            String plaintextPassword = passwordMap.get(hashedPassword); // Récupérer le mot de passe en clair
            System.out.println("Password cracked using dictionary: " + plaintextPassword);
        } else {
            System.out.println("Password not found in dictionary.");
        }
    }
}
