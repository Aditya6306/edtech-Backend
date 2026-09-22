package com.learnify_backend.config;
import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dkspqw5jb");
        config.put("api_key",
                        "723395437961498");
        config.put("api_secret", "888aPO41um45o7d7qUNAvz3GVck");

        return new Cloudinary(config);
    }
}