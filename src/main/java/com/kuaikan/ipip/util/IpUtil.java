package com.kuaikan.ipip.util;

import com.kuaikan.ipip.bean.LocationInfo;
import com.kuaikan.ipip.handler.AutoReloadLocator;
import com.kuaikan.ipip.parser.IP;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liguochao.
 */
@Slf4j
public class IpUtil {

    static {
        new AutoReloadLocator();
    }


    /**
     * 根据用户请求获取用户地域信息
     *
     * @param request 请求信息
     * @return 地域信息精确到区/县
     */
    public static LocationInfo getIpLocation(HttpServletRequest request) {
        String realIp = request.getHeader("X-Forwarded-For");
        if (StringUtils.isEmpty(realIp) || "unknown".equals(realIp)) {
            realIp = request.getRemoteAddr();
        }
        return getIpLocation(realIp);
    }


    /**
     * 根据ip获取地域信息
     *
     * @param ip ip地址
     * @return 地域信息精确到区/县
     */
    public static LocationInfo getIpLocation(String ip) {
        try {
            String[] locations = IP.find(ip);
            if (locations.length == 3 || locations.length == 4) {
                return new LocationInfo(locations[0], locations[1], locations[2], "", "");
            } else if (locations.length == 5) {
                return new LocationInfo(locations[0], locations[1], locations[2], "", locations[4]);
            } else if (locations.length == 2) {
                return new LocationInfo(locations[0], locations[1], "", "", "");
            } else {
                return new LocationInfo("", "", "", "", "");
            }
        } catch (Exception e) {
            log.error("查找ip地址所在地区异常,ip={}", ip, e);
            return new LocationInfo("", "", "", "", "");
        }
    }
}
