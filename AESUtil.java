import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.security.SecureRandom;

public class AESUtil {

    public static void encrypt(String key, File inputFile) throws Exception {

        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        File outputFile = new File(VaultManager.VAULT_PATH + inputFile.getName() + ".enc");

        FileInputStream fis = new FileInputStream(inputFile);
        FileOutputStream fos = new FileOutputStream(outputFile);

        fos.write(iv);

        byte[] buffer = new byte[4096];
        int read;

        while((read = fis.read(buffer)) != -1) {
            byte[] out = cipher.update(buffer,0,read);
            if(out != null) fos.write(out);
        }

        byte[] out = cipher.doFinal();
        if(out != null) fos.write(out);

        fis.close();
        fos.close();
    }

    public static void decrypt(String key, File inputFile) throws Exception {

        FileInputStream fis = new FileInputStream(inputFile);

        byte[] iv = new byte[16];
        fis.read(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,
                new SecretKeySpec(key.getBytes(),"AES"),
                new IvParameterSpec(iv));

        String originalName = inputFile.getName().replace(".enc","");
        File outputFile = new File(originalName);

        FileOutputStream fos = new FileOutputStream(outputFile);

        byte[] buffer = new byte[4096];
        int read;

        while((read = fis.read(buffer)) != -1) {
            byte[] out = cipher.update(buffer,0,read);
            if(out != null) fos.write(out);
        }

        byte[] out = cipher.doFinal();
        if(out != null) fos.write(out);

        fis.close();
        fos.close();
    }
}
