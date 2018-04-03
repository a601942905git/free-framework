package com.free.framework.core.file;

/**
 * com.free.framework.core.file.FileControllerMappingUrl
 *
 * @author lipeng
 * @dateTime 2018/4/3 17:48
 */
public interface FileControllerMappingUrl {

    String FILE_CONTROLLER = "/files";

    /**
     * 根路径请求映射路径,用于restful接口
     */
    String FILE = "/";

    String FILE_RETURN_PAGE = "file/file";

    String FILE_UPLOAD = "/upload";
}
