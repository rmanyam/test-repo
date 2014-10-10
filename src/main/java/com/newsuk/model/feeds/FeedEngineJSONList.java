package com.newsuk.model.feeds;

import java.util.List;

import com.google.api.client.util.Key;

public class FeedEngineJSONList {

	  @Key
	    public List<FeedItem> list;

	    @Key("has_more")
	    public boolean hasMore;
	    
public class FeedItem {
	@Key
	public String id;
	@Key
	public String title;
	@Key
	public String subtitle;
	@Key
	public String byline;
	@Key
	public String description;
	@Key
	public String startDate;
	@Key
	public String eventElapsedTime;
	@Key
	public String currentHalf;
	@Key
	public String matchId;
	@Key
	public String homeTeamTeamId;
	@Key
	public String homeTeamDisplayName;
	@Key
	public String homeTeamScore;
	@Key
	public String homeTeamActiveTeam;
	@Key
	public String homeTeamLineup;
	@Key
	public String awayTeamTeamId;
	@Key
	public String awayTeamDisplayName;
	@Key
	public String awayTeamScore;
	@Key
	public String awayTeamActiveTeam;
	@Key
	public String awayTeamLineup;
	@Key
	public String playerId;
	@Key
	public String playerDisplayName;
	@Key
	public String articleType;
	@Key
	public String type;
	@Key
	public String articleImageUrl;
	@Key
	public String articleImageCount;
	@Key
	public String videoId;
	@Key
	public String videoDuration;
	@Key
	public String articleId;
	@Key
	public String artId;
	@Key
	public String eventType;
	@Key
	public String articleUrl;

}
}
