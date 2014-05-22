package com.bazaarvoice.seo.sdk.footer;

import com.bazaarvoice.seo.sdk.url.BVSeoSdkUrl;

/**
 * Interface for adding bazaarvoice footer in the bazaarvoice seo content.
 * @author Anandan Narayanaswamy
 *
 */
public interface BVFooter {

	/**
	 * returns the footer based on the configuration that is set.
	 * @return footer
	 */
	String displayFooter(String accessMethod);

	/**
	 * Add some message to the Footer.
	 * @param message
	 */
	void addMessage(String message);
	
	void setExecutionTime(long executionTime);

	void setBvSeoSdkUrl(BVSeoSdkUrl _bvSeoSdkUrl);
	
}
