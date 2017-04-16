package com.scitus.sparks;

public class HttpConstants
{
    private HttpConstants() {}

    public class MimeTypes
    {
        private MimeTypes() {}

        public static final String APPLICATION_JSON = "application/json; charset=uft-8";
    }

    public class StatusCodes
    {
        private StatusCodes() {}

        public static final int OK = 200;
        public static final int CREATED = 201;
        public static final int FAILURE = 500;
    }
}
