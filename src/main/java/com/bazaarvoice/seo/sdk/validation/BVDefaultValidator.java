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

package com.bazaarvoice.seo.sdk.validation;

import java.net.URI;
import java.net.URISyntaxException;

import com.bazaarvoice.seo.sdk.servlet.RequestContext;
import com.bazaarvoice.seo.sdk.servlet.RequestFilter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bazaarvoice.seo.sdk.config.BVClientConfig;
import com.bazaarvoice.seo.sdk.config.BVConfiguration;
import com.bazaarvoice.seo.sdk.model.BVParameters;
import com.bazaarvoice.seo.sdk.util.BVMessageUtil;

/**
 * Validator implementation class for BVParameters & BVConfiguration.
 *
 * @author Anandan Narayanaswamy
 *
 */
public class BVDefaultValidator implements BVValidator {

  private static final Logger _logger = LoggerFactory.getLogger(BVDefaultValidator.class);
  private final static String HTTP_HEADER_USER_AGENT = "User-Agent";

  private StringBuilder errorMessages;

  public BVDefaultValidator() {
    errorMessages = new StringBuilder();
  }

  /**
   * Validator method to validate all the attributes in bvParameter object
   *
   * @return String if there are any errors for the attributes which are either null or invalid.
   */
  public String validate(BVConfiguration bvConfiguration, BVParameters bvParams) {
    if (bvConfiguration == null) {
      errorMessages.append(BVMessageUtil.getMessage("ERR0007"));
      _logger.error(BVMessageUtil.getMessage("ERR0007"));
      return errorMessages.toString();
    }

    if (bvParams == null) {
      errorMessages.append(BVMessageUtil.getMessage("ERR0011"));
      _logger.error(BVMessageUtil.getMessage("ERR0011"));
      return errorMessages.toString();
    }

    boolean loadSeoFilesLocally = Boolean.parseBoolean(bvConfiguration.getProperty(
        BVClientConfig.LOAD_SEO_FILES_LOCALLY.getPropertyName()));
    if (loadSeoFilesLocally) {
      String localSeoFileRoot = bvConfiguration.getProperty(BVClientConfig.LOCAL_SEO_FILE_ROOT.getPropertyName());
      if (StringUtils.isBlank(localSeoFileRoot)) {
        errorMessages.append(BVMessageUtil.getMessage("ERR0010"));
      }
    } else {
      String cloudKey = bvConfiguration.getProperty(BVClientConfig.CLOUD_KEY.getPropertyName());
      if (StringUtils.isBlank(cloudKey)) {
        errorMessages.append(BVMessageUtil.getMessage("ERR0020"));
      }
    }

    String rootFolder = bvConfiguration.getProperty(BVClientConfig.BV_ROOT_FOLDER.getPropertyName());
    if (StringUtils.isBlank(rootFolder)) {
      errorMessages.append(BVMessageUtil.getMessage("ERR0021"));
    }

    if (StringUtils.isBlank(bvParams.getUserAgent())
            && !RequestFilter.getIsConfigured()) {
      _logger.warn(BVMessageUtil.getMessage("WRN0000"));
    }

    URI uri = null;
    if (bvParams.getBaseURI() != null) {
      try {
        uri = new URI(bvParams.getBaseURI());
      } catch (URISyntaxException e) {
        errorMessages.append(BVMessageUtil.getMessage("ERR0023"));
      }
    }

    if (bvParams.getPageURI() != null) {
      try {
        uri = new URI(bvParams.getPageURI());
      } catch (URISyntaxException e) {
        errorMessages.append(BVMessageUtil.getMessage("ERR0022"));
      }
    }

    if (StringUtils.isBlank(bvParams.getPageURI()) ||
        !bvParams.getPageURI().contains("bvpage")) {

      if (StringUtils.isBlank(bvParams.getSubjectId())) {
        errorMessages.append(BVMessageUtil.getMessage("ERR0014"));
      }

      if (bvParams.getSubjectType() == null) {
        errorMessages.append(BVMessageUtil.getMessage("ERR0016"));
      }

      if (bvParams.getContentType() == null) {
        errorMessages.append(BVMessageUtil.getMessage("ERR0015"));
      }

    }


    if (errorMessages.length() > 0) {
      _logger.error("There is an error : " + errorMessages.toString());
      return errorMessages.toString();
    }

    return null;
  }

}
