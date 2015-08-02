package masterSpringMvc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;

@ConfigurationProperties(prefix = "upload.pictures")
public class PictureUploadProperties {
    private Resource uploadPath;
    private Resource anonymousPicture;

    public Resource getAnonymousPicture() {
        return anonymousPicture;
    }

    public void setAnonymousPicture(String anonymousPicture) throws IOException {
        this.anonymousPicture = new DefaultResourceLoader().getResource(anonymousPicture);
        if (!this.anonymousPicture.getFile().isFile()) {
            throw new IOException("File " + anonymousPicture + " does not exist");
        }
    }

    public Resource getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) throws IOException {
        this.uploadPath = new DefaultResourceLoader().getResource(uploadPath);
        if (!this.uploadPath.getFile().isDirectory()) {
            throw new IOException("Directory " + uploadPath + " does not exist");
        }
    }
}
