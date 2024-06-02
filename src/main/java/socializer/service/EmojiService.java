package socializer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import socializer.constant.WrongInputType;
import socializer.exception.CustomWrongInputException;
import socializer.util.CustomFileUtils;

import java.net.MalformedURLException;
import java.net.URI;

@RequiredArgsConstructor
@Service
public class EmojiService {

    public Resource getEmoji(String name) {
        String videoPath = String.format("emojis/%s.mp4", name);
        URI uri = CustomFileUtils.getFileFromResourcesAsFile(videoPath)
                .toPath()
                .toUri();
        try {
            return new UrlResource(uri);
        } catch (MalformedURLException e) {
            throw new CustomWrongInputException(WrongInputType.NO_SUCH_FILE);
        }
    }
}
