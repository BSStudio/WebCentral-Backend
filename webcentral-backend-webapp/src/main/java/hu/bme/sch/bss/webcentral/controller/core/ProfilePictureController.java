package hu.bme.sch.bss.webcentral.controller.core;

import hu.bme.sch.bss.webcentral.core.domain.UserResponse;
import hu.bme.sch.bss.webcentral.core.model.User;
import hu.bme.sch.bss.webcentral.core.service.ProfilePictureStorageService;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/profilePictures", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfilePictureController {

    private final Logger logger;
    private final ProfilePictureStorageService profilePictureStorageService;

    public ProfilePictureController(final Logger logger, final ProfilePictureStorageService profilePictureStorageService) {
        this.logger = logger;
        this.profilePictureStorageService = profilePictureStorageService;
    }

    @PostMapping()
    @ResponseStatus(CREATED)
    public UserResponse uploadPicture(@RequestParam("picture") MultipartFile multipartFile) {
        logger.info("Request to upload file received");
        profilePictureStorageService.storeImage(multipartFile);
        return new UserResponse(User.builder().build());
    }


}
