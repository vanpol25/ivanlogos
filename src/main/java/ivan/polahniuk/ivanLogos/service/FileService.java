package ivan.polahniuk.ivanLogos.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import static ivan.polahniuk.ivanLogos.service.ProductService.IMG_DIR;

@Service
public class FileService {

    public String saveFile(String img) throws IOException {
        createDir(IMG_DIR);//create folder if not exists

        String[] data = img.split(",");
        String metaInfo = data[0];
        String base64File = data[1];

        String fileName = createFileName(getFileExtensionFromMetaInfo(metaInfo));

        Files.write(
                Paths.get(IMG_DIR, fileName),
                Base64.getDecoder().decode(base64File.getBytes())
        );

        return fileName;
    }

    public void deleteFile(String img) throws IOException {
        Files.delete(Paths.get(IMG_DIR, img));
    }

    private String createFileName(String fileExtension) {
        String fileName = UUID.randomUUID().toString();
        return String.format("%s.%s", fileName, fileExtension);
    }

    private String getFileExtensionFromMetaInfo(String metaInfo) {
        return metaInfo.split("/")[1].split(";")[0];
    }

    private void createDir(String dir) {
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}