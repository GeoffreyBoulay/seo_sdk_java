/*
 * ===========================================================================
 * Copyright 2014 Bazaarvoice, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ===========================================================================
 *
 */

package com.bazaarvoice.seo.sdk;
import static org.testng.Assert.assertEquals;

import java.net.URL;

import com.bazaarvoice.seo.sdk.servlet.DefaultRequestContext;
import org.junit.Ignore;
import org.testng.annotations.Test;

import com.bazaarvoice.seo.sdk.config.BVClientConfig;
import com.bazaarvoice.seo.sdk.config.BVConfiguration;
import com.bazaarvoice.seo.sdk.config.BVSdkConfiguration;
import com.bazaarvoice.seo.sdk.model.BVParameters;
import com.bazaarvoice.seo.sdk.model.ContentType;
import com.bazaarvoice.seo.sdk.model.SubjectType;
import com.bazaarvoice.seo.sdk.util.BVMessageUtil;


/**
 * This test case tests the basic bazaarvoice managed UI content.
 *
 * The test case focus is to ensure that the contents are loaded from file and
 * from http mechanism based on various configuration. Test case focusses on
 * loading content without pagination.
 *
 * @author Anandan Narayanaswamy
 */
public class BVManagedUIContent_SinglePageTest {

  /**
   * OS dependent. Enable the test case if the OS allows to do so.
   */
  @Ignore
  public void testSEOContentFromFile_SinglePagePRR() {
    DefaultRequestContext.initialize();
    URL url = getClass().getResource("/seo_local_files/myshco-359c29d8a8cbe3822bc0d7c58cb9f9ca");

    BVConfiguration bvConfig = new BVSdkConfiguration();
    bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "True"
    );
    bvConfig.addProperty(
      BVClientConfig.LOCAL_SEO_FILE_ROOT,
      url.toString().replace("file:/", "")
    );
    bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "myshco-359c29d8a8cbe3822bc0d7c58cb9f9ca"
    );
    bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "9344seob"
    );

    BVUIContent uiContent = new BVManagedUIContent(bvConfig);

    BVParameters bvParameters = new BVParameters();
    bvParameters.setUserAgent("google");
    bvParameters.setContentType(ContentType.REVIEWS);
    bvParameters.setSubjectType(SubjectType.PRODUCT);
    bvParameters.setSubjectId("3000001");

    String theUIContent = uiContent.getContent(bvParameters);
    assertEquals(
      theUIContent.contains("BVRRSourceID"),
      true,
      "there should be BvRRSourceID in the content"
    );

  }

  /**
   * Nullpointer issue fix while forming query string. This is OS dependent.
   * Test case. Enable only when it is required.
   */
  @Ignore
  public void testSEOContentFromFile_SinglePagePRR_pageUri() {
    DefaultRequestContext.initialize();
    URL url = getClass().getResource("/seo_local_files/myshco-359c29d8a8cbe3822bc0d7c58cb9f9ca");

    BVConfiguration bvConfig = new BVSdkConfiguration();
    bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "True"
    );
    bvConfig.addProperty(
      BVClientConfig.LOCAL_SEO_FILE_ROOT,
      url.toString().replace("file:/", "")
    );
    bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "myshco-359c29d8a8cbe3822bc0d7c58cb9f9ca"
    );
    bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "9344seob"
    );

    BVUIContent uiContent = new BVManagedUIContent(bvConfig);

    BVParameters bvParameters = new BVParameters();
    bvParameters.setUserAgent("google");
    bvParameters.setContentType(ContentType.REVIEWS);
    bvParameters.setSubjectType(SubjectType.PRODUCT);
    bvParameters.setSubjectId("3000001");
    bvParameters.setPageURI("http://localhost:8080/sample/xyz.jsp");

    String theUIContent = uiContent.getContent(bvParameters);
    assertEquals(
      theUIContent.contains("BVRRSourceID"),
      true,
      "there should be BvRRSourceID in the content"
    );

  }

  @Test
  public void testSEOContentFromHTTP_SinglePagePRR() {
    DefaultRequestContext.initialize();
    BVConfiguration bvConfig = new BVSdkConfiguration();
    bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    bvConfig.addProperty(
      BVClientConfig.STAGING,
      "true"
    );
    bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "agileville-78B2EF7DE83644CAB5F8C72F2D8C8491"
    );
    bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "Main_Site-en_US"
    );

    BVUIContent uiContent = new BVManagedUIContent(bvConfig);

    BVParameters bvParameters = new BVParameters();
    bvParameters.setUserAgent("google");
    bvParameters.setContentType(ContentType.REVIEWS);
    bvParameters.setSubjectType(SubjectType.PRODUCT);
    bvParameters.setSubjectId("data-gen-5zkafmln4wymhcfbp5u6hmv5q");

    String theUIContent = uiContent.getContent(bvParameters);
    assertEquals(
      theUIContent.contains("class=\"bvseo-review\" itemprop=\"review\" itemscope itemtype=\"http://schema.org/Review\""),
      true,
      "there should be reviews in the content"
    );

  }

  /**
   * Test case for testing seo content retrieval from http way + single page and
   * url being c2013 format.
   */
  @Test
  public void testSEOContentFromHTTP_SinglePageC2013() {
    DefaultRequestContext.initialize();
    BVConfiguration bvConfig = new BVSdkConfiguration();
    bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    bvConfig.addProperty(
      BVClientConfig.STAGING,
      "true"
    );
    bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "agileville-78B2EF7DE83644CAB5F8C72F2D8C8491"
    );
    bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "Main_Site-en_US"
    );
    BVUIContent uiContent = new BVManagedUIContent(bvConfig);

    BVParameters bvParameters = new BVParameters();
    bvParameters.setUserAgent("google");
    bvParameters.setPageURI("/someproduct.jsp?bvpage=ctre/iddata-gen-5zkafmln4wymhcfbp5u6hmv5q/stp");

    String theUIContent = uiContent.getContent(bvParameters);
    assertEquals(
      theUIContent.contains("class=\"bvseo-review\" itemprop=\"review\""),
      true,
      "there should be reviews in the content"
    );
  }

  /**
   * Test case for testing seo content retrieval from http way + returns a blank
   * page. Manually copy a blank page to Agileville staging environment and then
   * run the test case.
   */
  @Ignore
  public void testSEOContentFromHTTP_BlankPageTest() {
    DefaultRequestContext.initialize();
    BVConfiguration bvConfig = new BVSdkConfiguration();
    bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    bvConfig.addProperty(
      BVClientConfig.STAGING,
      "true"
    );
    bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "agileville-78B2EF7DE83644CAB5F8C72F2D8C8491"
    );
    bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "Main_Site-en_US"
    );
    BVUIContent uiContent = new BVManagedUIContent(bvConfig);

    BVParameters bvParameters = new BVParameters();
    bvParameters.setUserAgent("google");
    bvParameters.setSubjectType(SubjectType.PRODUCT);
    bvParameters.setContentType(ContentType.REVIEWS);
    bvParameters.setSubjectId("5000002_NO_BV");

    String theUIContent = uiContent.getContent(bvParameters);
    assertEquals(
      !theUIContent.contains("itemprop=\"aggregateRating\" itemscope itemtype=\"http://schema.org/AggregateRating\">"),
      true,
      "there should not be aggregateRate section in the content"
    );
    assertEquals(
      !theUIContent.contains("itemprop=\"review\" itemscope itemtype=\"http://schema.org/Review\">"),
      true,
      "there should not be reviews section in the content"
    );
    String expectedMessage = BVMessageUtil.getMessage("ERR0025");
    assertEquals(
      theUIContent.contains(expectedMessage),
      true,
      "Message does not contain expected message please test"
    );
  }

  /**
   * Test case for testing seo content retrieval from seo files + single page
   * and url being c2013 format. This is an OS dependent test case. Enable only
   * when it is required.
   */
  @Ignore
  public void testSEOContentFromFile_SinglePageC2013() {
    DefaultRequestContext.initialize();
    URL url = getClass().getResource("/seo_local_files/myshco-359c29d8a8cbe3822bc0d7c58cb9f9ca");

    BVConfiguration bvConfig = new BVSdkConfiguration();
    bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "true"
    );
    bvConfig.addProperty(
      BVClientConfig.LOCAL_SEO_FILE_ROOT,
      url.toString().replace("file:/", "")
    );
    bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "myshco-359c29d8a8cbe3822bc0d7c58cb9f9ca"
    );
    bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "9344seob"
    );
    BVUIContent uiContent = new BVManagedUIContent(bvConfig);

    BVParameters bvParameters = new BVParameters();
    bvParameters.setUserAgent("google");
    bvParameters.setPageURI("/someproduct.jsp?bvpage=ctre/id3000001/stp");

    String theUIContent = uiContent.getContent(bvParameters);
    assertEquals(
      theUIContent.contains("BVRRSourceID"),
      true,
      "there should be BvRRSourceID in the content"
    );
  }

  /**
   * File content access.
   *
   * Test case to test if the content file is unavailable. If the file content
   * is unavailable it should display a message file unavailable error.
   */
  @Test
  public void testSEOContent_SinglePage_File_FileUnavailableError() {
    DefaultRequestContext.initialize();
    BVConfiguration bvConfig = new BVSdkConfiguration();
    bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "True"
    );
    bvConfig.addProperty(
      BVClientConfig.LOCAL_SEO_FILE_ROOT,
      "E:\\alpha-beta-invalid-\\Seo_cyberduck\\myshco-359c29d8a8cbe3822bc0d7c58cb9f9ca"
    );
    bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "myshco-359c29d8a8cbe3822bc0d7c58cb9f9ca"
    );
    bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "9344seob"
    );

    BVUIContent uiContent = new BVManagedUIContent(bvConfig);

    BVParameters bvParameters = new BVParameters();
    bvParameters.setUserAgent("google");
    bvParameters.setContentType(ContentType.REVIEWS);
    bvParameters.setSubjectType(SubjectType.PRODUCT);
    bvParameters.setSubjectId("3000001_thisFiledoesNotExist");

    String theUIContent = uiContent.getContent(bvParameters);
    assertEquals(
      !theUIContent.contains("BVRRSourceID"),
      true,
      "there should not be BVRRSourceID in the content"
    );
    assertEquals(
      theUIContent.contains("The resource to the URL or file is currently unavailable."),
      true,
      "there should be resource unavailable message"
    );
  }

  /**
   * File content access.
   *
   * Test case to test if the content file is unavailable and if it is bvreveal.
   * If the file content is unavailable it should display a message file
   * unavailable error.
   */
  @Test
  public void testSEOContent_SinglePage_File_FileUnavailableError_bvReveal() {
    DefaultRequestContext.initialize();
    BVConfiguration bvConfig = new BVSdkConfiguration();
    bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "True"
    );
    bvConfig.addProperty(
      BVClientConfig.LOCAL_SEO_FILE_ROOT,
      "E:\\alpha-beta-invalid-\\Seo_cyberduck\\myshco-359c29d8a8cbe3822bc0d7c58cb9f9ca"
    );
    bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "myshco-359c29d8a8cbe3822bc0d7c58cb9f9ca"
    );
    bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "9344seob"
    );

    BVUIContent uiContent = new BVManagedUIContent(bvConfig);

    BVParameters bvParameters = new BVParameters();
    bvParameters.setUserAgent("abcdefghijkl");
    bvParameters.setContentType(ContentType.REVIEWS);
    bvParameters.setSubjectType(SubjectType.PRODUCT);
    bvParameters.setSubjectId("3000001_thisFiledoesNotExist");
    bvParameters.setBaseURI("http://www.example.com/store/products/data-gen-696yl2lg1kurmqxn88fqif5y2/");
    bvParameters.setPageURI("http://www.example.com/store/products/data-gen-696yl2lg1kurmqxn88fqif5y2?bvreveal=debug");
    String theUIContent = uiContent.getContent(bvParameters);
    assertEquals(
      !theUIContent.contains("BVRRSourceID"),
      true,
      "there should not be BVRRSourceID in the content"
    );
    assertEquals(
      theUIContent.contains("The resource to the URL or file is currently unavailable."),
      true,
      "there should be resource unavailable message"
    );
    assertEquals(
      theUIContent.contains("debug"),
      true,
      "there should be debug message"
    );
  }

  /**
   * HTTP content access.
   *
   * Test case to test if the content file is unavailable. If the file content
   * is unavailable it should display a message file unavailable error.
   */
  @Test
  public void testSEOContent_SinglePage_HTTP_FileUnavailableError() {
    DefaultRequestContext.initialize();
    BVConfiguration bvConfig = new BVSdkConfiguration();
    bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "myshco-359c29d8a8cbe3822bc0d7c58cb9f9ca"
    );
    bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "9344seob"
    );

    BVUIContent uiContent = new BVManagedUIContent(bvConfig);

    BVParameters bvParameters = new BVParameters();
    bvParameters.setUserAgent("google");
    bvParameters.setContentType(ContentType.REVIEWS);
    bvParameters.setSubjectType(SubjectType.PRODUCT);
    bvParameters.setSubjectId("3000001_thisFiledoesNotExist");

    String theUIContent = uiContent.getContent(bvParameters);
    assertEquals(
      !theUIContent.contains("BVRRSourceID"),
      true,
      "there should not be BvRRSourceID in the content"
    );
    assertEquals(
      theUIContent.contains("The resource to the URL or file is currently unavailable."),
      true,
      "there should be resource unavailable message"
    );
  }

  /**
   * TODO: We need to have a valid HTTP product to start sending.
   */
  @Ignore
  public void testSEOContentFromHTTP_withOutIntegrationScript() {
    DefaultRequestContext.initialize();
    BVConfiguration bvConfig = new BVSdkConfiguration();
    bvConfig.addProperty(BVClientConfig.CLOUD_KEY, "");
    bvConfig.addProperty(BVClientConfig.BV_ROOT_FOLDER, "");
    bvConfig.addProperty(BVClientConfig.LOAD_SEO_FILES_LOCALLY, "False");

    BVUIContent uiContent = new BVManagedUIContent(bvConfig);

    BVParameters bvParameters = new BVParameters();
    bvParameters.setUserAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html");
    bvParameters.setBaseURI("http://www.example.com/store/products/data-gen-696yl2lg1kurmqxn88fqif5y2/");
    bvParameters.setPageURI("http://www.example.com/store/products/data-gen-696yl2lg1kurmqxn88fqif5y2/?utm_campaign=bazaarvoice&utm_medium=SearchVoice&utm_source=RatingsAndReviews&utm_content=Default&bvrrp=12325/reviews/product/2/data-gen-696yl2lg1kurmqxn88fqif5y2.htm");
    bvParameters.setContentType(ContentType.REVIEWS);
    bvParameters.setSubjectType(SubjectType.PRODUCT);
    bvParameters.setSubjectId("data-gen-696yl2lg1kurmqxn88fqif5y2");

    String theUIContent = uiContent.getContent(bvParameters);
  }

  /**
   * Test case for checking with bad cloud key.
   */
  @Test
  public void testSEOContentFromHTTP_InvalidCloudKey() {
    DefaultRequestContext.initialize();
    BVConfiguration bvConfig = new BVSdkConfiguration();
    bvConfig.addProperty(
      BVClientConfig.LOAD_SEO_FILES_LOCALLY,
      "false"
    );
    bvConfig.addProperty(
      BVClientConfig.CLOUD_KEY,
      "myshco_bad_URL-359c29d8a8cbe3822bc0d7c58cb9f9ca"
    );
    bvConfig.addProperty(
      BVClientConfig.BV_ROOT_FOLDER,
      "9344seob"
    );

    BVUIContent uiContent = new BVManagedUIContent(bvConfig);

    BVParameters bvParameters = new BVParameters();
    bvParameters.setUserAgent("google");
    bvParameters.setContentType(ContentType.REVIEWS);
    bvParameters.setSubjectType(SubjectType.PRODUCT);
    bvParameters.setSubjectId("3000001");

    String theUIContent = uiContent.getContent(bvParameters);
    assertEquals(
      !theUIContent.contains("BVRRSourceID"),
      true,
      "there should be BvRRSourceID in the content"
    );
  }

}
