package exam.model.imgurapi;

/**
 * Interface providing methods used to upload an image via the Imgur API.
 */
public interface ImgurApi {

    /**
     * Uploads a base64 encoded image to Imgur via the /3/image endpoint and returns a JSON response.
     *
     * @param image an image encoded as a base64 string
     * @return a JSON response String in the format defined by the Imgur API /3/image endpoint
     */
    String uploadBase64Image(String image);
}
