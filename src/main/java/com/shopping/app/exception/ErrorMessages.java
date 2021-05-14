/**
 * 
 */
package com.shopping.app.exception;

/**
 * final class to list all api error messages
 *
 */
public final class ErrorMessages {
    public static final String INTERNAL_SERVER_ERROR_MSG = "Internal server error occurred";
    public static final String BAD_REQUEST_MSG = "Bad data input";
    public static final String METHOD_NOT_ALLOWES_MSG = "Method not allowed";
    public static final String UNSUPPORTED_MEDIA_TYPE_MSG = "Unsupported media type";
    public static final String INVALID_USER_MSG = "You are not a valid user";
    public static final String INVALID_PRODUCT_MSG = "Product does not exist, please check product id";
    public static final String STOCK_UNAVAILABLE_MSG = "We do not have the requested stock";
    public static final String NO_SUCH_TEAM_MSG = "Bad team input!!";
    public static final String NO_SUCH_PUBLIC_CHANNEL_TIED_TO_ORG = "Invalid public channel, it may not be linked to the organisation";

    private ErrorMessages() {
        throw new UnsupportedOperationException();
    }
}
