package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.mp.api.ApiTestModule;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;

import static org.testng.AssertJUnit.assertNotNull;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 *
 * @author binarywang(https://github.com/binarywang)
 *         Created by Binary Wang on 2016/9/2.
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpUserTagServiceImplTest {
  @Inject
  protected WxMpServiceImpl wxService;

  @Test
  public void testTagCreate() throws Exception {
    String tagName = "测试标签";
    WxUserTag res = this.wxService.getUserTagService().tagCreate(tagName);
    System.out.println(res);
    Assert.assertEquals(tagName, res.getName());
  }

  @Test
  public void tagList() throws Exception {
    List<WxUserTag> tagList = this.wxService.getUserTagService().tagList();
    assertNotNull(tagList);
    Assert.assertFalse(tagList.size() < 1);
    System.out.println(tagList);
  }

}