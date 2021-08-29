package exam.model.imgurresponse.uploadresponse;

/**
 * Default implementation of the ImgurUploadResponse interface.
 */
public class ImgurUploadResponseImpl implements ImgurUploadResponse {

    private final String link;
    private final String errorMessage;

    /**
     * Default ImgurUploadResponseImpl constructor
     *
     * @param link         a link to an image
     * @param errorMessage a String representing an error that has occurred while uploading an image
     * @throws IllegalArgumentException if both parameters are null, or both are not null
     */
    public ImgurUploadResponseImpl(String link, String errorMessage) {
        if ((null == link && null == errorMessage) || (null != link && null != errorMessage)) {
            throw new IllegalArgumentException();
        }
        this.link = link;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getLink() {
        return this.link;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
