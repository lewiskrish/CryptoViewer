package exam.model.imgurresponse.uploadresponse;

/**
 * Interface used to represent and provide getters to the content returned
 * by a call to the the Imgur /3/image endpoint.
 */
public interface ImgurUploadResponse {

    /**
     * Returns the url that the image has been uploaded to as returned by the Imgur API.
     *
     * @return a String url for an image
     */
    String getLink();

    /**
     * Returns a String representing an error that has occurred while uploading an image.
     *
     * @return an error message
     */
    String getErrorMessage();
}
