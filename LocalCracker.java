public abstract class LocalCracker extends PasswordCrackerFactory {
    public CrackerFactory createPasswordCracker() {
        return (CrackerFactory) this;
    }
}