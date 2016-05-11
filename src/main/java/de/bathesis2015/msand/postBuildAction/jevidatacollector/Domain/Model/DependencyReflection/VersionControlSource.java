package de.bathesis2015.msand.postBuildAction.jevidatacollector.Domain.Model.DependencyReflection;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * image of the source information of a package
 * defined in a composer.lock file
 * 
 * VersionControlSource.java
 *
 * @author Michael Sandritter
 * 24.09.2015
 */
public class VersionControlSource {
	
	/**
	 * repository url
	 */
	@JsonProperty("url")
	private String url;
	
	/**
	 * repository type - Git/SVN
	 */
	@JsonProperty("type")
	private String type;
	
	/**
	 * reference that points on a unique version of a component
	 */
	@JsonProperty("reference")
	private String reference;
	
	public String getUrl() {
		return url;
	}
	public String getType() {
		return type;
	}
	public String getReference() {
		return reference;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	
}