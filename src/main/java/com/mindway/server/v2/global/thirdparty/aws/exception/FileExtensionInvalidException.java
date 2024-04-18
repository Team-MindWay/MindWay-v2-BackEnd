package com.mindway.server.v2.global.thirdparty.aws.exception;

import com.mindway.server.v2.global.exception.ErrorCode;
import com.mindway.server.v2.global.exception.MindWayException;

public class FileExtensionInvalidException extends MindWayException {
    public FileExtensionInvalidException() {
        super(ErrorCode.FILE_EXTENSION_INVALID);
    }
}
