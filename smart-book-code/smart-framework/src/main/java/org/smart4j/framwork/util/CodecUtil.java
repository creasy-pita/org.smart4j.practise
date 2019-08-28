package org.smart4j.framwork.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by creasypita on 8/27/2019.
 *
 * @ProjectName: lujunqiang-smart-book-code-master
 */
public class CodecUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);

    /**
     * 对字符串 UTF-8 的 url 编码
     * @param source
     * @return
     */
    public static String encodeUrl(String source){
        String target;
        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("encode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 对字符串 UTF-8 的 url 解码
     * @param source
     * @return
     */
    public static String decodeUrl(String source)
    {
        String target;
        try {
            target = URLDecoder.decode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("decode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }
}
