package com.jeff.tovusacco.backend;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;


public class MainApp {
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public static void main(String[] args) {
//        try {
//
//            String result = Register("", "", "", "", "", "", "", "");
//            System.out.println("Registration Result: " + result);
//            System.out.println("this is main");
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//    }

    public static final String publicKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0OTq4FBkCO/5kZbBgt+7tHUKmqa6NSvzGnvo8Pia2C7moYDF77TGNcMk5Q5bYjE91QCauAYWxse2thARA1X6FjJz/jeVfYpcV43uuKd8FDaI7P7ah4A+WO4CTwRu95x2a5Hzg0y3qWsxuuBtBeV66uWzKtKcWObPwsblPjfgWkpAxhaIdWhnAk1cXDrukGLrzRIhdY+m3M6yyoW9E+htP9oSkhBF39TxjNtGM0vTSA/w9rVv3x1DGCc7hlvo8DOaj4aG60pdsA7VkVeBnEsXS/lba5dVRFCUHAlMUQfKVx7pZJ9fuHP9IZIfRE0wTPPZwqJSlU8/YQ0ARa5ic5NLjQIDAQAB";

    private static final String base_url = "https://suluhutech.com";

    public static int statusCode;
    public static String Register(String phone, String nationalId, String firstName, String middleName, String lastName, String gender, String dob, String email) throws Exception {

        UUID uuid = UUID.randomUUID();
        String Uid = uuid.toString();

        String service = "PROFILE";
        String action = "BASE";
        String command = "REGISTER";

        String AppId = "TOVU001";
        String Platform = "IOS";
        String CustomerId = "2540000001";
        String MobileNumber = "254700000001";
        String Lat = "0.200";
        String Lon = "-1.01";
        String Device = Uid;// This should be actual deviceId


        String Rsc; // Rsa Somme de Control
        String Rrk; // Rsa Random Key
        String Rrv; // Rsa Random Vector
        String Aad;// Aes App Data

        String F000 = service;
        String F001 = action;
        String F002 = command;
        String F003 = AppId;
        String F004 = CustomerId;
        String F005 = MobileNumber;
        String F006 = ""; //username or email
        String F007 = ""; //passwords (pwd)
        String F008 = "PIN"; //auth mode using pin sent to user via sms
        String F009 = Device;// IMEI
        String F010 = Device;


        String F011 = "";
        String F012 = "";
        String F013 = "";
        String F014 = Platform;
        String F015 = "";
        String F016 = "";
        String F017 = "";
        String F018 = "";
        String F019 = "";
        String F020 = "";

        // sending customer info

        String F021 = phone; // MobileNumber
        String F022 = nationalId;// NationalId
        String F023 = firstName;// FirstName
        String F024 = middleName;// MiddleName
        String F025 = lastName;// LastName
        String F026 = gender;// Gender
        String F027 = dob; // DOB
        String F028 = email; // Email Address
        String F029 = "";
        String F030 = "";

        String F031 = "";
        String F032 = "";
        String F033 = "";
        String F034 = "";
        String F035 = "";
        String F036 = "";
        String F037 = "";
        String F038 = "";
        String F039 = "";
        String F040 = "";

        String trxData = "{\"F000\":\"" + F000 + "\",\"F001\":\"" + F001 + "\",\"F002\":\"" + F002 + "\",\"F003\":\"" + F003 + "\",\"F004\":\"" + F004 + "\",\"F005\":\"" + F005 + "\",\"F006\":\"" + F006 + "\",\"F007\":\"" + F007 + "\",\"F008\":\"" + F008 + "\",\"F009\":\"" + F009 + "\",\"F010\":\"" + F010 + "\",\"F011\":\"" + F011 + "\",\"F012\":\"" + F012 + "\",\"F013\":\"" + F013 + "\",\"F014\":\"" + F014 + "\",\"F015\":\"" + F015 + "\",\"F016\":\"" + F016 + "\",\"F017\":\"" + F017 + "\",\"F018\":\"" + F018 + "\",\"F019\":\"" + F019 + "\",\"F020\":\"" + F020 + "\",\"F021\":\"" + F021 + "\",\"F022\":\"" + F022 + "\",\"F023\":\"" + F023 + "\",\"F024\":\"" + F024 + "\",\"F025\":\"" + F025 + "\",\"F026\":\"" + F026 + "\",\"F027\":\"" + F027 + "\",\"F028\":\"" + F028 + "\",\"F029\":\"" + F029 + "\",\"F030\":\"" + F030 + "\",\"F031\":\"" + F031 + "\",\"F032\":\"" + F032 + "\",\"F033\":\"" + F033 + "\",\"F034\":\"" + F034 + "\",\"F035\":\"" + F035 + "\",\"F036\":\"" + F036 + "\",\"F037\":\"" + F037 + "\",\"F038\":\"" + F038 + "\",\"F039\":\"" + F039 + "\",\"F040\":\"" + F040 + "\"}";

        String appData = "{\"UniqueId\":\"" + Uid + "\",\"AppId\":\"" + AppId + "\",\"Device\":\"" + Device + "\",\"Platform\":\"" + Platform + "\",\"CustomerId\":\"" + CustomerId + "\",\"MobileNumber\":\"" + MobileNumber + "\",\"Lat\":\"" + Lat + "\",\"Lon\":\"" + Lon + "\"}";

        System.out.println(trxData);
        String hashedTrxData = Hash(trxData, Device);
        System.out.println();
        System.out.println(hashedTrxData);
        System.out.println();

        String strKey = "lbXDF0000yxrG24B";
        String strIV = "HlPGoH11117Pf5sv";

        Rsc = hashedTrxData;
        Rrk = encrypt( strKey);
        Rrv = encrypt(strIV);
        Aad = encrypt(appData, strKey, strIV);
        Log.d("aad", Aad);

        String coreData = encrypt(trxData, strKey, strIV);

        coreData = "{\"Data\":\"" + coreData + "\"}";

        String dataString = "{\"Uid\":\"" + Uid + "\",\"Rsc\":\"" + Rsc + "\",\"Rrk\":\"" + Rrk + "\",\"Rrv\":\"" + Rrv + "\",\"Aad\":\"" + Aad + "\"}";

        final String AUTH_URL = base_url + "/AtomGate/api/apps/auth"; //endpoint for auth
        final String CORE_URL = base_url + "/AtomGate/api/apps/core"; //core endpoint for decryption the data

        final String coreRequest = coreData;


        Log.d("dataString", dataString);

        final String authResultStr = HttpPost(AUTH_URL, dataString);

        System.out.println("this is statusCode : " + statusCode);
        System.out.println("this is authResultStr : " + authResultStr);

        final String coreResultStr = HttpPost(CORE_URL, authResultStr, coreRequest);

        if(statusCode==200 || statusCode==401)
        {
            final String coreDecryted = decrypt(coreResultStr, strKey, strIV);

            System.out.println(coreDecryted);

        }
        else{
            System.out.println(coreResultStr);

        }
        return coreResultStr;

    }
    public static byte[] EncryptData(String data, RSAPublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] plaintextBytes = data.getBytes("UTF-8");
            return cipher.doFinal(plaintextBytes);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return new byte[0];
        }
    }

    public static String encrypt(String textToEncrypt) {
        try {
            PublicKey publicKey = getPublicKeyFromString(publicKeyString);
            byte[] encryptedData = EncryptData(textToEncrypt, (RSAPublicKey) publicKey);
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return "";
        }
    }

    public static PublicKey getPublicKeyFromString(String publicKeyString) {
        try {
            byte[] bytes = Base64.getDecoder().decode(publicKeyString);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to parse public key: " + ex.getMessage(), ex);
        }
    }

    public static String encrypt(String data, String keyStr, String ivStr) throws Exception {
        // Static 16-byte (128-bit) key and IV
        byte[] keyBytes = keyStr.getBytes("UTF-8");
        byte[] iv = ivStr.getBytes("UTF-8");

        String plaintext = data;

        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

        // Create the AES-GCM cipher object
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        // Encryption
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new GCMParameterSpec(128, iv));
        byte[] encryptedText = cipher.doFinal(plaintext.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedText);
    }
    public static String decrypt(String data, String keyStr, String ivStr) throws Exception {
        // Static 16-byte (128-bit) key and IV
        byte[] keyBytes = keyStr.getBytes("UTF-8");
        byte[] iv = ivStr.getBytes("UTF-8");

        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

        // Create the AES-GCM cipher object
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        byte[] encryptedBytes = Base64.getDecoder().decode(data);
        // Decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new GCMParameterSpec(128, iv));
        byte[] decryptedText = cipher.doFinal(encryptedBytes);

        return new String(decryptedText, "UTF-8");
    }


    // START HTTP
    public static String HttpPost(String authUrl, String json) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(authUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
            streamWriter.write(json);
            streamWriter.flush();
            StringBuilder stringBuilder = new StringBuilder();
            statusCode = connection.getResponseCode();
            System.out.println("HttpStatusCode:" + statusCode);
            if (statusCode == HttpURLConnection.HTTP_OK) {
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                String response = null;
                while ((response = bufferedReader.readLine()) != null) {
                    stringBuilder.append(response);
                }
                bufferedReader.close();
                System.out.println(stringBuilder.toString());
                return stringBuilder.toString();
            } else if (statusCode == HttpURLConnection.HTTP_BAD_REQUEST) {
                InputStreamReader streamReader = new InputStreamReader(connection.getErrorStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                String response = null;
                while ((response = bufferedReader.readLine()) != null) {
                    stringBuilder.append(response);
                }
                bufferedReader.close();
                System.out.println(stringBuilder.toString());
                return stringBuilder.toString();
            } else if (statusCode == HttpURLConnection.HTTP_NOT_FOUND) {
                InputStreamReader streamReader = new InputStreamReader(connection.getErrorStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                String response = null;
                while ((response = bufferedReader.readLine()) != null) {
                    stringBuilder.append(response);
                }
                bufferedReader.close();
                System.out.println(stringBuilder.toString());
                return stringBuilder.toString();
            } else if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                InputStreamReader streamReader = new InputStreamReader(connection.getErrorStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                String response = null;
                while ((response = bufferedReader.readLine()) != null) {
                    stringBuilder.append(response);
                }
                bufferedReader.close();
                System.out.println(stringBuilder.toString());
                return stringBuilder.toString();
            } else {
                System.out.println(connection.getResponseMessage());
                return connection.getResponseMessage();
            }
        } catch (Exception exception) {
            System.out.println(exception.toString());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static String HttpPost(String coreUrl, String header, String json) {


        System.out.println("this is coreurl : " + coreUrl);
        System.out.println("this is header : " + header);
        System.out.println("this is json : " + json);

        HttpURLConnection connection = null;
        try {
            URL url = new URL(coreUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.addRequestProperty("Authorization", "Bearer " + header);

            OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
            streamWriter.write(json);
            streamWriter.flush();
            StringBuilder stringBuilder = new StringBuilder();
            statusCode = connection.getResponseCode();
            System.out.println("HttpStatusCode from core:" + statusCode);
            if (statusCode == HttpURLConnection.HTTP_OK) {
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                String response = null;
                while ((response = bufferedReader.readLine()) != null) {
                    stringBuilder.append(response);
                }
                bufferedReader.close();
                System.out.println(stringBuilder.toString());
                return stringBuilder.toString();
            } else if (statusCode == HttpURLConnection.HTTP_BAD_REQUEST) {
                InputStreamReader streamReader = new InputStreamReader(connection.getErrorStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                String response = null;
                while ((response = bufferedReader.readLine()) != null) {
                    stringBuilder.append(response);
                }
                bufferedReader.close();
                System.out.println(stringBuilder.toString());
                return stringBuilder.toString();
            } else if (statusCode == HttpURLConnection.HTTP_NOT_FOUND) {
                InputStreamReader streamReader = new InputStreamReader(connection.getErrorStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                String response = null;
                while ((response = bufferedReader.readLine()) != null) {
                    stringBuilder.append(response);
                }
                bufferedReader.close();
                System.out.println(stringBuilder.toString());
                return stringBuilder.toString();
            } else if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                InputStreamReader streamReader = new InputStreamReader(connection.getErrorStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                String response = null;
                while ((response = bufferedReader.readLine()) != null) {
                    stringBuilder.append(response);
                }
                bufferedReader.close();
                System.out.println(stringBuilder.toString());
                return stringBuilder.toString();
            } else {
                System.out.println(connection.getResponseMessage());
                return connection.getResponseMessage();
            }
        } catch (Exception exception) {
            System.out.println(exception.toString());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    // END HTTP
    // START HASH-256
    public static String Hash(String data, String salt) throws NoSuchAlgorithmException {

        String originalString = data + salt;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                originalString.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
