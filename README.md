# ipip-client
ipip-client 是基于ipip地址库用于解析ip地址信息的项目, 支持地址库数据变更自动监听并加载！(目前仅支持ipip 高级版数据)

# 特点  
* 一键引入jar包，无需任何配置，对业务代码零侵入  
* 加载地址库数据使用默认地址(/data/17monipdb/ipip/gaoji.dat)，支持用户自定义(最高优先级)，在启动项目时指定JVM属性 -Dipip.file=***  
* 地址库数据变更自动reload时间间隔默认为1小时，用户可自定义(最高优先级)，启动项目时指定JVM属性 -Dipip.interval=*** （秒级）

# 使用  
* pom中引入ipip-client  
```
<dependency>
    <groupId>com.kuaikan.ipip</groupId>
    <artifactId>ipip-client</artifactId>
    <version>1.0</version>
</dependency>
```
* 调用IpUtil中相关方法  
LocationInfo location = IpUtil.getIpLocation(request);  
LocationInfo location = IpUtil.getIpLocation(ip);

# 说明
LocationInfo 字段说明  
country 国家  
province 省份  
city 城市  
county 县  
isp 运营商  
