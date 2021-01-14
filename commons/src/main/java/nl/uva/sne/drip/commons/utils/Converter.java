/*
 * Copyright 2019 S. Koulouzis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this zipfile except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.uva.sne.drip.commons.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author S. Koulouzis
 */
public class Converter {

    public static String map2YmlString(Map<String, Object> map) throws JSONException {
        JSONObject jsonObject = new JSONObject(map);
        String yamlStr = json2Yml2(jsonObject.toString());
        return yamlStr;
    }

    public static String json2Yml2(String jsonString) throws JSONException {
        Yaml yaml = new Yaml();
        String yamlStr = yaml.dump(ymlString2Map(jsonString));
        return yamlStr;
    }

    public static Map<String, Object> ymlString2Map(String yamlString) {
        Yaml yaml = new Yaml();
        Object object = yaml.load(yamlString);
        if (object instanceof List) {
            Map<String, Object> map = new HashMap<>();
            map.put("---", object);
            return map;
        }
        return (Map<String, Object>) object;
    }

    public static String encodeFileToBase64Binary(String fileName) throws IOException {
        return encode2Base64(Files.readAllBytes(Paths.get(fileName)));
    }

    public static void decodeBase64BToFile(String base64, String fileName) throws IOException {
        byte[] decodedBytrs = Base64.getDecoder().decode(base64);
        Files.write(Paths.get(fileName), decodedBytrs);
    }

    public static String getFileMD5(String filePath) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        String keyStoreContents = new String(Files.readAllBytes(Paths.get(filePath)));
        md.update(keyStoreContents.getBytes());
        byte[] digest = md.digest();
        return new String(digest, StandardCharsets.UTF_8);
    }

    public static String encodeFileToBase64Binary(MultipartFile file) throws IOException {

        String originalFileName = file.getOriginalFilename();
        String name = System.currentTimeMillis() + "_" + originalFileName;
        byte[] bytes = file.getBytes();

        return encode2Base64(bytes);

    }

    private static String encode2Base64(byte[] bytes) {

        byte[] encodedBytes = Base64.getEncoder().encode(bytes);
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }

    public static void zipFolder(String sourceFolder, String zipFolder) throws FileNotFoundException, IOException {
        try (FileOutputStream fos = new FileOutputStream(zipFolder);
                ZipOutputStream zos = new ZipOutputStream(fos)) {
            Path sourcePath = Paths.get(sourceFolder);
            Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) throws IOException {
                    if (!sourcePath.equals(dir)) {
                        zos.putNextEntry(new ZipEntry(sourcePath.relativize(dir).toString() + "/"));
                        zos.closeEntry();
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
                    zos.putNextEntry(new ZipEntry(sourcePath.relativize(file).toString()));
                    Files.copy(file, zos);
                    zos.closeEntry();
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    public static void unzipFolder(String zipFile, String uncompressedDirectory) throws IOException {
        try (ZipFile zipfile = new ZipFile(zipFile)) {
            FileSystem fileSystem = FileSystems.getDefault();
            Enumeration<? extends ZipEntry> zipEntries = zipfile.entries();

            while (zipEntries.hasMoreElements()) {
                ZipEntry entry = zipEntries.nextElement();
                if (entry.isDirectory()) {
                    Files.createDirectories(fileSystem.getPath(uncompressedDirectory + File.separator + entry.getName()));
                } else {
                    InputStream is = zipfile.getInputStream(entry);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    String uncompressedFileName = uncompressedDirectory + File.separator + entry.getName();
                    Path uncompressedFilePath = fileSystem.getPath(uncompressedFileName);
                    Files.createFile(uncompressedFilePath);
                    try (FileOutputStream fileOutput = new FileOutputStream(uncompressedFileName)) {
                        while (bis.available() > 0) {
                            fileOutput.write(bis.read());
                        }
                    }
                }
            }
        }
    }

}
