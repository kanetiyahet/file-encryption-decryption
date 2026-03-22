import java.io.File;

public class VaultManager {

    public static final String VAULT_PATH = "vault/";

    static {
        new File(VAULT_PATH).mkdirs();
    }
}

