package me.chanjar.weixin.mp.api.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserTagService;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;

/**
 *
 * @author binarywang(https://github.com/binarywang)
 *         Created by Binary Wang on 2016/9/2.
 */
public class WxMpUserTagServiceImpl implements WxMpUserTagService {
  protected final Logger log = LoggerFactory.getLogger(WxMpDataCubeServiceImpl.class);
  private static final String API_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/tags";
  private static final String API_USER_TARG_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/user/tag";

  private WxMpService wxMpService;

  public WxMpUserTagServiceImpl(WxMpService wxMpService) {
    this.wxMpService = wxMpService;
  }

  @Override
  public WxUserTag tagCreate(String name) throws WxErrorException {
    String url = API_URL_PREFIX + "/create";
    JsonObject json = new JsonObject();
    JsonObject groupJson = new JsonObject();
    groupJson.addProperty("name", name);
    json.add("tag", groupJson);

    String responseContent = this.wxMpService.execute(
            new SimplePostRequestExecutor(),
            url,
            json.toString());
    this.log.debug("\nurl:{}\nparams:{}\nresponse:{}",url, name, responseContent);
    return WxUserTag.fromJson(responseContent);
  }

  @Override
  public List<WxUserTag> tagList() throws WxErrorException {
    String url = API_URL_PREFIX + "/get";
    String responseContent = this.wxMpService.execute(
            new SimplePostRequestExecutor(),
            url,
            null);
    return WxUserTag.fromJsonList(responseContent);
  }

  @Override
  public WxMpUserList tagUserList(Integer tag, String next_openid) throws WxErrorException {
    String url = API_USER_TARG_URL_PREFIX + "/get";
    JsonObject json = new JsonObject();
    json.addProperty("tagid", tag);
    if (null != next_openid) json.addProperty("next_openid", next_openid);
    String responseContent = this.wxMpService.execute(new SimplePostRequestExecutor(), url, json.toString());
    return WxMpUserList.fromJson(responseContent);
  }
}
