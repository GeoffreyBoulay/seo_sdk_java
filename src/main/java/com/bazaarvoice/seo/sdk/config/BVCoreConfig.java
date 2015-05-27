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

package com.bazaarvoice.seo.sdk.config;

/**
 * Enums that should be used only for configuring bazaarvoice server
 * configuration.
 *
 * @author Anandan Narayanaswamy
 */
public enum BVCoreConfig {

  TESTING_STAGING_S3_HOSTNAME("testingStagingS3Hostname"),
  TESTING_PRODUCTION_S3_HOSTNAME("testingProductionS3Hostname"),
  STAGING_S3_HOSTNAME("stagingS3Hostname"),
  PRODUCTION_S3_HOSTNAME("productionS3Hostname");

  private String propertyName;

  private BVCoreConfig(String propertyName) {
    this.propertyName = propertyName;
  }

  public String getPropertyName() {
    return this.propertyName;
  }
}
